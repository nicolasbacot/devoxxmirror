package com.devoxx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.devoxx.model.ScheduledRoom;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.devoxx.model.ScheduledSession;
import com.devoxx.model.Speaker;
import com.devoxx.model.Talk;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DevoxxJSONReader {

	private final static String URL_SPEAKERS = "https://cfp.devoxx.com/rest/v1/events/8/speakers";
	private final static String URL_TALKS = "http://cfp.devoxx.com/rest/v1/events/8/presentations";
	
	private final static int NB_DAYS = 3;
	private final static String URL_SCHEDULE_DAY = "https://cfp.devoxx.com/rest/v1/events/8/schedule/day/";
    private final static String URL_SCHEDULE_ROOMS = "https://cfp.devoxx.com/rest/v1/events/8/schedule/rooms";

	public static Talk[] readTalks() {
		return loadFromURL(Talk[].class, URL_TALKS);
	}

	public static Speaker[] readSpeakers() {
		return loadFromURL(Speaker[].class, URL_SPEAKERS);
	}
	
	public static ScheduledSession[][] readSchedule(){
		ScheduledSession[][] out = new ScheduledSession[NB_DAYS][];
		for (int i = 0 ; i < NB_DAYS ; i++){
			out[i] = loadFromURL(ScheduledSession[].class, URL_SCHEDULE_DAY + String.valueOf(i + 1));
		}
		return out;
	}

    public static ScheduledRoom[] readRooms() {
        return loadFromURL(ScheduledRoom[].class, URL_SCHEDULE_ROOMS);
    }

    private static InputStream getURLInputStream(String url) throws IOException, ClientProtocolException {
        InputStream out = null;
        HttpGet get = new HttpGet(url);
        HttpResponse response;
        DefaultHttpClient client = new DefaultHttpClient();
        response = client.execute(get);
        try {
            out = response.getEntity().getContent();
        } catch (Exception ex) {
            Logger.getLogger(DevoxxJSONReader.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return out;
    }

	static <T> T loadFromInputStream(Class<T> type, InputStream is) {
		T out = null;
		InputStreamReader inputStreamReader = null;
		inputStreamReader = new InputStreamReader(is);

		if (is != null) {
			Gson gson = new Gson();
			JsonReader jsonReader = new JsonReader(inputStreamReader);
			out = gson.fromJson(jsonReader, type);
		}

		if (is != null) {
			try {
				inputStreamReader.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out;
	}

	private static <T> T loadFromURL(Class<T> type, String url) {
		T out = null;
		try {
			out = loadFromInputStream(type, getURLInputStream(url));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

}
