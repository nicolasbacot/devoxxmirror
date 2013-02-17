package com.devoxx.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.devoxx.model.Speaker;


public class DevoxxJSONReaderTest {

	@Test
	public void test_readSpeakers(){
		Speaker[] result = DevoxxJSONReader.readSpeakers();
		assertNotNull(result);
		assertTrue(result.length > 0);
	}
	
}
