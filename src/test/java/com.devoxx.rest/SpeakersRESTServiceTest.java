package com.devoxx.rest;

import com.devoxx.controller.DevoxxJSONReader;
import com.devoxx.ejb.DevoxxReaderBean;
import com.devoxx.model.Speaker;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@RunWith(Arquillian.class)
public class SpeakersRESTServiceTest {

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Speaker.class.getPackage())
                .addPackage(DevoxxReaderBean.class.getPackage())
                .addPackage(DevoxxJSONReader.class.getPackage())
                .addPackage(SpeakersRESTService.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Test
    public void can_read_speakers() throws Exception {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8080).path("test/rest/speakers").build();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri.toString());

        HttpResponse response = httpclient.execute(httpGet);

        // no speakers loaded
        Assert.assertEquals("HTTP/1.1 204 No Content", response.getStatusLine().toString());
    }
}
