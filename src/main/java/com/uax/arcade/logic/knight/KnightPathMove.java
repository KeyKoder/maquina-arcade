package com.uax.arcade.logic.knight;

import com.uax.arcade.logic.GameMove;
import com.uax.arcade.logic.utils.Tuple;
import com.uax.arcade.logic.utils.Vector2;

public class KnightPathMove extends GameMove<Vector2> {
	public KnightPathMove(Vector2 from, Vector2 to) {
		super(from, to);
	}

	@Override
	public String toString() {
		return "Caballo desde " + from.toString() + " hasta " + to.toString();
	}
}
