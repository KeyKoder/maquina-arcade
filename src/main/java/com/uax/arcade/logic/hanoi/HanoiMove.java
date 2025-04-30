package com.uax.arcade.logic.hanoi;

import com.uax.arcade.logic.GameMove;

public class HanoiMove extends GameMove<Tower> {
	private int disk;

	public HanoiMove(int disk, Tower from, Tower to) {
		super(from, to);
		this.disk = disk;
	}

	public int getDisk() {
		return disk;
	}
	@Override
	public String toString() {
		return "Mover disco: " + disk + ", desde poste: " + from + ", hasta poste: " + to;
	}
}
