package com.devoxx.ejb;

import com.devoxx.model.Speaker;
import com.devoxx.model.Talk;

public interface DevoxxCache {

    Speaker[] getSpeakers();

    Speaker getSpeaker(String id);

    Talk[] getTalks();

    void loadData();

    Talk getTalk(String id);
}
