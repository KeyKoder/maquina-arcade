package com.uax.arcade;

import com.uax.arcade.logic.Game;
import com.uax.arcade.logic.GameMove;
import com.uax.arcade.logic.GameSolution;
import com.uax.arcade.logic.hanoi.Hanoi;
import com.uax.arcade.logic.knight.KnightPath;
import com.uax.arcade.logic.queens.NQueens;
import com.uax.arcade.logic.queens.NQueensMove;
import com.uax.arcade.logic.utils.Vector2;
import com.uax.arcade.persistence.utils.HibernateUtil;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Theme("default")
public class Main implements AppShellConfigurator {
	public static void main(String[] args) {
//		HibernateUtil.getSessionFactory();
		SpringApplication.run(Main.class, args);

//		Game g = new KnightPath();
//		g.startGame(8, 0, 0);
//		for(Object sol : g.getSolutions()) {
//			for(Object m : ((GameSolution)sol).getMoves()) {
//				System.out.println(m);
//			}
//			System.out.println("---");
//		}
	}
}
