package com.uax.arcade.logic.utils;

public class Tuple<T1, T2> {
	private final T1 _1;
	private final T2 _2;
	public Tuple(T1 _1, T2 _2) {
		this._1 = _1;
		this._2 = _2;
	}

	public T1 get_1() {
		return _1;
	}

	public T2 get_2() {
		return _2;
	}
}
