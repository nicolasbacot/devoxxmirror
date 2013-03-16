package com.devoxx.model.json;

public class TalkPoll {

    private Long talkdId;
    private int nbPolls;

    public TalkPoll() {
    }

    public TalkPoll(Long talkdId, int nbPolls) {
        this.talkdId = talkdId;
        this.nbPolls = nbPolls;
    }

    public Long getTalkdId() {
        return talkdId;
    }

    public void setTalkdId(Long talkdId) {
        this.talkdId = talkdId;
    }

    public int getNbPolls() {
        return nbPolls;
    }

    public void setNbPolls(int nbPolls) {
        this.nbPolls = nbPolls;
    }
}
