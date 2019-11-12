package com.jobsity.bowling.entity;

/**
 * Each chance done by the player to knock down pins.
 * 
 * @author crodriguez
 * @since 06/04/2019
 */
public class Pinfall {

	private final int pinsKnockedDown;
	private final boolean foul;

	private Pinfall(Builder builder) {
		pinsKnockedDown = builder.pinsKnockedDown;
		foul = builder.foul;
	}

	public int getPinsKnockedDown() {
		return pinsKnockedDown;
	}

	public boolean isFoul() {
		return foul;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pinsKnockedDown;
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
		Pinfall other = (Pinfall) obj;
		if (pinsKnockedDown != other.pinsKnockedDown)
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Pinfall pinfall) {
		return new Builder(pinfall);
	}

	public static class Builder {

		private int pinsKnockedDown;
		private boolean foul;

		private Builder() {
		}

		private Builder(Pinfall pinfall) {
			pinsKnockedDown(pinfall.pinsKnockedDown).foul(pinfall.foul);
		}

		public Builder pinsKnockedDown(int pinsKnockedDown) {
			this.pinsKnockedDown = pinsKnockedDown;
			return this;
		}

		public Builder foul(boolean foul) {
			this.foul = foul;
			return this;
		}

		public Pinfall build() {
			return new Pinfall(this);
		}
	}
}