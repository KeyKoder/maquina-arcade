package com.uax.arcade.logic;

import java.util.ArrayList;
import java.util.List;


/**
 * Representa un juego/problema/puzzle cualquiera.
 * @param <T> El tipo con el que se va a representar los "movimientos" del juego y su solución.
 */
public abstract class Game<T> {
	private List<GameMove<T>> userMoves;
	private List<GameSolution<T>> solutions;
	private int paramsCount;

	public Game(int paramsCount) {
		this.solutions = new ArrayList<GameSolution<T>>();
		this.paramsCount = paramsCount;
	}

	private void preInit(Object... params) {
		if(params == null) {
			throw new UnsupportedOperationException("Tried to initialize a " + this.getClass().getCanonicalName() + " game with null params array. The minimum required array size is " + this.paramsCount);
		}
		if(params.length < this.paramsCount) {
			throw new UnsupportedOperationException("Tried to initialize a " + this.getClass().getCanonicalName() + " game with params array of size " + params.length + ". The minimum required array size is " + this.paramsCount);
		}
		this.initialize(params);
	}

	public void initGame(Object... params) {
		this.preInit(params);
	}

	public void startGame(Object... params) {
		this.preInit(params);
		this.solve();
	}

	protected abstract void initialize(Object... params);
	protected abstract void solve();


	public List<GameSolution<T>> getSolutions() {
		return this.solutions;//.stream().map(GameSolution::new).toList(); // Clone the list
	}

	public void addSolution(List<GameMove<T>> moves) {
		this.solutions.add(new GameSolution<T>(moves));
	}

	public List<GameMove<T>> getUserMoves() {
		return userMoves.stream().toList();
	}

	public void makeUserMove(GameMove<T> move) {
		this.userMoves.add(move);
	}


}
