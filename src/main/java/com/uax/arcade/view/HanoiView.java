package com.uax.arcade.view;

import com.uax.arcade.logic.hanoi.Hanoi;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.List;

@PageTitle("Arcade - Torres de Hanoi")
@Route(value = "/hanoi")
@AnonymousAllowed
public class HanoiView extends TVView {
	private Hanoi game;
	private Div[] towerDivs;
	private Integer selectedTower = null;
	private static final String DEFAULT_TOWER_COLOR = "#888";
	private static final int MIN_DISKS = 3;
	private static final int MAX_DISKS = 8;
	private int diskCount = MIN_DISKS;
	private boolean usedSolve = false;
	private int totalMoves = 0;
	private Div movementCounter;
	private Div minMoves;

    public HanoiView() {
		game = new Hanoi();
		towerDivs = new Div[3];

		H1 title = new H1("HANOI");
		title.getStyle()
				.set("color", "#50cec3")
				.set("text-shadow", "4px 4px 0 #046075")
				.set("margin-bottom", "40px");

		Div controls = new Div();
		controls.setWidth("100vh");
		controls.getStyle()
				.set("display", "flex")
				.set("align-items", "center")
				.set("flex-direction", "row")
				.set("align-self", "center")
				.set("justify-content", "center");

		Div diskCountDiv = new Div();
		Text diskCountLabel = new Text("Disk Count:");
		Div diskCountControl = new Div();
		Text diskNumber = new Text(String.valueOf(diskCount));
		Button minus = new Button("-");
		minus.addClickListener(e -> {
			diskCount = Math.max(diskCount-1, MIN_DISKS);
			updateDisks(diskNumber);
		});
		minus.getStyle().set("color", "#fff").set("text-weight", "bold").set("background-color", "#ffffff3f")
				.set("margin", "5px").set("cursor", "pointer");
		Button plus = new Button("+");
		plus.addClickListener(e -> {
			diskCount = Math.min(diskCount+1, MAX_DISKS);
			updateDisks(diskNumber);
		});
		plus.getStyle().set("color", "#fff").set("text-weight", "bold").set("background-color", "#ffffff3f")
				.set("margin", "5px").set("cursor", "pointer");
		diskCountControl.add(minus, diskNumber, plus);
		diskCountDiv.add(diskCountLabel, diskCountControl);

		Button reset = new Button("Reset");
		reset.getStyle().set("color", "#fff").set("text-weight", "bold").set("background-color", "#ffffff3f")
				.set("margin", "5px").set("cursor", "pointer");
		reset.addClickListener(e -> {
			updateDisks(diskNumber);
			usedSolve = false;

			// En caso de que se resetee a mitad de la resolución automática, hay que parar los setTimeout que aún
			// no han ocurrido.
			Page page = UI.getCurrent().getPage();
			page.executeJs("for(let t of window.timeouts) clearTimeout(t)");
		});

		Button solve = new Button("Solve");
		solve.getStyle().set("color", "#fff").set("text-weight", "bold").set("background-color", "#ffffff3f")
				.set("margin", "5px").set("cursor", "pointer");
		solve.addClickListener(e -> {
			usedSolve = true;
			updateDisks(diskNumber);
			game.startGame(diskCount);

			// Como no podemos utilizar threads, para poder animar los movimientos automaticos, utilizo la función
			// setTimeout de javascript para poder posponer la ejecución de código en el navegador para más tarde
			Page page = UI.getCurrent().getPage();
			page.executeJs("window.timeouts = []");
			final int[] counter = {0};
			game.getSolutions().forEach(sol -> {
				sol.getMoves().forEach(move -> {
					page.executeJs("window.timeouts.push(setTimeout(() => {" +
													"document.getElementById('tower'+$0).click();" +
													"document.getElementById('tower'+$1).click();" +
													"document.getElementById('moveCounter').innerText = 'Total Moves: $3';" +
												"}," +
											"250*$2))", move.getFrom().ordinal(), move.getTo().ordinal(), counter[0], totalMoves);
					counter[0]++;
				});
			});
		});

		movementCounter = new Div("Total Moves: 0");
		movementCounter.setId("moveCounter");
		minMoves = new Div("Minimum Moves: 0");

		Div moveInfo = new Div();
		moveInfo.getStyle()
				.set("display", "flex")
				.set("align-items", "center")
				.set("flex-direction", "column")
				.set("align-self", "center")
				.set("justify-content", "center");

		moveInfo.add(minMoves, movementCounter);

		controls.add(diskCountDiv, reset, solve, moveInfo);

		FlexLayout flexLayout = new FlexLayout();
		flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
		flexLayout.setJustifyContentMode(FlexLayout.JustifyContentMode.CENTER);
		flexLayout.setAlignItems(FlexLayout.Alignment.STRETCH);

		for (int i = 0; i < 3; i++) {
			final int towerIndex = i; // Final variable for lambda expression
			towerDivs[i] = new Div();
			towerDivs[i].setWidth("400px");
			towerDivs[i].setHeight("600px");
			towerDivs[i].getStyle()
					.set("border", "1px solid black")
					.set("margin", "0 10px")
					.set("display", "flex")
					.set("flex-direction", "column")
					.set("align-items", "center")
					.set("justify-content", "end")
					.set("background", DEFAULT_TOWER_COLOR);
			towerDivs[i].setId("tower"+i);
			towerDivs[i].addClickListener(event -> {
				handleTowerClick(towerIndex);
				if(checkForVictory()) {
					Notification.show("You did it!", 3000, Notification.Position.MIDDLE);
					towerDivs[0].getStyle().set("background", "#39bf69");
					towerDivs[1].getStyle().set("background", "#39bf69");
					towerDivs[2].getStyle().set("background", "#39bf69");
				}
			});
			flexLayout.add(towerDivs[i]);
		}

		clearTowers();
		spawnDisks(3);

		this.finishBuilding(title, controls, flexLayout);
	}

	private void handleTowerClick(int towerIndex) {
		if (selectedTower == null) {
			// Select the tower
			selectedTower = towerIndex;
			if(towerDivs[selectedTower].getChildren().findFirst().isEmpty()) return;
			towerDivs[selectedTower].getStyle().set("background", "#fff"); // Highlight selected tower
		} else {
			// Attempt to move the disk
			if (verifyMove(selectedTower, towerIndex)) {
				moveDisk(selectedTower, towerIndex);
			} else {
				Notification.show("Invalid move!", 1000, Notification.Position.MIDDLE);
			}
			// Reset selection
			towerDivs[selectedTower].getStyle().set("background", DEFAULT_TOWER_COLOR);
			selectedTower = null;
		}
	}

	private boolean verifyMove(int fromIndex, int toIndex) {
		if(towerDivs[fromIndex].getChildren().findFirst().isEmpty()) return false;
		Component disk = towerDivs[fromIndex].getChildren().findFirst().get();
		int width = Integer.parseInt(disk.getStyle().get("width").replace("px", ""));
		if(towerDivs[toIndex].getChildren().findFirst().isEmpty()) return true;

		Component otherDisk = towerDivs[toIndex].getChildren().findFirst().get();
		int otherWidth = Integer.parseInt(otherDisk.getStyle().get("width").replace("px", ""));

		return width < otherWidth;
	}

	private void moveDisk(int fromIndex, int toIndex) {
		Component disk = towerDivs[fromIndex].getChildren().findFirst().get();
		int width = Integer.parseInt(disk.getStyle().get("width").replace("px", ""));
		disk.removeFromParent();
		List<Component> disks = towerDivs[toIndex].getChildren().toList();
		disks.forEach(d -> d.removeFromParent());
		towerDivs[toIndex].add(disk);
		towerDivs[toIndex].add(disks);
		totalMoves++;
		movementCounter.setText("Total Moves: " + totalMoves);
	}

	private void clearTowers() {
		for(Div div : towerDivs) {
			div.getChildren().toList().forEach(d -> d.removeFromParent());
			div.getStyle().set("background", DEFAULT_TOWER_COLOR);
		}
		selectedTower = null;
	}

	private void spawnDisks(int count) {
		for(int i=0;i<count;i++) {
			Div diskDiv = new Div();
			diskDiv.setWidth((50*(i+1)) + "px");
			diskDiv.setHeight("50px");
			diskDiv.getStyle()
					.set("border", "1px solid black")
					.set("background", i % 2 == 0 ? "#f00" : "#0f0")
					.set("color", "transparent");
			diskDiv.setText(String.valueOf(i+1));
			towerDivs[0].add(diskDiv);
		}
	}

	private void updateDisks(Text diskNumber) {
		diskNumber.setText(String.valueOf(diskCount));
		clearTowers();
		spawnDisks(diskCount);
		totalMoves = 0;
		movementCounter.setText("Total Moves: " + totalMoves);
		minMoves.setText("Minimum Moves: " + (int)Math.floor(Math.pow(2, diskCount) - 1));
	}

	private boolean checkForVictory() {
		return towerDivs[2].getChildren().toList().size() == diskCount;
	}
}
