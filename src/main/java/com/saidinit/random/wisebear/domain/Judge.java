package com.saidinit.random.wisebear.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;


/**
 * Class for judges. Judges should state how many languages they speak, should be helpful for queries on players.
 * */
@Data
@Builder
public class Judge {
	
	private int id;
	private String name;
	private String surname;
	private String country;
	private List<LanguageModel> spokenLanguages;
	private List<Penalitiy> penalties;
	private int level;
	
}
