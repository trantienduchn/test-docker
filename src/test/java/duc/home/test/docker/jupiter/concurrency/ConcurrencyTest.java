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

package duc.home.test.docker.jupiter.concurrency;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class ConcurrencyTest {

    static class Key {

        private final String key;

        Key(String key) {
            this.key = key;
        }

        @Override
        public final boolean equals(Object o) {
            if (o instanceof Key) {
                Key key1 = (Key) o;
                return Objects.equals(this.key, key1.key);
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return Objects.hash(key);
        }
    }

    static class Value {

        private final String value;

        Value(String value) {
            this.value = value;
        }
    }

    @Test
    void abc() {
        Set<Value> values = ConcurrentHashMap.newKeySet();
        ConcurrentHashMap<Key, Value> map = new ConcurrentHashMap<>();

        int threadCount = 100;
        ExecutorService service = Executors.newFixedThreadPool(threadCount);

        List<? extends Future<?>> futures = IntStream.rangeClosed(1, threadCount)
            .mapToObj(thread -> service.submit(() -> {
                Value x = map.computeIfAbsent(new Key("key"), value -> {
                    try {
                        Thread.sleep(100 - thread);
                        return new Value("value");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
                if (x == null) {
                    System.out.println("d");
                }
                values.add(x);
            }))
            .collect(Collectors.toList());

        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println(values.size());
    }
}
