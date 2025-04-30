package com.uax.arcade.view;

import com.uax.arcade.logic.hanoi.Hanoi;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;

@PageTitle("Arcade - N Reinas")
@Route(value = "/nreinas")
@AnonymousAllowed
public class NQueensView extends TVView {
	private Hanoi game;
	private Div[] towerDivs;
	private Integer selectedTower = null;
	private static final String DEFAULT_TOWER_COLOR = "#888";
	private static final int MIN_DISKS = 3;
	private static final int MAX_DISKS = 8;
	private int diskCount = MIN_DISKS;
//	private IntegerField diskCount;

    public NQueensView() {
		game = new Hanoi();
		towerDivs = new Div[3];

		H1 title = new H1("N REINAS");
		title.getStyle()
				.set("color", "#50cec3")
				.set("text-shadow", "4px 4px 0 #046075")
				.set("margin-bottom", "40px");

		Text placeholderText = new Text("En progreso...");

		this.finishBuilding(title, placeholderText);
	}

	private void handleTowerClick(int towerIndex) {
		Notification.show(towerIndex + " & " + selectedTower);

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
				Notification.show("Invalid move!");
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
	}

	private void clearTowers() {
		towerDivs[0].getChildren().toList().forEach(d -> d.removeFromParent());
		towerDivs[1].getChildren().toList().forEach(d -> d.removeFromParent());
		towerDivs[2].getChildren().toList().forEach(d -> d.removeFromParent());
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
	}
}
