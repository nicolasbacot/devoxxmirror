package com.devoxx.model.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Talk")
public class TalkEntity {

	
	@Id
	private Long id;

	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	private List<PollEntity> listOfPolls;

	public Long getId() {
		return id;
	}

	public List<PollEntity> getListOfPolls() {
		return listOfPolls;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void addPoll(PollEntity poll){
		if (listOfPolls ==null){
			listOfPolls = new ArrayList<PollEntity>();
		} 
		listOfPolls.add(poll);
	}
	
}
