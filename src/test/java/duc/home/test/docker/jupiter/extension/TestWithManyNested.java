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

package duc.home.test.docker.jupiter.extension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

@ExtendWith(TestWithManyNested.NestedExtension.class)
class TestWithManyNested {

    static class NestedExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

        @Override
        public void beforeAll(ExtensionContext extensionContext) throws Exception {
            Class<?> x = extensionContext.getRequiredTestClass();
            if (x.getEnclosingClass() == null) {
                System.out.println("beforeAll");
            }
        }

        @Override
        public void afterAll(ExtensionContext extensionContext) throws Exception {
            Class<?> x = extensionContext.getRequiredTestClass();
            if (x.getEnclosingClass() == null) {
                System.out.println("afterAll");
            }
        }

        @Override
        public void beforeEach(ExtensionContext extensionContext) throws Exception {
            System.out.println("beforeEach");
        }

        @Override
        public void afterEach(ExtensionContext extensionContext) throws Exception {
            System.out.println("afterEach");
        }
    }

//    @BeforeAll
//    static void setup() {
//        System.out.println("before all");
//    }
//
//    @AfterAll
//    static void tearDown() {
//        System.out.println("after all");
//    }

    @Nested
    class Nested1Test {

        @Test
        void nested1() {
            System.out.println("nested1");
        }
    }

    @Nested
    class Nested2 {

        @Test
        void nested2() {
            System.out.println("nested2");
        }
    }

//    @Test
//    void test() throws IOException {
//        Files.walk(Paths.get("/Users/duc/repo/java/linagora/james-project/"))
//            .filter(path -> StringUtils.endsWith(path.getFileName().toString(), ".java"))
//            .filter(path -> {
//                try {
//                    String x = new String(Files.readAllBytes(path));
//                    return (x.contains("@ExtendWith") || x.contains("@RegisterExtension")) && x.contains("@Nested");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return false;
//            })
//            .forEach(path -> System.out.println(path.getFileName()));
//    }
}
