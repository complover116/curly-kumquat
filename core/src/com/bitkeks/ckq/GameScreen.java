package com.bitkeks.ckq;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {

	float scrollX;
	float scrollY;

	static float WIDTH;
	static float HEIGHT;
	static float Xtension;

	// AndroidButton buttons[] = new AndroidButton[4];
	public GameScreen() {
	}

	@Override
	public void render(float delta) {
		CurGame.gameTime += delta;
		CurGame.tickEvents();
		if (Gdx.app.getType() == Application.ApplicationType.Android)
			Input.processInput();
		else
			Input.processKeyInput();

		CurGame.tickEnts(delta);
		CurGame.character.tick(delta);

		// float leftGutter = KumQuat.viewport.getLeftGutterWidth();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		KumQuat.camera.position.x = CurGame.character.x;
		KumQuat.camera.position.y = CurGame.character.y;
		if (CurGame.character.x < WIDTH / 2 - 200)
			KumQuat.camera.position.x = WIDTH / 2 - 200;
		if (CurGame.character.x > CurGame.maze.tiles[0].length * 32 - WIDTH / 2)
			KumQuat.camera.position.x = CurGame.maze.tiles[0].length * 32 - WIDTH / 2;
		if (CurGame.character.y < HEIGHT / 2)
			KumQuat.camera.position.y = HEIGHT / 2;
		if (CurGame.character.y > CurGame.maze.tiles[0][0].length * 32 - HEIGHT / 2)
			KumQuat.camera.position.y = CurGame.maze.tiles[0][0].length * 32 - HEIGHT / 2;

		KumQuat.camera.update();
		KumQuat.batch.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.cache.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.UIshapeRenderer.setProjectionMatrix(KumQuat.UIcamera.combined);
		KumQuat.shapeRenderer.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.cache.begin();

		/*
		 * byte up = 0; byte right = 0; byte down = 0; byte left = 0;
		 */
		KumQuat.cache.draw(CurGame.maze.cacheIDs[CurGame.curLayer]);

		KumQuat.cache.end();
		KumQuat.batch.begin();

		CurGame.character.draw();

		
		for(int i = 0; i < CurGame.entities.size(); i++) 
			CurGame.entities.get(i).drawBatch();
		
		KumQuat.batch.end();

		Gdx.gl.glEnable(GL20.GL_BLEND);
		KumQuat.shapeRenderer.begin(ShapeType.Filled);
		for(int i = 0; i < CurGame.entities.size(); i++) 
			CurGame.entities.get(i).draw();
		
		//DEBUG EVENT LOCATION
		KumQuat.shapeRenderer.setColor(Color.RED);
		for(int i = 0; i < CurGame.maze.events.size(); i ++) {
			Event event = CurGame.maze.events.get(i);
			KumQuat.shapeRenderer.line(event.x-10, event.y, event.x+10, event.y);
			KumQuat.shapeRenderer.line(event.x, event.y-10, event.x, event.y+10);
		}
		
		KumQuat.shapeRenderer.end();
		
		
		
		
		
		KumQuat.UIshapeRenderer.begin(ShapeType.Filled);
		
		
		
		if (Gdx.app.getType() == Application.ApplicationType.Android) {
			KumQuat.UIshapeRenderer.setColor(1, 1, 1, 0.5f);
			KumQuat.UIshapeRenderer.circle(100, 150, 100);
			KumQuat.UIshapeRenderer.setColor(1, 1, 1, 1);
			KumQuat.UIshapeRenderer.circle(100 + Input.joyX, 150 + Input.joyY, 40);
		}
		KumQuat.UIshapeRenderer.end();
		
		
		
		float fireIntensity = 0;
		//TODO: add panning
		//float firePan = 0;
		
		
		for(int i = 0; i < CurGame.entities.size(); i ++)
			if(CurGame.entities.get(i) instanceof EntityFire) {
				float dist = (float) Math.sqrt(Math.pow(CurGame.entities.get(i).x - CurGame.character.x, 2) + Math.pow(CurGame.entities.get(i).y - CurGame.character.y, 2));
				if (dist < 1024) {
					fireIntensity += 1024-dist;
				}
			}
		
		Resources.sounds.get("env/fire").setVolume(Resources.fireSoundID, Math.min(fireIntensity/2048, 1));
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {
		KumQuat.viewport.update(width, height);
		KumQuat.UIviewport.update(width, height);
		WIDTH = KumQuat.viewport.getWorldWidth();
		HEIGHT = KumQuat.viewport.getWorldHeight();
		Xtension = (WIDTH - 800) / 2;
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
