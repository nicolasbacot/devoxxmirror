package com.devoxx.model;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ScheduleSessionTest {

    @Test
    public void fromHour_should_return_hour_minute() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:30:00.0");
        
        assertThat(session.getFromHour()).isEqualTo("9:30");
    }

    @Test
    public void toHour_should_return_hour_minute() {
        ScheduledSession session = new ScheduledSession();
        session.setToTime("2013-03-27 12:30:00.0");

        assertThat(session.getToHour()).isEqualTo("12:30");
    }

    @Test
    public void durationMinute_should_return_duration_in_minute() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:30:00.0");
        session.setToTime("2013-03-27 12:30:00.0");

        assertThat(session.getDurationMinute()).isEqualTo("180");
    }
}
