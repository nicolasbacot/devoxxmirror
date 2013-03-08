package com.devoxx.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.devoxx.model.jpa.PollEntity;
import com.devoxx.model.jpa.TalkEntity;
import com.devoxx.model.json.Talk;

@Stateless
public class TalkEJB {

	@PersistenceContext
	EntityManager em;

	public void pollOnTalk(Talk talk) {
		TalkEntity managedTalk = em.find(TalkEntity.class,talk.getId());
		if (managedTalk == null){
			managedTalk = new TalkEntity();
			managedTalk.setId(talk.getId());
			managedTalk = createTalk(managedTalk);
		} 
		PollEntity poll = new PollEntity();
		em.persist(poll);
		managedTalk.addPoll(poll);
		em.merge(managedTalk);
	}
		
    public TalkEntity createTalk(TalkEntity talk) {
        if (talk != null) {
            em.persist(talk);
        }
        return talk;
    }
}
