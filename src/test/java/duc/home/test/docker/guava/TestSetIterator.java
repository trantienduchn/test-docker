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

package duc.home.test.docker.guava;

import java.util.Base64;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.apache.commons.lang.StringEscapeUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class TestSetIterator {

    @Test
    void name() {
        Multimap<String, String> x = ArrayListMultimap.create();
        x.put("3", "33");
        x.put("4", "44");

        Sets.SetView<String> diff = Sets.difference(x.keySet(), Sets.newHashSet(x.values()));
        for (String d : diff) {
            System.out.println(d);
        }
    }

    @Test
    void test1() {
//        String x = new String(Base64.getDecoder().decode("YXBwbGljYXRpb24vaWNzLCBhcHBsaWNhdGlvbi96aXA"));
//        System.out.println(x);
//        System.out.println(ImmutableList.of("application/ics", "application/zip"));
//        System.out.println(ImmutableList.of("application/ics, application/zip"));
        PropertiesConfiguration p = new PropertiesConfiguration();
        System.out.println(p.getListDelimiter());
    }
}
