package com.devoxx.model;

import org.junit.Test;

import com.devoxx.model.json.ScheduledSession;

import static org.fest.assertions.api.Assertions.assertThat;

public class ScheduleSessionTest {

    @Test
    public void fromHour_should_return_hour_minute() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:30:00.0");
        
        assertThat(session.getFromHour()).isEqualTo("9:30");
    }

    @Test
    public void fromHour_should_return_hour_minute_with_zero() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:00:00.0");

        assertThat(session.getFromHour()).isEqualTo("9:00");
    }

    @Test
    public void toHour_should_return_hour_minute() {
        ScheduledSession session = new ScheduledSession();
        session.setToTime("2013-03-27 12:30:00.0");

        assertThat(session.getToHour()).isEqualTo("12:30");
    }

    @Test
    public void toHour_should_return_hour_minute_with_zero() {
        ScheduledSession session = new ScheduledSession();
        session.setToTime("2013-03-27 12:05:00.0");

        assertThat(session.getToHour()).isEqualTo("12:05");
    }

    @Test
    public void durationMinute_should_return_duration_in_minute() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:30:00.0");
        session.setToTime("2013-03-27 12:30:00.0");

        assertThat(session.getDurationMinute()).isEqualTo("180");
    }

    @Test
    public void durationHour_should_return_duration_in_hour() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:30:00.0");
        session.setToTime("2013-03-27 12:30:00.0");

        assertThat(session.getDurationHour()).isEqualTo("3.00000");
    }

    @Test
    public void durationHour_should_return_duration_in_hour_with_decimal() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 09:30:00.0");
        session.setToTime("2013-03-27 10:00:00.0");

        assertThat(session.getDurationHour()).isEqualTo("0.50000");
    }

    @Test
    public void durationHour_should_return_duration_in_hour_with_5decimal() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 10:10:00.0");
        session.setToTime("2013-03-27 10:45:00.0");

        assertThat(session.getDurationHour()).isEqualTo("0.58333");
    }

    @Test
    public void startHour_should_return_0_from_start() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 07:00:00.0");

        assertThat(session.getStartHour()).isEqualTo("0.00000");
    }

    @Test
    public void startHour_should_return_duration_from_start() {
        ScheduledSession session = new ScheduledSession();
        session.setFromTime("2013-03-27 10:00:00.0");

        assertThat(session.getStartHour()).isEqualTo("3.00000");
    }
}
