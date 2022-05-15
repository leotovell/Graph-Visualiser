package com.leo.algorithms.Assets;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LExpandableMenu {
	
	int x, y, width, height;
	int originalHeight;
	int leftSide, rightSide, top, bottom;
	int tabX, tabY, tabWidth, tabHeight;
	int tabLeftSide, tabRightSide, tabTop, tabBottom;
	Color backgroundColor, tabColor;
	
	String title = "Default";
	
	boolean moving = false;
	boolean expanded = false;
	boolean direction; // FALSE: UP | TRUE: DOWN
	
	ArrayList<Object> elementList = new ArrayList<>();

	public LExpandableMenu(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.originalHeight = height;
		this.leftSide = x;
		this.rightSide = x + this.width;
		this.top = y;
		this.bottom = y - height;

		this.tabHeight = originalHeight/5;
		this.height = this.tabHeight;
		this.tabX = x;
		this.tabY = y - tabHeight;
		this.tabWidth = width;
		this.tabLeftSide = tabX;
		this.tabRightSide = tabX + width;
		this.tabTop = tabY;
		this.tabBottom = tabY + tabHeight;
		
		this.backgroundColor = color;
		this.tabColor = color;
	}
	
	public void draw(ShapeRenderer sr, Batch batch) {
	
		Resources.sr.begin(ShapeType.Filled);
		Resources.sr.setColor(backgroundColor);
		Resources.sr.rect(tabX, tabY, tabWidth, tabHeight);
		Resources.sr.rect(x, y-height, width, height);
		Resources.sr.end();
		
		Resources.sr.begin(ShapeType.Line);
		Resources.sr.setColor(Color.WHITE);
		Resources.sr.rect(tabX, tabY, tabWidth, tabHeight);
		Resources.sr.rect(x, y-height, width, height);
		Resources.sr.end();
		
//		GlyphLayout layout = new GlyphLayout(Resources.font, this.title);
		
		GlyphLayout tabLetter = new GlyphLayout(Resources.font, String.valueOf(this.title.charAt(0)));
		float tabFontX = (tabX + (tabWidth - tabLetter.width) /2);
		float tabFontY = (tabY + (tabHeight + tabLetter.height) /2);
		
		batch.begin();
		Resources.font.draw(batch, tabLetter, tabFontX, tabFontY);
		batch.end();
	}
	
	public void update() {
		
		// Has it finished its moving?
		if(moving) {
			if(direction) {
				if(!(height < originalHeight)) {moving = false; expanded = true;}
			}
			if(!direction) {
				if(height <= tabHeight) {moving = false; expanded = false;}
			}
		}
		
		// If clicked...
		else if(this.wasClicked()) {
			moving = true;
			direction = !direction;
		}
	
		// Animate
		if(moving) animate();
		
		// Update variables so functions work after being moved by LMenuGroup
		this.tabX = x;
		this.tabY = y - tabHeight;
		this.tabLeftSide = tabX;
		this.tabRightSide = tabX + width;
		this.tabTop = tabY;
		this.tabBottom = tabY + tabHeight;
	
	}
	
	public boolean wasClicked() {
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		if(mouseX > tabLeftSide && mouseX < tabRightSide && mouseY < tabBottom && mouseY > tabTop && Gdx.input.isButtonJustPressed(Buttons.LEFT)) return true;
		return false;
	}
	
	public void changeState() {
		
	};
	
	public void animate() {
		if(direction) {
			height ++;
		}
		else height--;
	};
	
	public void addElements(ArrayList<Object> elements) {
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int[] getPosition() {
		return new int[] {x, y};
	}
	
	public int[] getDimensions() {
		return new int[] {width, height};
	}
}