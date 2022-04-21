package com.leo.algorithms;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.leo.algorithms.Assets.LButton;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class graphVisualiser extends ApplicationAdapter {
	
	private Graph graph;
	private ShapeRenderer sr;
	private int WINDOW_WIDTH, WINDOW_HEIGHT;
	private int VERTEX_RADIUS = 15;
	private BitmapFont font;
	private Batch batch;
	private Stage stage;
	private Texture myTexture;
	private TextureRegion myTextureRegion;
	private TextureRegionDrawable myTextRegionDrawable;
	private ImageButton retryButton;
	private TextButtonStyle textButtonStyle;
	private TextButton addVertex, removeVertex, removePath;
	private TextField weightInputField;
	private ArrayList<Actor> weightInputFields;
	private ArrayList<LButton> editButtons;
	private Random r;
	private boolean addVertexToggle = false;
	private boolean removeVertexToggle = false;
	private boolean removeEdgeToggle = false;
	private boolean addEdgeToggle = false;
	private LButton addVertexButton, removeVertexButton, addEdgeButton, removeEdgeButton;
	
	
	public Graph createGraph(Random r) {
		graph = new Graph("My Graph");
		
		
		
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addVertex("d");
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		
		for(int i = 0; i < 0; i++) {
			graph.addVertex(String.valueOf(i));
		}
		
		graph.addEdge("a", "b", 10);
		graph.addEdge("b", "c", 9);
		graph.addEdge("c", "d", 8);
		graph.addEdge("a", "3", 12);
		graph.addEdge("c", "2", 10);
		graph.addEdge("c", "1", 10);
		graph.addEdge("3", "4", 10);
		graph.addEdge("d", "3", 10);
		
		/*
		
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		
		graph.addEdge("1", "2", 10);
		graph.addEdge("2", "3", 10);
		graph.addEdge("3", "1", 2);
		graph.addEdge("1", "d", 10);
		
		*/
		for(Vertex vertex: graph.getVertexes()) {
			int x = r.nextInt(WINDOW_WIDTH - 40) + 20;
			int y = r.nextInt(WINDOW_HEIGHT - 80) + 60;
			
			
			while(coordinatesCollide(x, y, vertex, graph)) {
				x = r.nextInt(WINDOW_WIDTH - 40) + 20;
				y = r.nextInt(WINDOW_HEIGHT - 80) + 60;
			}
			
			
			vertex.setX(x);
			vertex.setY(y);
			vertex.setRadius(VERTEX_RADIUS);
			
		}
		return graph;
	}
	
	public boolean coordinatesCollide(int x, int y, Vertex currentVertex, Graph graph) {
		boolean collided = false;
		for(Vertex vertex: graph.getVertexes()) {
			if(vertex != currentVertex & vertex.hasCoords()) {
				if(x < 20) x = 20;
				if(x > WINDOW_WIDTH - 20) x = WINDOW_WIDTH - 20;
				if(y < 20) y = 20;
				if(y > WINDOW_HEIGHT - 20) y = WINDOW_HEIGHT - 20;
				int dx = vertex.getX() - x;
				int dy = vertex.getY() - y;
				int distance = (int) Math.hypot(dx, dy);
				if(distance < 75) {
					collided = true;
				}}}
		return collided;
	}
	
	@Override
	public void create() {
		
		r = new Random();
		WINDOW_WIDTH = Gdx.graphics.getWidth();
		WINDOW_HEIGHT = Gdx.graphics.getHeight();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		batch = new SpriteBatch();
		
		graph = createGraph(r);
		
		sr = new ShapeRenderer();
	
		myTexture = new Texture(Gdx.files.internal("retry.png"));
		myTextureRegion = new TextureRegion(myTexture);
		myTextRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		retryButton = new ImageButton(myTextRegionDrawable);
		
		retryButton.setX(10);
		retryButton.setY(10);
		
		retryButton.setWidth((float) (0.2 * retryButton.getWidth()));
		retryButton.setHeight((float) (30));
		
		retryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				graph = createGraph(r);
			}
		});
		TextFieldStyle textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = font;
		textFieldStyle.fontColor = Color.WHITE;
		weightInputField = new TextField("sad", textFieldStyle);
		weightInputField.setX(200);
		weightInputField.setY(200);
		weightInputField.setVisible(false);
		
		addVertexButton = new LButton("Add Vertex", Gdx.graphics.getWidth()-520, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		addVertexButton.setClickedColor(Color.GREEN);

		addEdgeButton = new LButton("Add Edge", Gdx.graphics.getWidth()-390, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		addEdgeButton.setClickedColor(Color.GREEN);
		
		removeVertexButton = new LButton("Remove Vertex", Gdx.graphics.getWidth()-260, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		removeVertexButton.setClickedColor(Color.RED);
		
		removeEdgeButton = new LButton("Remove Edge", Gdx.graphics.getWidth()-130, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		removeEdgeButton.setClickedColor(Color.RED);
		
		editButtons = new ArrayList<>();
		editButtons.add(addVertexButton);
		editButtons.add(addEdgeButton);
		editButtons.add(removeVertexButton);
		editButtons.add(removeEdgeButton);
		
		stage = new Stage(new ScreenViewport());
		stage.addActor(retryButton);
		stage.addActor(weightInputField);
		Gdx.input.setInputProcessor(stage);
		weightInputFields = new ArrayList<>();
		weightInputFields.add(weightInputField);
	
	}

	@Override
	public void render() {
		if(Gdx.input.isKeyJustPressed(Keys.Q)) Gdx.app.exit();
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) graph = createGraph(r);
		ScreenUtils.clear(Color.BLACK);
		
		for(Vertex vertex: graph.getVertexes()) {
			for(Edge edge: vertex.getEdges()) {
				edge.draw(sr);
			}
		}
		for(Vertex vertex: graph.getVertexes()) {
			if(addVertexToggle) {
				//
			}
			
			vertex.checkDragged();
			vertex.draw(sr, font, batch);
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		for(LButton button: editButtons) {
			button.draw(sr, batch);
		}
		
	}

	@Override
	public void dispose() {
		sr.dispose();
		stage.dispose();
	}
}