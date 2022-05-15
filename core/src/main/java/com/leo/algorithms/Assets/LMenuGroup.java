package com.leo.algorithms.Assets;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LMenuGroup {

	ArrayList<LExpandableMenu> elements;

	public LMenuGroup() {
		elements = new ArrayList<>();	
	}
	
	public void update() {
		for(LExpandableMenu menu: elements) menu.update();
		for(int i = 0; i < elements.size(); i++) {
			if(i < 1 && elements.size() > 1) i = 1;
			LExpandableMenu previousMenu = elements.get(i -1);
			elements.get(i).setPosition(previousMenu.getPosition()[0], (previousMenu.getPosition()[1] - previousMenu.getDimensions()[1]));
		}
	}
	
	public void render(ShapeRenderer sr, SpriteBatch batch) {
		for(LExpandableMenu menu: elements) menu.draw(sr, batch);
	}
	
	public void addMenu(LExpandableMenu menu) {
		elements.add(menu);
	}
	
}
