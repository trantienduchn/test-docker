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

package duc.home.test.ical4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

public class Run {

    public static void main(String[] args) throws IOException, ParserException {
        String x = "BEGIN:VCALENDAR\n" +
                "PRODID:-//Aliasource Groupe LINAGORA//OBM Calendar 3.2.1-rc2//FR\n" +
                "CALSCALE:GREGORIAN\n" +
                "X-OBM-TIME:1483703436\n" +
                "VERSION:2.0\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "CREATED:20170106T115035Z\n" +
                "LAST-MODIFIED:20170106T115036Z\n" +
                "DTSTAMP:20170106T115036Z\n" +
                "DTSTART:20170111T090000Z\n" +
                "DURATION:PT1H30M\n" +
                "TRANSP:OPAQUE\n" +
                "SEQUENCE:0\n" +
                "SUMMARY:Sprint planning #23\n" +
                "DESCRIPTION:\n" +
                "CLASS:PUBLIC\n" +
                "PRIORITY:5\n" +
                "ORGANIZER;X-OBM-ID=128;CN=Raphael OUAZANA:MAILTO:ouazana@linagora.com\n" +
                "X-OBM-DOMAIN:linagora.com\n" +
                "X-OBM-DOMAIN-UUID:02874f7c-d10e-102f-acda-0015176f7922\n" +
                "LOCATION:Hangout\n" +
                "CATEGORIES:\n" +
                "X-OBM-COLOR:\n" +
                "UID:f1514f44bf39311568d640727cff54e819573448d09d2e5677987ff29caa01a9e047fe\n" +
                " b2aab16e43439a608f28671ab7c10e754ce92be513f8e04ae9ff15e65a9819cf285a6962bc\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Matthieu EXT_BAECHLER;PARTSTAT=NEE\n" +
                " DS-ACTION;X-OBM-ID=302:MAILTO:baechler@linagora.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Laura ROYET;PARTSTAT=NEEDS-ACTION;\n" +
                " X-OBM-ID=723:MAILTO:royet@linagora.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Raphael OUAZANA;PARTSTAT=ACCEPTED;\n" +
                " X-OBM-ID=128:MAILTO:ouazana@linagora.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Luc DUZAN;PARTSTAT=NEEDS-ACTION;X-\n" +
                " OBM-ID=715:MAILTO:duzan@linagora.com\n" +
                "ATTENDEE;CUTYPE=RESOURCE;CN=Salle de reunion Lyon;PARTSTAT=ACCEPTED;X-OBM-\n" +
                " ID=66:MAILTO:noreply@linagora.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Antoine DUPRAT;PARTSTAT=NEEDS-ACTI\n" +
                " ON;X-OBM-ID=453:MAILTO:duprat@linagora.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Benoît TELLIER;PARTSTAT=NEEDS-ACTI\n" +
                " ON;X-OBM-ID=623:MAILTO:tellier@linagora.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;RSVP=TRUE;CN=Quynh Quynh N NGUYEN;PARTSTAT=NEED\n" +
                " S-ACTION;X-OBM-ID=769:MAILTO:nguyen@linagora.com\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR\n";


        Calendar calendar2 = new CalendarBuilder().build(new ByteArrayInputStream(x.getBytes()));
    }
}
