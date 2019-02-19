package com.saidinit.random.wisebear.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Each round has p/2 matches (rounded up) 
 * */
@Data
@Builder
public class Round {

	private List<Match> matches;
	private int roundNumber;
}
