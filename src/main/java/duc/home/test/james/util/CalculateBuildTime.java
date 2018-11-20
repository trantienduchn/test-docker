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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateBuildTime {

    private static final String BUILD_TIME = "[2.552s][1.678s][1.712s][17.093s][5.489s][0.523s][50.816s][21.720s][0.982s][0.781s][8.009s][0.462s][1.813s][1.914s][0.867s][29.958s][2.541s][0.465s][1.435s][7.373s][8.792s][0.466s][0.860s][0.443s][6.387s][3.713s][0.456s][0.560s][10.704s][3.797s][3.975s][1.011s][0.444s][0.782s][18.637s][0.798s][6.661s][4.422s][9.603s][13:02min][5.442s][12.513s][02:46min][27.426s][01:37min][33.694s][14.008s][10.990s][9.707s][7.288s][5.478s][2.386s][1.456s][10.175s][8.294s][12.304s][17.427s][3.203s][5.646s][13.400s][2.168s][0.444s][01:25min][3.031s][1.595s][2.457s][6.252s][4.742s][7.669s][0.740s][0.658s][0.438s][1.534s][9.878s][4.216s][2.462s][4.478s][0.477s][3.010s][11.676s][0.456s][2.578s][10:33min][1.720s][2.841s][0.866s][21.704s][11.151s][1.492s][02:25min][20.826s][47.164s][1.870s][0.441s][1.548s][02:29min][17.786s][24.929s][2.695s][0.435s][1.784s][8.917s][47.637s][1.903s][3.962s][0.463s][0.823s][0.654s][3.336s][2.063s][1.286s][2.320s][1.308s][1.049s][12.480s][6.286s][21.653s][6.582s][1.851s][1.354s][0.840s][11.951s][3.937s][0.961s][7.436s][6.617s][1.144s][1.278s][5.598s][8.721s][1.623s][5.933s][15.880s][3.166s][57.948s][01:46min][21.414s][30.333s][2.662s][4.678s][13.674s][4.412s][4.345s][2.030s][0.997s][1.251s][6.223s][2.430s][36.206s][1.751s][36.388s][1.681s][7.474s][1.605s][12.512s][1.677s][1.583s][1.576s][0.434s][15.493s][01:09min][05:52min][57.125s][2.729s][3.673s][27.683s][8.692s][29.076s][14.824s][2.838s][2.386s][12.146s][3.280s][29.528s][20.960s][33.063s][12.883s][03:02min][0.776s][2.998s][31.854s][3.576s][8.651s][27.007s][04:54min][07:36min][23:53min][01:08min][0.440s][02:55min]";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(BUILD_TIME);

        double totalTime = 0;

        while (matcher.find()) {
            String time = matcher.group(1);
            if (time.endsWith("s")) {
//                time = time.substring(0, time.length() - 1);
//                totalTime += Double.valueOf(time);
//                System.out.println(totalTime + " - " + time);
            }
            else {
                String timeWithMinute = time.substring(0, time.length() - 3);
                String[] timeWithMinuteSplitted = timeWithMinute.split(":");
                totalTime += Double.valueOf(timeWithMinuteSplitted[0]) * 60 + Double.valueOf(timeWithMinuteSplitted[1]);

                System.out.println(totalTime + " - " + time);
            }
        }

        System.out.println(totalTime);
    }
}
