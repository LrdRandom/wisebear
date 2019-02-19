package com.saidinit.random.wisebear.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saidinit.random.wisebear.domain.Info;
import com.saidinit.random.wisebear.domain.Match;
import com.saidinit.random.wisebear.domain.Player;
import com.saidinit.random.wisebear.domain.Round;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WisebearServiceImpl implements WisebearService {

	// we are going to need a repository here, probably
	
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
	public Round calculateRound(List<Player> players, int nextRound) {
		
		List<Match> matches = new ArrayList<>();
		// order players by highest score
		//maybe call orderPlayers if we do not want to alter the existing list
		//TODO: only sort if they are after round 1, on round 1 we want to randomize the order
		Collections.sort(players);
		
		List<Player> auxPlayers = new ArrayList<>();
		auxPlayers.addAll(players);
		
		for(int i = 0 ; i<players.size()-1; i ++) {
			Player p = players.get(i);
			if(auxPlayers.contains(p)) {
				//player has not yet been put in a match
				auxPlayers.remove(p);
				Match m = null;
				for(Player p2 : auxPlayers) {
					// find an oponent player hasn't played against already
					if(p.getOponents().contains(p)) {
						continue;
					}
					else {
						m = Match.builder()
								.p1(p)
								.p2(p2)
								.table(i+1)
								.build();
						break;
					}
				}
				if(m != null) {
					//remove both players from being elected for another match this round
					auxPlayers.remove(m.getP1());
					auxPlayers.remove(m.getP2());
				} else {
					//algorithm is broken, we have to retry with floating
					// TODO: think about this
				}
				matches.add(m);
				
			}
		}
		if(auxPlayers.size() != 0) {
			// something has gone wrong, we need to redo
			// TODO: same as before
		}
		
		Round round = Round.builder()
				.matches(matches)
				.roundNumber(nextRound)
				.build();
		return round;
		
		// separate players by points
		
		// float player down
		
		// pair players with table number
		
		// repeat if necessary 
		// XXX: no clue what to change... maybe float up?
		
		
	}
	
	private List<Player> orderPlayers(List<Player> players){
		List<Player> orderedPlayers = new ArrayList<>();
		orderedPlayers.addAll(players);
		Collections.sort(orderedPlayers);
		return orderedPlayers;
	}

}
