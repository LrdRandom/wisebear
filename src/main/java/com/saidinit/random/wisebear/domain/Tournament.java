package com.saidinit.random.wisebear.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tournament {

	private List<Round> rounds;
	private List<Judge> judges;
	private List<Player> players;
}
