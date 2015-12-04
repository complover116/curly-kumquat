package com.bitkeks.ckq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.badlogic.gdx.graphics.g2d.ShapeRenderer;

public class KumQuat extends ApplicationAdapter {
	public static SpriteBatch batch;
	//ShapeRenderer shapeRenderer;
	public static Viewport viewport;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//viewport = new FillViewport();
		//shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}
}
