/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package duc.home.test.docker.rabbitmq;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import org.testcontainers.shaded.org.apache.http.HttpResponse;
import org.testcontainers.shaded.org.apache.http.auth.AuthScope;
import org.testcontainers.shaded.org.apache.http.auth.UsernamePasswordCredentials;
import org.testcontainers.shaded.org.apache.http.client.CredentialsProvider;
import org.testcontainers.shaded.org.apache.http.client.HttpClient;
import org.testcontainers.shaded.org.apache.http.client.methods.HttpGet;
import org.testcontainers.shaded.org.apache.http.impl.client.BasicCredentialsProvider;
import org.testcontainers.shaded.org.apache.http.impl.client.CloseableHttpClient;
import org.testcontainers.shaded.org.apache.http.impl.client.HttpClientBuilder;

import com.google.common.collect.ImmutableMap;
import com.rabbitmq.client.ConnectionFactory;

import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

class ExclusiveQueueCreationTest {

    private static final String IMAGE_NAME = "rabbitmq:3.7.7-management";
    private static final int DEFAULT_RABBITMQ_PORT = 5672;
    private static final int DEFAULT_RABBITMQ_ADMIN_PORT = 15672;
    private static final Duration TEN_MINUTES_TIMEOUT = Duration.ofMinutes(10);

    private static GenericContainer createContainer() {
        return new GenericContainer<>(IMAGE_NAME)
            .withExposedPorts(DEFAULT_RABBITMQ_PORT, DEFAULT_RABBITMQ_ADMIN_PORT)
            .waitingFor(waitStrategy())
            .withCreateContainerCmdModifier(cmd -> cmd.getHostConfig()
            .withTmpFs(ImmutableMap.of("/var/lib/rabbitmq/mnesia", "rw,noexec,nosuid,size=100m")));
    }

    private static WaitStrategy waitStrategy() {
        return new WaitAllStrategy()
            .withStrategy(Wait.forHttp("").forPort(DEFAULT_RABBITMQ_ADMIN_PORT)
                .withStartupTimeout(TEN_MINUTES_TIMEOUT))
            .withStartupTimeout(TEN_MINUTES_TIMEOUT);
    }

    private GenericContainer container;
    private Sender sender;

    @BeforeEach
    void setUp() {
        container = createContainer();
        container.start();

        sender = RabbitFlux.createSender(new SenderOptions().connectionFactory(connectionFactory()));
    }

    @AfterEach
    void tearDown() {
        container.stop();
    }

    private ConnectionFactory connectionFactory() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setUri("amqp://guest:guest@localhost:" + container.getMappedPort(DEFAULT_RABBITMQ_PORT));
            connectionFactory.useNio();
            return connectionFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void queueDeclareDurableButNot() throws IOException {
        sender.declareQueue(QueueSpecification.queue()
            .durable(true)
            .exclusive(false)
            .autoDelete(false)
            .arguments(ImmutableMap.of()))
            .block();

        HttpClient client = managementClient();
        HttpResponse response = client.execute(new HttpGet("http://localhost:" + container.getMappedPort(DEFAULT_RABBITMQ_ADMIN_PORT) + "/api/queues"));
        String body = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
        System.out.println(body);

        // [{"node":"rabbit@ec41c34c7910","arguments":{},"exclusive":true,"auto_delete":true,"durable":false,"vhost":"/","name":"amq.gen-o82q7LUrrd3N5IHq9noMnw"}]
        assertThat(body).contains("\"durable\":false");
        assertThat(body).contains("\"auto_delete\":true");
        assertThat(body).contains("\"exclusive\":true");
    }

    @Test
    void queueDeclareWithNameShouldHasExactlyDurable() throws IOException {
        sender.declareQueue(QueueSpecification.queue("name")
            .durable(true)
            .exclusive(false)
            .autoDelete(false)
            .arguments(ImmutableMap.of()))
            .block();

        HttpClient client = managementClient();
        HttpResponse response = client.execute(new HttpGet("http://localhost:" + container.getMappedPort(DEFAULT_RABBITMQ_ADMIN_PORT) + "/api/queues"));
        String body = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
        System.out.println(body);

        // [{"node":"rabbit@ff4abaae5c05","arguments":{},"exclusive":false,"auto_delete":false,"durable":true,"vhost":"/","name":"name"}]
        assertThat(body).contains("\"durable\":true");
        assertThat(body).contains("\"auto_delete\":false");
        assertThat(body).contains("\"exclusive\":false");
    }

    private CloseableHttpClient managementClient() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("guest", "guest");
        provider.setCredentials(AuthScope.ANY, credentials);

        return HttpClientBuilder.create()
          .setDefaultCredentialsProvider(provider)
          .build();
    }
}
