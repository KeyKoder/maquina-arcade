package com.uax.arcade.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Arcade")
@Route(value = "")
@AnonymousAllowed
public class MainView extends TVView {

	public MainView() {
		super();
		H1 title = new H1("PUZZ-CADE");
		title.getStyle()
				.set("color", "#50cec3")
				.set("text-shadow", "4px 4px 0 #046075")
				.set("margin-bottom", "40px");

		Div gamesContainer = new Div();
		gamesContainer.getStyle()
				.set("display", "grid")
				.set("grid-template-columns", "repeat(3, 1fr)")
				.set("gap", "20px")
				.set("max-width", "800px")
				.set("margin", "0 auto");

		Button reinasBtn = createGameButton("N REINAS", "nreinas");
		Button caballosBtn = createGameButton("CABALLO", "caballo");
		Button hanoiBtn = createGameButton("HANOI", "hanoi");

		gamesContainer.add(reinasBtn, caballosBtn, hanoiBtn);

		this.finishBuilding(title, gamesContainer);
	}

	private Button createGameButton(String title, String route) {
		Div content = new Div();

		Div text = new Div();
		text.setText(title);
		text.getStyle()
				.set("font-size", "14px")
				.set("color", "#fff");

		content.add(text);
		content.getStyle()
				.set("display", "flex")
				.set("flex-direction", "column")
				.set("align-items", "center");

		Button button = new Button(content);
		button.getStyle()
				.set("box-shadow", "inset 0 0 10px #4ec6be")
				.set("border", "3px solid #4ec6be")
				.set("border-radius", "10px")
				.set("padding", "20px")
				.set("cursor", "pointer")
				.set("width", "100%")
				.set("height", "100%");

		button.addClickListener(e -> {
			UI.getCurrent().navigate(route);
		});

		return button;
	}
}
