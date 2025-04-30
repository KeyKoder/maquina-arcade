package com.uax.arcade.logic.queens;

import com.uax.arcade.logic.Game;
import com.uax.arcade.logic.GameMove;
import com.uax.arcade.logic.utils.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Los parámetros para lanzar el juego "N Reinas" son los siguientes:
 * <br>N -> Tamaño del tablero (N x N) y número de reinas
 */
public class NQueens extends Game<Integer> {
	private int n;
	private int[][] board;

	public NQueens() {
		super(0);
	}

	@Override
	protected void initialize(Object... params) {
		this.n = (Integer) params[0];
		this.board = new int[this.n][this.n];
	}

	@Override
	protected void solve() {
		solveNQueens(0);
	}

	private void solveNQueens(int row) {
		if(row == this.n) {
			List<GameMove<Integer>> moves = new ArrayList<GameMove<Integer>>();
			for(int r = 0; r < this.n; r++) {
				for(int c = 0; c < this.n; c++) {
					if(this.board[r][c] == 1) moves.add(new NQueensMove(r, c));
				}
			}
			this.addSolution(moves);
		}

		List<Vector2> candidates = buildCandidates(row);
		for(Vector2 candidate : candidates) {
			// Si ya esta resuelto, salir antes del bucle para ahorrarnos tiempo
//			if(!this.moves.isEmpty()) break;
			if(candidate == null) continue;

			this.board[candidate.getY()][candidate.getX()] = 1;
			solveNQueens(row + 1);
			this.board[candidate.getY()][candidate.getX()] = 0;
		}
	}

	private List<Vector2> buildCandidates(int row) {
		List<Vector2> candidates = new ArrayList<Vector2>();
		int j = 0;
		for(int col = 0; col < n; col++) {
			if(isSafe(row, col)) {
				candidates.add(new Vector2(col, row));
				j++;
			}
		}
		return candidates;
	}

	private boolean isSafe(int row, int col) {
		Vector2 pos = new Vector2(col, row);
		Vector2 newPos, delta;
		for(int deltaRow = -1; deltaRow <= 1; deltaRow++) {
			for(int deltaCol = -1; deltaCol <= 1; deltaCol++) {
				if(deltaRow == 0 && deltaCol == 0) continue;
				delta = new Vector2(deltaCol, deltaRow);

				newPos = pos.add(delta);
				while(checkBoundary(newPos)) {
					if(board[newPos.getY()][newPos.getX()] != 0) return false;
					newPos = newPos.add(delta);
				}
			}
		}

		return true;
	}

	private boolean checkBoundary(Vector2 pos) {
		return pos.getX() >= 0 && pos.getX() < this.n && pos.getY() >= 0 && pos.getY() < this.n;

	}
}
