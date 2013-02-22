package com.devoxx.ejb;

import com.devoxx.model.Speaker;

public interface DevoxxCache {

    Speaker[] getSpeakers();

    Speaker getSpeaker(String id);

}
