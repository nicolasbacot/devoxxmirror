package com.devoxx.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

import com.devoxx.model.jpa.PollEntity;
import com.devoxx.model.jpa.TalkEntity;
import com.devoxx.model.jpa.TalkEntity_;
import com.devoxx.model.jpa.TalkWithPollResult;
import com.devoxx.model.json.Talk;

@Stateless
public class TalkEJB {

	@PersistenceContext
	EntityManager em;

	public int pollOnTalk(Talk talk) {
		TalkEntity managedTalk = em.find(TalkEntity.class, talk.getId());
		if (managedTalk == null) {
			managedTalk = new TalkEntity();
			managedTalk.setId(talk.getId());
			managedTalk = createTalk(managedTalk);
		}
		PollEntity poll = new PollEntity();
		em.persist(poll);
		managedTalk.addPoll(poll);
		em.merge(managedTalk);
		return managedTalk.getListOfPolls().size();
	}

	public int getPollSize(Talk talk) {
		TalkEntity managedTalk = em.find(TalkEntity.class, talk.getId());
		return managedTalk == null ? 0 : managedTalk.getListOfPolls().size();
	}

	public TalkEntity createTalk(TalkEntity talk) {
		if (talk != null) {
			em.persist(talk);
		}
		return talk;
	}

	public List<TalkWithPollResult> getTopTalks() {
		return getTopTalks(-1);
	}

	public List<TalkWithPollResult> getTopTalks(int nbTalks) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TalkWithPollResult> cq = cb
				.createQuery(TalkWithPollResult.class);
		Root<TalkEntity> from = cq.from(TalkEntity.class);
		ListJoin<TalkEntity, PollEntity> polls = from.join(
				TalkEntity_.listOfPolls, JoinType.INNER);
		cq.multiselect(cb.count(polls), from.get(TalkEntity_.id));
		cq.groupBy(from.get(TalkEntity_.id));
		cq.orderBy(cb.desc(cb.count(polls)));
		Query q = em.createQuery(cq);
		if (nbTalks > 0) {
			q.setMaxResults(nbTalks);
		}
		@SuppressWarnings("unchecked")
		List<TalkWithPollResult> out = q.getResultList();
		return out;
	}

	public void deleteAllPolls() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TalkEntity> cq = cb.createQuery(TalkEntity.class);
		cq.from(TalkEntity.class);
		Query q = em.createQuery(cq);

		List<TalkEntity> results = q.getResultList();
		for (TalkEntity talkEntity : results) {
			talkEntity.getListOfPolls().clear();
			em.persist(talkEntity);
		}
	}
}
