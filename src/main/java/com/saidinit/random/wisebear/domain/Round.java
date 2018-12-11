package com.saidinit.random.wisebear.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Round {

	private List<Match> matches;
	int roundNumber;
}
