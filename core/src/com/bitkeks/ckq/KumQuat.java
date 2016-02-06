package com.bitkeks.ckq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
//import com.badlogic.gdx.graphics.g2d.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class KumQuat extends Game {

	public static KumQuat game;
	public static SpriteBatch batch;
	public static SpriteCache cache;
	//ShapeRenderer shapeRenderer;
	public static Viewport viewport;
	public static OrthographicCamera camera;
	
	public static MainMenuScreen MMS;
	public static GeneratorScreen GS;
	public static GameScreen GMS;
	
	@Override
	public void create () {
		game = this;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		viewport = new ExtendViewport(800, 600, camera);
		batch = new SpriteBatch();
		cache = new SpriteCache(20000, false);
		MMS = new MainMenuScreen();
		GS = new GeneratorScreen();
		GMS = new GameScreen();
		this.setScreen(MMS);
		Resources.load();
		//viewport = new FillViewport();
		//shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void render() {
		super.render();
	}
	@Override
	public void dispose () {
		batch.dispose();
		cache.dispose();
	}
}
