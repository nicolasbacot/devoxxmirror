package com.devoxx.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.devoxx.model.jpa.PollEntity;
import com.devoxx.model.json.TalkPoll;
import org.jboss.resteasy.annotations.cache.Cache;

import com.devoxx.ejb.DevoxxCache;
import com.devoxx.ejb.TalkEJB;
import com.devoxx.model.jpa.TalkWithPollResult;
import com.devoxx.model.json.Talk;

@Path("/talks")
@Produces("application/json")
public class TalksRESTService {

	@Inject
	private DevoxxCache devoxxCache;

	@Inject
	private TalkEJB talkEJB;

	@GET
	@Cache(maxAge = 3600)
	public Talk[] getTalks() {
		return devoxxCache.getTalks();
	}

	@GET
	@Path("{id}")
	@Cache(maxAge = 3600)
	public Talk getTalk(@PathParam("id") String id) {
		return devoxxCache.getTalk(id);
	}

	@POST
	@Path("{id}/poll")
	public void pollOnTalk(@PathParam("id") String id) {
		Talk talk = new Talk();
		talk.setId(Long.valueOf(id));
		talkEJB.pollOnTalk(talk);
	}

    @GET
    @Path("{id}/poll")
    public TalkPoll pollAndGetTalkPoll(@PathParam("id") String id) {
        Talk talk = new Talk();
        talk.setId(Long.valueOf(id));
        int poll = talkEJB.pollOnTalk(talk);
        return new TalkPoll(talk.getId(), poll);
    }

    @GET
    @Path("{id}/pollsize")
    public TalkPoll getTalkPoll(@PathParam("id") String id) {
        Talk talk = new Talk();
        talk.setId(Long.valueOf(id));
        int poll = talkEJB.getPollSize(talk);
        return new TalkPoll(talk.getId(), poll);
    }

	@GET
	@Path("/top/{nb}")
	public List<Talk> getTopTalks(@PathParam("nb") String nb) {
		List<Talk> out = new ArrayList<Talk>();
		List<TalkWithPollResult> topTalks = talkEJB.getTopTalks(Integer
				.valueOf(nb));
		Talk curTalk = null;
		for (TalkWithPollResult curTalkResult : topTalks) {
			if (curTalkResult.getTalkId() != null) {
				curTalk = devoxxCache.getTalk(String.valueOf(curTalkResult
						.getTalkId()));
				if (curTalk != null) {
					curTalk.setNbVotes(curTalkResult.getNbPolls());
					out.add(curTalk);
				}
			}
		}
		return out;
	}

}
