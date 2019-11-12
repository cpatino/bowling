package com.jobsity.bowling.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

	private final List<Player> players;

	public Game(Builder builder) {
		players = builder.players;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Game game) {
		return new Builder(game);
	}

	public static class Builder {

		private List<Player> players = new ArrayList<>();

		private Builder() {
		}

		private Builder(Game game) {
			players(game.players);
		}

		private Builder players(Collection<Player> players) {
			this.players = new ArrayList<>(players);
			return this;
		}

		public Builder player(Player player) {
			this.players.add(player);
			return this;
		}

		public Game build() {
			return new Game(this);
		}
	}
}