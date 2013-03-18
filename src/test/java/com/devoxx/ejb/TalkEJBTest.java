package com.devoxx.ejb;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;
import java.util.List;

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
import com.devoxx.model.jpa.TalkWithPollResult;
import com.devoxx.model.json.Talk;
import com.devoxx.model.json.TalkBuilder;

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

	@Test
	public void topTalkShouldReturnTalkWithPoll() throws Exception {
		Long id = System.currentTimeMillis();
		TalkEntity talkWithoutPoll = new TalkEntity();
		talkWithoutPoll.setId(id);
		talkEJB.pollOnTalk(TalkBuilder.talk().withId(id).build());
		assertThat(contains(talkEJB.getTopTalks(),id)).isTrue();
	}

	
	@Test
	public void topTalkShouldNotReturnTalkWithoutPoll() throws Exception {
		Long id = System.currentTimeMillis();
		TalkEntity talkWithoutPoll = new TalkEntity();
		talkWithoutPoll.setId(id);
		talkEJB.createTalk(talkWithoutPoll);
		assertThat(contains(talkEJB.getTopTalks(),id)).isFalse();
	}
	
	private boolean contains(List<TalkWithPollResult> listOfTalkWithPollResult, Long talkId) {
		boolean out = false; 
		int i = 0;
		while(!out && i < listOfTalkWithPollResult.size()){
			if (talkId.equals(listOfTalkWithPollResult.get(i).getTalkId())){
				out = true;
			}
			i++;
		}
		return out;
	}
	
	@Test
	public void deleteAllPollsShouldDeleteAllPolls() throws Exception {
		int nbPollToAdd = 3;
		Long id = System.currentTimeMillis();
		Talk talk = TalkBuilder.talk().withId(id).build();
		for (int i = 0 ;i < nbPollToAdd ; i ++){
			talkEJB.pollOnTalk(talk);					
		}
		
		//TODO replace this with junit-checker
		assertThat(talkEJB.getPollSize(talk)).isGreaterThanOrEqualTo(nbPollToAdd);
		
		talkEJB.deleteAllPolls();
		assertThat(talkEJB.getPollSize(talk)).isEqualTo(0);		
	}

	
}
