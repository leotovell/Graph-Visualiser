package com.leo.algorithms;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class graphVisualiser extends ApplicationAdapter {

	private Graph graph;
	private ShapeRenderer sr;
	private int WINDOW_WIDTH, WINDOW_HEIGHT;
	private Stage stage;
	private Texture myTexture;
	private TextureRegion myTextureRegion;
	private TextureRegionDrawable myTextRegionDrawable;
	private ImageButton retryButton;
	private TextField weightInputField;
	private Random r;
	private int fps;


	public Graph createGraph(Random r) {
		graph = new Graph("My Graph");
		return graph;
	}

	@Override
	public void create() {

		r = new Random();
		WINDOW_WIDTH = Gdx.graphics.getWidth();
		WINDOW_HEIGHT = Gdx.graphics.getHeight();
		graph = createGraph(r);
		fps = Gdx.graphics.getFramesPerSecond();

		TextFieldStyle textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = graph.getFont();
		textFieldStyle.fontColor = Color.WHITE;
		weightInputField = new TextField("sad", textFieldStyle);
		weightInputField.setX(200);
		weightInputField.setY(200);
		weightInputField.setVisible(false);

		myTexture = new Texture(Gdx.files.internal("retry.png"));
		myTextureRegion = new TextureRegion(myTexture);
		myTextRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		retryButton = new ImageButton(myTextRegionDrawable);
		retryButton.setX(10);
		retryButton.setY(10);
		retryButton.setWidth((float) (0.2 * retryButton.getWidth()));
		retryButton.setHeight((30));
		retryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				graph = createGraph(r);
			}
		});

		stage = new Stage(new ScreenViewport());
		stage.addActor(retryButton);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render() {
		if(Gdx.input.isKeyJustPressed(Keys.Q)) Gdx.app.exit();
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) graph = createGraph(r);
		ScreenUtils.clear(Color.BLACK);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		graph.draw();
		
		graph.getBatch().begin();
		graph.getFont().draw(graph.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth() - 70, Gdx.graphics.getHeight() - 15);
		graph.getBatch().end();
		
		
	}

	@Override
	public void dispose() {
		graph.getBatch().dispose();
		graph.getShapeRenderer().dispose();
	}
}