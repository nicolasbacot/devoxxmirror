package com.devoxx.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;
import static org.fest.assertions.api.Assertions.filter;

import org.junit.Ignore;
import org.junit.Test;

import com.devoxx.model.json.EmbeddedSpeaker;
import com.devoxx.model.json.ScheduledSession;
import com.devoxx.model.json.Speaker;

public class DevoxxJSONReaderTest {

	@Test
	public void test_readSpeakers() {
		Speaker[] result = DevoxxJSONReader.readSpeakers();
		assertNotNull(result);
		assertTrue(result.length > 0);
	}

	@Test
	public void test_loadFromURL_schedule() {
		ScheduledSession[] result = DevoxxJSONReader.loadFromInputStream(
				ScheduledSession[].class, this.getClass().getClassLoader()
						.getResourceAsStream("json/schedule.json"));
		
		assertThat(result).isNotNull();
		assertThat(extractProperty("id").from(result)).contains(1129L, 1132L);
		
		
		ScheduledSession expectedBreakFast = fillBreakfast();
		ScheduledSession loadedBreakfast = filter(result).with("id", 1129L).get().iterator().next();
		assertThat(loadedBreakfast).isEqualsToByComparingFields(expectedBreakFast);

	
		ScheduledSession expectedSession = fillSession();
		ScheduledSession loadedSession = filter(result).with("id", 1132L).get().iterator().next();
		assertThat(loadedSession).isLenientEqualsToByIgnoringFields(expectedSession, "speakers");
		assertThat(loadedSession.getSpeakers().length).isEqualTo(1);
		assertThat(loadedSession.getSpeakers()[0]).isEqualsToByComparingFields(expectedSession.getSpeakers()[0]);
		
	}

	private ScheduledSession fillBreakfast() {
		ScheduledSession out = new ScheduledSession();
		out.setCode("Breakfast");
		out.setFromTime("2013-03-28 07:30:00.0");
		out.setToTime("2013-03-28 09:00:00.0");
		out.setId(1129L);
		out.setKind("Breakfast");
		out.setPartnerSlot(false);
		out.setRoom("Hall d'exposition");
		out.setType("Conference");
		return out;
	}
	
	private ScheduledSession fillSession(){
		ScheduledSession out = new ScheduledSession();
		out.setSpeaker("Martin Odersky");
		out.setPresentationUri("https://cfp.devoxx.com/rest/v1/events/presentations/2936");
		out.setCode("Keynote");
		out.setFromTime("2013-03-28 09:25:00.0");
		out.setPartnerSlot(false);
		out.setType("Conference");
		out.setKind("Keynote");
		out.setToTime("2013-03-28 10:05:00.0");
		out.setId(1132L);
		out.setTitle("Objects and functions, conflict without a cause?");
		out.setSpeakerUri("https://cfp.devoxx.com/rest/v1/events/speakers/304");
		out.setSpeakerId(304L);
		out.setPresentationId(2936L);
		out.setRoom("La Seine A");
		
		EmbeddedSpeaker[] speakers = new EmbeddedSpeaker[1];
		EmbeddedSpeaker speaker = new EmbeddedSpeaker();
		speaker.setSpeakerId(304L);
		speaker.setSpeaker("Martin Odersky");
		speaker.setSpeakerUri("https://cfp.devoxx.com/rest/v1/events/speakers/304");
		speakers[0] = speaker;
		out.setSpeakers(speakers);
		return out;
	}

}
