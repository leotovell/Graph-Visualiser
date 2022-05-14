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
	int originalWidth;
	int leftSide, rightSide, top, bottom;
	int tabX, tabY, tabWidth, tabHeight;
	int tabLeftSide, tabRightSide, tabTop, tabBottom;
	Color backgroundColor, tabColor;
	
	String title = "Default";
	
	boolean moving = false;
	boolean expanded = false;
	
	ArrayList<Object> elementList = new ArrayList<>();

	public LExpandableMenu(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = 0;
		this.originalWidth = width;
		this.height = height;
		this.leftSide = x;
		this.rightSide = x + this.width;
		this.top = y;
		this.bottom = y - height;

		this.tabHeight = height/5;
		this.tabX = x + this.width;
		this.tabY = y - tabHeight;
		this.tabWidth = width/5;
		this.tabLeftSide = tabX;
		this.tabRightSide = tabX + tabWidth;
		this.tabTop = tabY;
		this.tabBottom = tabY + tabHeight;
		
		this.backgroundColor = color;
		this.tabColor = color;
	}
	
	public void move() {
		if(expanded) {
			if(width > 0) {
				width -= 1;
				tabX = x + width;
				tabY = y - tabHeight;
				tabLeftSide = tabX;
				tabRightSide = tabX + tabWidth;
				tabTop = tabY;
				tabBottom = tabY + tabHeight;
			}
			else {
				expanded = false;
				moving = false;
			}
		}
		else {
			if(width < originalWidth) {
				width += 1;
				tabX = x + width;
				tabY = y - tabHeight;
				tabLeftSide = tabX;
				tabRightSide = tabX + tabWidth;
				tabTop = tabY;
				tabBottom = tabY + tabHeight;
			}
			else {
				expanded = true;
				moving = false;
			}
		}
	}
	
	public void draw(ShapeRenderer sr, Batch batch) {
		
		if(!moving) {//Check tab clicked?
			float mouseX = Gdx.input.getX();
			float mouseY = 720 - Gdx.input.getY();
			boolean M1 = Gdx.input.isButtonJustPressed(Buttons.LEFT);
						
			if((mouseX >= tabLeftSide & mouseX <= tabRightSide) & (mouseY >= tabTop & mouseY <= tabBottom)) {
				if(M1) {
					tabColor = backgroundColor;
					moving = true;
				}
				else {
					tabColor = Color.GRAY;
				}
			}
			else {
				tabColor = backgroundColor;
			}
		}
		
		if(moving) {
			move();
		}
				
		sr.begin(ShapeType.Filled);
		sr.setColor(backgroundColor);
		sr.rect(x,y - height, width, height);
		sr.end();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(tabColor);
		sr.rect(tabX, tabY, tabWidth, tabHeight);
		sr.end();
		
		GlyphLayout layout = new GlyphLayout(Resources.font, this.title);
		float fontX = (x + (width - layout.width) / 2);
		float fontY = (y + (height + layout.height) / 2) - height;
		
		GlyphLayout tabLetter = new GlyphLayout(Resources.font, String.valueOf(this.title.charAt(0)));
		float tabFontX = (tabX + (tabWidth - tabLetter.width) /2);
		float tabFontY = (tabY + (tabHeight + tabLetter.height) /2);
		
		batch.begin();
		if(width > originalWidth * 0.2) Resources.font.draw(batch, layout, fontX, this.top - layout.height);
		Resources.font.draw(batch, tabLetter, tabFontX, tabFontY);
		batch.end();
	}
	
	public void addElements(ArrayList<Object> elements) {
		for(Object element: elements) {
			//
		}
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}