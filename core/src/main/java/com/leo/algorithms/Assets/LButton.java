package com.leo.algorithms.Assets;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LButton {
	
	int DEFAULT_FONT_SIZE = 1;
	String text;
	String defaultText;
	String hoverText = "Hover";
	String clickedText = "Clicked";
	int x, y, width, height;
	int leftSide, rightSide, top, bottom;
	int priority;
	boolean visible = true;
	boolean toggled = false;
	
	ShapeType shapetype;
	BitmapFont font;
	Color color, fontColor, colorHover, defaultColor, clickedColor;
	
	boolean clicked, hover, deselected;
	
	public LButton(String text, int x, int y, int width, int height, Color color, ShapeType shapetype) {
		//Position
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.leftSide = x;
		this.rightSide = x + width;
		this.top = y + height; //query?
		this.bottom = y; //query?
		
		//Text, Colours
		this.defaultText = text;
		this.hoverText = text;
		this.clickedText = text;
		this.color = color;
		this.defaultColor = color;
		this.shapetype = shapetype;
		this.colorHover = Color.GRAY;
		this.clickedColor = Color.BROWN;
		
		this.font = Resources.font;
		this.font.getData().setScale(DEFAULT_FONT_SIZE);
		this.font.setColor(Color.BLACK);
		
		}
	
	public boolean isClicked() {
		hover = false;
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		boolean M1 = Gdx.input.isButtonJustPressed(Buttons.LEFT);
		if((mouseX >= leftSide & mouseX <= rightSide) & (mouseY <= top & mouseY >= bottom)) {
			if(M1) toggled = !toggled;
			else hover = true;
		}
		return toggled;
	}
	
	public boolean isHovered() {
		return hover;
	}
	
	public void draw(ShapeRenderer sr, Batch batch, ArrayList<LButton> editButtons) {		
		// Clicked /  Hovered
		isClicked();
	
		if(toggled) {
			this.color = this.clickedColor;
			this.text = this.clickedText;
			untoggleOtherButtonsInGroup(editButtons);
		}
		else if(hover) {
			this.color = this.colorHover;
			this.text = this.hoverText;
		}
		else {
			this.color = this.defaultColor;
			this.text = this.defaultText;
		}
		
		// Draw button box
		sr.begin(this.shapetype);
		sr.setColor(this.color);
		sr.rect(this.x, this.y, this.width, this.height);
		sr.end();
		
		// Draw button text
		
		GlyphLayout layout = new GlyphLayout(font, this.text);
		float fontX = x + (width - layout.width) / 2;
		float fontY = y + (height + layout.height) / 2;
		
		batch.begin();
		font.draw(batch, layout, fontX, fontY);
		batch.end();
	}
	
	public void untoggleOtherButtonsInGroup(ArrayList<LButton> editButtons) {
		for(LButton button: editButtons) {
			if(button != this) {
				button.setToggled(false);
			}
		}
	}
	
	public void setFont(String filepath, Color color) {
		try {
			this.fontColor = color;
								
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}
	
	public void setColor(Color color) {
		this.color = color;		
	}
	
	public void setHoverColor(Color color) {
		this.colorHover = color;
	}
	
	public void setClickedColor(Color color) {
		this.clickedColor = color;
	}
	
	public void setDefaultText(String text){
		this.defaultText = text;
	}
	
	public void setHoverText(String text){
		this.hoverText = text;
	}
	
	public void setClickedText(String text){
		this.clickedText = text;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;		
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisibile() {
		return this.visible;
	}
	
	public void setToggled(boolean value) {
		this.toggled = value;
	}
	
	public boolean getToggled() {
		return this.toggled;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
		
	
	
}
