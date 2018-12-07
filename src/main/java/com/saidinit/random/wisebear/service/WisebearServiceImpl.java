package com.saidinit.random.wisebear.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.saidinit.random.wisebear.domain.Info;
import com.saidinit.random.wisebear.domain.Player;
import com.saidinit.random.wisebear.domain.Round;

public class WisebearServiceImpl implements WisebearService {

	@Override
	public Info getInfo() {
		return Info.builder()
				.info("Doviandi setovia sagain")
				.build();
	}

	
	/** Pseudocode for Backtracking algorithm
	 * root(P): return the partial candidate at the root of the search tree.
	 * reject(P,c): return true only if the partial candidate c is not worth completing.
	 * accept(P,c): return true if c is a solution of P, and false otherwise.
	 * first(P,c): generate the first extension of candidate c.
	 * next(P,s): generate the next alternative extension of a candidate, after the extension s.
  	 * output(P,c): use the solution c of P, as appropriate to the application.
	 * */
	@Override
	public Round calculateRound(List<Player> players) {
		
		// order players by highest score
		//maybe call orderPlayers if we do not want to alter the existing list
		Collections.sort(players);
		
		// separate players by points
		
		// float player down
		
		// pair players with table number
		
		// repeat if necessary 
		// XXX: no clue what to change... maybe float up?
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Player> orderPlayers(List<Player> players){
		List<Player> orderedPlayers = new ArrayList<>();
		orderedPlayers.addAll(players);
		Collections.sort(orderedPlayers);
		return orderedPlayers;
	}

}
