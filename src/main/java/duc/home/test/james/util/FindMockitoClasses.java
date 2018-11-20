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

package duc.home.test.james.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FindMockitoClasses {

    public static void main(String[] args) throws IOException {

        Path jamesPath = Paths.get("/Users/duc/repo/java/linagora/james-project");
        String mockitoClasses = Files.walk(jamesPath)
            .filter(path -> path.toString().contains("/test/") && path.toString().endsWith(".java"))
            .filter(path -> {
                try {
                    return Files.lines(path)
                        .anyMatch(line -> line.contains("org.mockito"));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            })
            .map(path -> {
                try {
                    String packageName = Files.lines(path)
                        .filter(line -> line.startsWith("package "))
                        .findFirst()
                        .map(line -> line.substring(8, line.length() - 1))
                        .get();
                    String fileName = path.getFileName().toString();
                    return packageName + "." + fileName.substring(0, fileName.length() - 5);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "cassandra";
                }
            })
            .filter(className -> !className.toLowerCase().contains("cassandra"))
            .collect(Collectors.joining("||"));

        System.out.println(mockitoClasses);
    }
}
