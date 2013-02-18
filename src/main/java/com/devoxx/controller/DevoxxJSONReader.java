package com.devoxx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.devoxx.model.Speaker;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DevoxxJSONReader {

	private final static String URL_SPEAKERS = "https://cfp.devoxx.com/rest/v1/events/8/speakers";
	
    private static InputStream getURLInputStream(String url) throws IOException, ClientProtocolException {
        InputStream out = null;
    	HttpGet get = new HttpGet(url);
        HttpResponse response;
        DefaultHttpClient client = new DefaultHttpClient();
        response = client.execute(get);
        try {
            out = response.getEntity().getContent();
        } catch (Exception ex) {
            Logger.getLogger(DevoxxJSONReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

	
	
    public static Speaker[] readSpeakers() {
        Speaker[]  out = null;
        InputStream is = null;
        InputStreamReader inputStreamReader = null;
		try {
			is = getURLInputStream(URL_SPEAKERS);
	        inputStreamReader = new InputStreamReader(is);

	        if (is != null) {
	            Gson gson = new Gson();
				JsonReader jsonReader = new JsonReader(inputStreamReader);
	            out = gson.fromJson(jsonReader, Speaker[].class);
	        }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null){
	            try {
					inputStreamReader.close();
		            is.close();
	            } catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return out;
    }

	
}
