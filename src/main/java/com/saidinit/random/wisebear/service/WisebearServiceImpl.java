package com.saidinit.random.wisebear.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.saidinit.random.wisebear.domain.Info;
import com.saidinit.random.wisebear.domain.Match;
import com.saidinit.random.wisebear.domain.Player;
import com.saidinit.random.wisebear.domain.Round;

import lombok.AllArgsConstructor;

/**
 * Wisebear functionality of creating matches and tournaments
 * 
 * @author Random.
 * */
@Service
@AllArgsConstructor
public class WisebearServiceImpl implements WisebearService {

	// we are going to need a repository here, probably
	
	private static final int FIRST_ROUND = 1;

	@Override
	public Info getInfo() {
		return Info.builder()
				.info("Dovie'andi se tovya sagain")
				.build();
	}

	/**
	 * TODO: Explain algorithm
	 * */
	@Override
	public Round calculateRound(List<Player> players, int nextRound) {
		
		Random random = new Random(System.currentTimeMillis());
		
		List<Match> matches = new ArrayList<>();
		// order players by highest score
		//maybe call orderPlayers if we do not want to alter the existing list
		
		if(nextRound == FIRST_ROUND) {
			Collections.shuffle(players, random);
		}
		else {
			Collections.sort(players);	
		}

		
		// divide players by points
		List<List<Player>> playersByPoints = new ArrayList<>();
		List<Player> playersWithSamepoints = new ArrayList<>();
		for(int i = 0 ; i<players.size(); i ++) {
			Player p = players.get(i);
			if((playersWithSamepoints.isEmpty()) || (playersWithSamepoints.get(0).getPoints() == p.getPoints()) ) {
				playersWithSamepoints.add(p);
			}
			else {
				playersByPoints.add(new ArrayList<Player>(playersWithSamepoints));
				playersWithSamepoints = new ArrayList<>();
			}
		}
		List<Player> floatingPlayers = new ArrayList<>();
		int tableNum = 1;
		for(int i=0; i<playersByPoints.size(); i++) {
			List<Player> samePoints = playersByPoints.get(i);
			samePoints.addAll(floatingPlayers);
			List<Player> auxPlayers = new ArrayList<>();
			Collections.shuffle(samePoints, random);
			auxPlayers.addAll(samePoints);
			floatingPlayers = new ArrayList<>();
			
			for(int j=0;j<samePoints.size();j++) {
				if(auxPlayers.size()==0) {
					break;
				}
				Player p1 = samePoints.get(j);
				// could not be there anymore, the player has already been processed
				if(auxPlayers.contains(p1)) {
					auxPlayers.remove(p1);
					boolean foundOponent = false;
					for(Player p2 : auxPlayers) {
						if(p1.getOponents().contains(p2)) {
							continue;
						}
						else {
							Match m = Match.builder()
									.p1(p1)
									.p2(p2)
									.table(tableNum)
									.build();
							matches.add(m);
							tableNum++;
							foundOponent = true;
							auxPlayers.remove(p2);
							p1.getOponents().add(p2);
							p2.getOponents().add(p1);
							break;
						}
					}
					if(!foundOponent) {
						// the player is a floating player and needs to be added to the next list
						floatingPlayers.add(p1);
					}
				}
			}
		}
		// by here we should have all the players matched it is possible that the system breaks, since it could leave floating players
		// we will break rule#1 if that is the case, and the bottom players will play against each other again
		// turns out, this function can also be called if we have a bye!
		if(floatingPlayers.size()!=0) {
			matchFloatingPlayers(matches, floatingPlayers, tableNum);
		}

		
		return Round.builder()
				.matches(matches)
				.roundNumber(nextRound)
				.build();
	}
	
	/**
	 * we match the rest of the floating players, this function should never been called and it is a safeFail
	 * for when the algorithm cannot cope with a correct round matching for the players
	 * 
	 * @since we can also have byes, turns out this function is called more than expected
	 * */
	private Player matchFloatingPlayers(List<Match> matches, List<Player> floatingPlayers, int tableNum) {
		Player byePlayer = null;
		if(floatingPlayers.size()%2 != 0) {
			for(int i=0;i<floatingPlayers.size();i++) {
				Player p1 = floatingPlayers.get(i);
				if(!p1.isHadTournamentBye()) {
					byePlayer = p1;
					byePlayer.setHadTournamentBye(true);
					break;
					//XXX: if all of them had a bye, we are in trouble...
					//TODO: calculate this at the BEGGINING of the algorith, that way you get it out of the way and not treat it as an afterthought.
					// also means that again this function is only a safe fail and we can log it as such
				}
			}
		} 
		floatingPlayers.remove(byePlayer);
		//this is the actual bad case, no choice but to pair them together again 
		for(int i=0;i<floatingPlayers.size();i++) {
			Player p1 = floatingPlayers.get(i);
			i++;
			Player p2 = floatingPlayers.get(i);
			Match m = Match.builder()
					.p1(p1)
					.p2(p2)
					.table(tableNum)
					.build();
			matches.add(m);
			tableNum++;
		}
		return byePlayer;
	}
}
