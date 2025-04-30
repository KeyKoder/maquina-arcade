package com.uax.arcade.logic.knight;

import com.uax.arcade.logic.Game;
import com.uax.arcade.logic.GameMove;
import com.uax.arcade.logic.queens.NQueensMove;
import com.uax.arcade.logic.utils.Vector2;

import java.util.ArrayList;
import java.util.List;

// Resuelto utilizando el Algoritmo de Warnsdorff

/**
 * Los parámetros para lanzar el juego "Recorrido del Caballo" son los siguientes:
 * <br>N -> Tamaño del tablero (N x N)
 * <br>Fila inicial
 * <br>Columna inicial
 */
public class KnightPath extends Game<Vector2> {
	private int n;
	private int startingRow;
	private int startingCol;
	private int[][] board;
	private List<Vector2> currentMoves = new ArrayList<Vector2>();

	private final Vector2[] POSSIBLE_MOVES = new Vector2[] {
			new Vector2(2, 1), new Vector2(1, 2), new Vector2(1, -2), new Vector2(2, -1),
			new Vector2(-2, 1), new Vector2(-1, 2), new Vector2(-1, -2), new Vector2(-2, -1),
	};

	public KnightPath() {
		super(3);
	}

	@Override
	protected void initialize(Object... params) {
		this.n = (Integer) params[0];
		this.startingRow = (Integer) params[1];
		this.startingCol = (Integer) params[2];
		this.board = new int[this.n][this.n];
		this.board[this.startingCol][this.startingRow] = 1;
		this.currentMoves.clear();
	}

	@Override
	protected void solve() {
		solvePath(new Vector2(this.startingCol, this.startingRow));
	}

	private void solvePath(Vector2 startingPos) {
		Vector2 pos = startingPos;
		Vector2 move;
		List<GameMove<Vector2>> moves = new ArrayList<GameMove<Vector2>>();

		for(int i = 0; i < this.n*this.n-1; i++) {
			move = nextMove(pos);
			if(move == null) return;

			moves.add(new KnightPathMove(pos, pos.add(move)));
			pos = pos.add(move);
			this.board[pos.getY()][pos.getX()] = 1;
		}
		if(moves.size() == this.n*this.n-1) this.addSolution(moves);
	}

	private Vector2[] buildCandidates(Vector2 pos) {
		Vector2[] candidates = new Vector2[n];
		int j = 0;
		Vector2 newPos;
		for(Vector2 move : POSSIBLE_MOVES) {
			newPos = pos.add(move);
			if(checkBoundary(newPos) && this.board[newPos.getY()][newPos.getX()] == 0) {
				candidates[j] = new Vector2(newPos);
				j++;
			}
		}
		return candidates;
	}

	private boolean checkBoundary(Vector2 pos) {
		return pos.getX() >= 0 && pos.getX() < this.n && pos.getY() >= 0 && pos.getY() < this.n;
	}

	private boolean isEmpty(Vector2 pos) {
		return checkBoundary(pos) && this.board[pos.getY()][pos.getX()] == 0;
	}

	// Devuelve cuantas casillas válidas hay accesibles desde esta casilla
	private int getDegree(Vector2 pos) {
		int count = 0;
		for(Vector2 move : POSSIBLE_MOVES) {
			if(isEmpty(pos.add(move))) count++;
		}
		return count;
	}

	private Vector2 nextMove(Vector2 pos) {
		int minDeg = POSSIBLE_MOVES.length+1;
		Vector2 minMove = null;
		int currentDeg;
		Vector2 newPos;

		for(Vector2 move : POSSIBLE_MOVES) {
			newPos = pos.add(move);
			currentDeg = getDegree(newPos);
			if(isEmpty(newPos) && currentDeg < minDeg) {
				minMove = move;
				minDeg = currentDeg;
			}
		}

		if(minMove == null) {
			return null;
		}

		return minMove;
	}
}
