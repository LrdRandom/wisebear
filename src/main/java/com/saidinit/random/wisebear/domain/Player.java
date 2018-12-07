package com.saidinit.random.wisebear.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import lombok.Data;

@Data
public class Player implements Comparable<Player>{

	private int dci;
	private String name;
	private int tableNumber;
	private List<Player> oponents;
	private int points;
	private float adjustmentPlayerMatchWin;
	private float playerGameWin;
	private float opponentMatchWin;
	private int orderRegister;
	private boolean hadTournamentBye;
	private boolean dropped;
	private List<Penalitiy> penalities;
	
	@Override
	public int compareTo(Player player) {
		int tournamentPoints =  this.points - player.getPoints();
		if(tournamentPoints != 0 ) {
			return tournamentPoints;
		}
		else{
			Float omw =this.opponentMatchWin - player.getOpponentMatchWin();
			if(omw != 0f) {
				return omw.intValue();
			}
			else {
				Float pgw = this.playerGameWin - player.getPlayerGameWin();
				if(pgw != 0f) {
					return pgw.intValue();
				}
				else {
					//we calculate OGW
					Float ogw = calculateOgw(player);
					if(ogw != 0f) {
						return ogw.intValue();
					}
					else {
						return this.orderRegister - player.getOrderRegister();
					}
				}
			}
		}
		
	}

	private float calculateOgw(Player player) {
		
		List<Float> oponentGameWins = new ArrayList<>();
		
		for(Player p : player.getOponents()) {
			float tempPgw = p.getPlayerGameWin();
			if(tempPgw < 33f) {
				tempPgw = 33f;
			}
			oponentGameWins.add(tempPgw);
		}
		
		OptionalDouble average = oponentGameWins
				.stream()
				.mapToDouble(a -> a)
				.average();
		
		return average.isPresent() ? ((Double)average.getAsDouble()).floatValue() : 0f;
	}
}
