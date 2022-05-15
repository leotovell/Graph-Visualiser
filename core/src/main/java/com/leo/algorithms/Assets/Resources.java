package com.leo.algorithms.Assets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.leo.algorithms.Graph;

/*** 
 * Thank you to Freddy Cansick for the inspiration (yes i copied it)
 * @author leobt
 */

public class Resources {
	
	public static ShapeRenderer sr;
	public static SpriteBatch batch;
	public static BitmapFont font;
	public static Stage stage;
	public static Graph graph;
	
	public Resources() {
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		stage = new Stage(new ScreenViewport());
	}

	// Getters/Setters
	
	public static ShapeRenderer getSr() {
		return sr;
	}

	public static SpriteBatch getBatch() {
		return batch;
	}

	public static BitmapFont getFont() {
		return font;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static Graph getGraph() {
		return graph;
	}
	
	public static void setGraph(Graph g) {
		graph = g;
	}
	
	
	
}
