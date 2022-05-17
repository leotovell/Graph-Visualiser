package com.leo.algorithms;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.leo.algorithms.Assets.Resources;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class graphVisualiser extends ApplicationAdapter {

	private Graph graph;
	private Stage stage;
	private Texture myTexture;
	private TextureRegion myTextureRegion;
	private TextureRegionDrawable myTextRegionDrawable;
	private ImageButton retryButton;
	private TextField weightInputField;
	public Resources resources;

	public Graph createGraph() {
		graph = new Graph("My Graph");
		return graph;
	}

	@Override
	public void create() {
		
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

		graph = createGraph();
		Resources.setGraph(graph);

		TextFieldStyle textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = Resources.getFont();
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
				graph = createGraph();
			}
		});

		stage = Resources.getStage();
		stage.addActor(retryButton);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render() {
		if(Gdx.input.isKeyJustPressed(Keys.Q)) Gdx.app.exit();
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {graph = createGraph(); Resources.setGraph(graph);};
		if(Gdx.input.isKeyJustPressed(Keys.C)) graph.clearGraph();
		ScreenUtils.clear(Color.BLACK);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		graph.draw();
		
		Resources.getBatch().begin();
		Resources.font.setColor(Color.WHITE);
		Resources.getFont().draw(Resources.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth() - 70, Gdx.graphics.getHeight() - 15);
		Resources.getBatch().end();
		
	}

	@Override
	public void dispose() {
		Resources.getBatch().dispose();
		Resources.getSr().dispose();
	}
}