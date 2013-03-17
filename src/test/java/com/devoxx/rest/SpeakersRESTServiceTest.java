package com.devoxx.rest;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.ejb.DevoxxFakeCache;
import com.devoxx.model.json.Speaker;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.fest.assertions.api.Assertions;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

@RunWith(Arquillian.class)
public class SpeakersRESTServiceTest {

    
    @ArquillianResource
    URL deploymentURL;

    public static File[] getLibsFromArtifactIdAndGroup(String... mvnDef) {
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        return resolver.artifacts(mvnDef).resolveAsFiles();
    }
	
    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, SpeakersRESTServiceTest.class.getName() + ".war")
                .addPackage(Speaker.class.getPackage())
                .addClass(DevoxxCache.class)
                .addClass(DevoxxFakeCache.class)
                .addClass(JaxRsActivator.class)
                .addClass(SpeakersRESTService.class)
                .addAsLibraries(getLibsFromArtifactIdAndGroup("com.sun.jersey:jersey-json"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void can_read_speakers() throws Exception {
        URI uri = UriBuilder.fromUri(deploymentURL.toURI()).path("/rest/speakers").build();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri.toString());

        HttpResponse response = httpclient.execute(httpGet);

        // no speakers loaded
        Assert.assertEquals("HTTP/1.1 204 No Content", response.getStatusLine().toString());
    }

    @Test
    public void can_read_speaker() throws Exception {
        URI uri = UriBuilder.fromUri(deploymentURL.toURI()).path("/rest/speakers/1").build();
        System.out.println("URI" + uri.toString());
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri.toString());

        HttpResponse response = httpclient.execute(httpGet);

        // 1 speaker found
        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine().toString());
        InputStream content = response.getEntity().getContent();
        String json = CharStreams.toString(new InputStreamReader(content, Charsets.UTF_8));
        JSONAssert.assertEquals("{\"id\":1,\"bio\":null,\"company\":null,\"imageURI\":null,\"firstName\":\"Steve\",\"lastName\":\"Jobs\",\"tweethandle\":null,\"talks\":null}", json, false);
    }
}
