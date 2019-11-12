package com.jobsity.bowling.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Each one of ten scoring units constituting a game, each including one or two
 * rolls (or three in 10th frame) depending on pin fall.
 * 
 * @author crodriguez
 * @since 06/04/2019
 */
public class Frame {

	private final int id;
	private final List<Pinfall> pinfalls;
	private int score;

	private Frame(Builder builder) {
		id = builder.id;
		pinfalls = builder.pinfalls;
	}

	public int getId() {
		return id;
	}

	public List<Pinfall> getPinfalls() {
		return pinfalls;
	}

	public void addPinfall(Pinfall pinfall) {
		pinfalls.add(pinfall);
	}

	public int getPinsKnockedDown(boolean allAttempts) {
		return (allAttempts)
				? pinfalls.stream().map(Pinfall::getPinsKnockedDown).collect(Collectors.summingInt(Integer::intValue))
				: pinfalls.get(0).getPinsKnockedDown()
						+ ((pinfalls.size() > 1) ? pinfalls.get(1).getPinsKnockedDown() : 0);
	}

	public int getPinsKnockedDownFirstAttempt() {
		return pinfalls.size() >= 1 ? pinfalls.get(0).getPinsKnockedDown() : 0;
	}

	public boolean isStrike() {
		return getPinsKnockedDownFirstAttempt() == 10;
	}

	public boolean isSpare() {
		return pinfalls.size() >= 2
				&& (pinfalls.get(0).getPinsKnockedDown() + pinfalls.get(1).getPinsKnockedDown()) == 10;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Frame other = (Frame) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Frame frame) {
		return new Builder(frame);
	}

	public static class Builder {

		private int id;
		private List<Pinfall> pinfalls = new ArrayList<>();

		private Builder() {
		}

		private Builder(Frame frame) {
			id(frame.id).pinfalls(frame.pinfalls);
		}

		private Builder pinfalls(Collection<Pinfall> pinfalls) {
			this.pinfalls = new ArrayList<>(pinfalls);
			return this;
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder pinfall(Pinfall pinfall) {
			pinfalls.add(pinfall);
			return this;
		}

		public Frame build() {
			return new Frame(this);
		}
	}
}