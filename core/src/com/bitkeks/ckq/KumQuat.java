package com.bitkeks.ckq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
//import com.badlogic.gdx.graphics.g2d.ShapeRenderer;

public class KumQuat extends Game {
	public static SpriteBatch batch;
	//ShapeRenderer shapeRenderer;
	public static Viewport viewport;
	public static OrthographicCamera camera;
	
	public static MainMenuScreen MMS;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		viewport = new ExtendViewport(800, 600, camera);
		batch = new SpriteBatch();
		MMS = new MainMenuScreen();
		this.setScreen(MMS);
		Resources.load();
		//viewport = new FillViewport();
		//shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void render() {
		super.render();
	}
}
