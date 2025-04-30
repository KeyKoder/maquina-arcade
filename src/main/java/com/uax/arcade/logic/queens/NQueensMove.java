package com.uax.arcade.logic.queens;

import com.uax.arcade.logic.GameMove;

public class NQueensMove extends GameMove<Integer> {
	// No se están usando los campos from y to para el sentido que dicta su nombre, en este caso, simplemente
	// representan una posición (fila,columna), porque es todo lo necesario que se necesita para codificar la solución del
	// problema de las N reinas, por eso "ocultamos" el constructor original, para poder exponer los nombres
	// correctos en el constructor y que no haya ninguna posible confusión

	private NQueensMove(Integer from, Integer to) {
		super(from, to);
	}

	public NQueensMove(int row, int col) {
		this(Integer.valueOf(col), Integer.valueOf(row));
	}

	@Override
	public String toString() {
		return "Reina en (" + this.from + "," + this.to + ")";
	}
}
