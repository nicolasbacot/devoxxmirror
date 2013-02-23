package com.devoxx.ejb;

import com.devoxx.model.Speaker;
import com.devoxx.model.Talk;

public class DevoxxFakeCache implements DevoxxCache {

    @Override
    public Speaker[] getSpeakers() {
        return null;
    }

    @Override
    public Speaker getSpeaker(String id) {
        return null;
    }

    @Override
    public Talk[] getTalks() {
        return null;
    }

    @Override
    public void loadData() {
    }

    @Override
    public Talk getTalk(String id) {
        return null;
    }

}
