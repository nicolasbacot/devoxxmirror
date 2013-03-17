package com.devoxx.ejb;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.devoxx.model.jpa.TalkEntity;
import com.devoxx.model.json.Talk;
import com.devoxx.model.json.TalkBuilder;
import com.devoxx.rest.SpeakersRESTServiceTest;

@RunWith(Arquillian.class)
public class TalkEJBTest {

	@Inject
	TalkEJB talkEJB;

	public static File[] getLibsFromArtifactIdAndGroup(String... mvnDef) {
		MavenDependencyResolver resolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		return resolver.artifacts(mvnDef).resolveAsFiles();
	}

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, TalkEJB.class.getName() + ".war")
				.addPackage(TalkEntity.class.getPackage())
				.addClass(TalkEJB.class)
				.addAsResource("persistence-test.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void pollOnUnexistingTalkShouldCreateTalkAndAddAPoll() throws Exception {
		Long id = System.currentTimeMillis();
		Talk talk = TalkBuilder.talk().withId(id).build();
		talkEJB.pollOnTalk(talk);		
		assertThat(talkEJB.getPollSize(talk)).isEqualTo(1);
		
	}

}
