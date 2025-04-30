package com.uax.arcade.logic.hanoi;

import com.uax.arcade.logic.Game;
import com.uax.arcade.logic.GameMove;

import java.util.ArrayList;
import java.util.List;

/**
 * Los parámetros para lanzar el juego "Torres de Hanoi" son los siguientes:
 * <br>N -> Número de discos
 */
public class Hanoi extends Game<Tower> {
	private int n;
	private List<GameMove<Tower>> moves = new ArrayList<GameMove<Tower>>();

	public Hanoi() {
		super(1);
	}

	@Override
	protected void initialize(Object... params) {
		this.n = (Integer) params[0];
	}

	@Override
	protected void solve() {
		solveHanoi(this.n, Tower.LEFT, Tower.RIGHT, Tower.MIDDLE);
		this.addSolution(this.moves);
	}

	private void solveHanoi(int disc, Tower from, Tower to, Tower aux) {
		if (disc == 1) {
			this.moves.add(new HanoiMove(disc, from, to));
			return;
		}
		solveHanoi(disc - 1, from, aux, to);
		this.moves.add(new HanoiMove(disc, from, to));
		solveHanoi(disc - 1, aux, to, from);
	}
}
