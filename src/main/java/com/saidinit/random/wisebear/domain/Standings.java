package com.saidinit.random.wisebear.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Standings {

	List<Player> players;
}
