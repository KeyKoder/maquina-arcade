package com.uax.arcade.logic;

import java.util.List;

public class GameSolution<T> {
	private List<GameMove<T>> moves;

	public GameSolution(List<GameMove<T>> moves) {
		this.moves = moves;
	}

	public GameSolution(GameSolution<T> solution) {
		this.moves = solution.getMoves();
	}

	public List<GameMove<T>> getMoves() {
		return moves;
	}
}
