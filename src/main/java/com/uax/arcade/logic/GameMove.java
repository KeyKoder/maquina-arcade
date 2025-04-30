package com.uax.arcade.logic;

import com.uax.arcade.logic.hanoi.Tower;

public class GameMove<T> {
	protected T from;
	protected T to;

	public GameMove(T from, T to) {
		this.from = from;
		this.to = to;
	}

	public GameMove(GameMove<T> gm) {
		this.from = gm.from;
		this.to = gm.to;
	}

	public T getFrom() {
		return from;
	}

	public T getTo() {
		return to;
	}
}
