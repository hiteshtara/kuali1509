/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.scheduling.seq;

import org.quartz.CronTrigger;
import org.quartz.TriggerUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * This class is default implementation of ScheduleSequence Interface.
 * <p>
 * This implementation should be used if dates generated by schedule sequence needs no modification. Implementation uses
 * org.quartz.TriggerUtils class to generate List of dates from Cron expression.
 * <p>
 * It uses current time zone, where application is hosted. 
 * <p>
 * Note: Dates used in generating schedule must be wrapped with required time accuracy.
 * e.g.  Start Date: 02/01/09 10:10 End Date: 02/05/09 10:10 
 * Generated Dates will be in between 02/01/09 10:10 and 02/05/09 10:10.
 * Any date expected before 02/01/09 10:09 will be ignored. Date 02/01/09 10:00 will be ignored.
 * Any date expected after  02/05/09 10:11 will be ignored. Date 02/05/09 12:00 will be ignored.
 */
public class DefaultScheduleSequence implements ScheduleSequence {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Date> executeScheduleSequence(String expression, Date startDate, Date endDate) throws ParseException {

        CronTrigger ct = new CronTrigger(NAME, GROUP, JOBNAME, JOBGROUP, startDate, null, expression);
        return TriggerUtils.computeFireTimesBetween(ct, null, startDate, endDate);
    }

}