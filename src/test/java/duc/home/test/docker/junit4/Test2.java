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

package duc.home.test.docker.junit4;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.ClassRule;
import org.junit.Test;

public class Test2 {

    @ClassRule
    public static StaticTestRule staticTestRule = StaticTestRule.getRule();

    @Test
    public void test2() throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostName());
    }

    @Test
    public void test22() {
        System.out.println("test2.2");
    }
}
