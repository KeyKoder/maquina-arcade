package com.uax.arcade.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

//@PageTitle("Arcade")
//@Route(value = "")
@AnonymousAllowed
public class TVView extends VerticalLayout {
	private Div tvContainer;
	public TVView() {
		setSizeFull();
		setAlignItems(FlexComponent.Alignment.CENTER);
		setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		getStyle()
				.set("background", "#222")
				.set("color", "#fff")
				.set("padding", "20px");

		Div tv = new Div();
		tv.setHeight("100vh");
		tv.setWidth("130vh");
		tv.getStyle()
				.set("position", "relative")
				.set("border", "3px solid #fff")
				.set("display", "flex")
				.set("align-items", "center")
				.set("justify-content", "center")
				.set("flex-direction", "column")
				.set("text-align", "center");
		tv.setId("tv");

		this.tvContainer = new Div();
		this.tvContainer.getStyle()
				.set("text-align", "center")
				.set("display", "flex")
				.set("align-items", "center")
				.set("justify-content", "center")
				.set("flex-direction", "column");
		this.tvContainer.setId("tvContainer");

		Button homeButton = new Button();
//		Image homeIcon = new Image("images/home.svg", "\u2302");
		SvgIcon homeIcon = new SvgIcon("images/home.svg");
		homeIcon.getStyle()
				.set("fill", "#63fff1")
				.set("color", "#63fff1");

		homeButton.setIcon(homeIcon);
		homeButton.addClickListener(e -> UI.getCurrent().navigate(""));
		homeButton.getStyle()
				.set("box-shadow", "inset 0 0 10px #4ec6be")
				.set("border", "3px solid #4ec6be")
				.set("position", "absolute")
				.set("top", "20px")
				.set("left", "20px")
				.set("width", "75px")
				.set("height", "75px")
				.set("border-radius", "50%")
				.set("cursor", "pointer");

		tv.add(homeButton);

		tv.add(this.tvContainer);

		add(tv);
	}

	public void finishBuilding(Component... components) {
		this.tvContainer.add(components);
	}
}