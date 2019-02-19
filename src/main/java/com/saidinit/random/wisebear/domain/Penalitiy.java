package com.saidinit.random.wisebear.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Penalties applied by judges. Not sure how this exactly works...
 * */
@Data
@Builder
public class Penalitiy {
	
	private int level;//XXX: might be an Enum
	private String reason;
	private int judgeApply;
	private int playerReceive;
}
