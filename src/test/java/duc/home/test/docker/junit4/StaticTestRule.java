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

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class StaticTestRule extends TestWatcher {

    public static StaticTestRule getRule() {
        return rule;
    }

    private static final StaticTestRule rule = new StaticTestRule();

    private int count;

    public StaticTestRule() {
        count = 0;
    }

//    @Override
//    public Statement apply(Statement statement, Description description) {
//        System.out.println("apply static rule: " + ++count);
//        return statement;
//    }

    @Override
    protected void finished(Description description) {
        System.out.println("finish static rule: " + ++count);
    }

    @Override
    protected void starting(Description description) {
        System.out.println("start static rule: " + ++count);
    }

    @Override
    protected void succeeded(Description description) {
        System.out.println("succeeded static rule: " + ++count);
    }
}
