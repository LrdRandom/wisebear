package com.saidinit.random.wisebear.service;

import java.util.List;

import com.saidinit.random.wisebear.domain.Info;
import com.saidinit.random.wisebear.domain.Player;
import com.saidinit.random.wisebear.domain.Round;

public interface WisebearService {
	
	Info getInfo();
	
	Round calculateRound(List<Player> players);

}
