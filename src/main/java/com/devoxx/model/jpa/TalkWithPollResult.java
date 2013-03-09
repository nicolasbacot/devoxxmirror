package com.devoxx.model.jpa;

public class TalkWithPollResult {

	private Long talkId;

	private Long nbPolls;

	public TalkWithPollResult(Long nbPolls,Long talkId) {
		this.talkId = talkId;
		this.nbPolls = nbPolls;
	}

	public Long getTalkId() {
		return talkId;
	}

	public Long getNbPolls() {
		return nbPolls;
	}

}
