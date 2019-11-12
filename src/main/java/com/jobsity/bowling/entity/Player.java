package com.jobsity.bowling.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Player participating in the bowling game.
 * 
 * @author crodriguez
 * @since 06/04/2019
 */
public class Player {
	
	private final String name;
	private final List<Frame> frames;
	
	private Player(Builder builder) {
		name = builder.name;
		frames = builder.frames;
	}
	
	public String getName() {
		return name;
	}

	public List<Frame> getFrames() {
		return frames;
	}
	
	public void addFrame(Frame frame) {
		frames.add(frame);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frames == null) ? 0 : frames.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Player other = (Player) obj;
		if (frames == null) {
			if (other.frames != null)
				return false;
		} else if (!frames.equals(other.frames))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Player player) {
		return new Builder(player);
	}

	public static class Builder {
		
		private String name;
		private List<Frame> frames = new ArrayList<>();
		
		private Builder() {
		}
		
		private Builder(Player player) {
			name(player.name).frames(player.frames);
		}
		
		private Builder frames(Collection<Frame> frames) {
			this.frames = new ArrayList<>(frames);
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder frame(Frame frame) {
			this.frames.add(frame);
			return this;
		}
		
		public Player build() {
			return new Player(this);
		}
	}
}