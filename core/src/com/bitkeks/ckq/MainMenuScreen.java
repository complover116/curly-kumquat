package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen implements Screen {
	float WIDTH;
	float HEIGHT;
	float Xtension;
	
	AndroidButton buttons[] = new AndroidButton[1];
	public MainMenuScreen() {
		buttons[0] = new AndroidButton(new Rectangle(200, 100, 400, 200));
	}
	@Override
	public void render(float delta) {
		
		
		for(int i = 0; i < buttons.length; i ++) {
			buttons[i].update();
		}
		//float leftGutter = KumQuat.viewport.getLeftGutterWidth();
		Gdx.gl.glClearColor(0.75f, 0.5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		KumQuat.camera.update();
		KumQuat.batch.setProjectionMatrix(KumQuat.camera.combined);
		if(buttons[0].justReleased){
			KumQuat.game.setScreen(KumQuat.GS);
		}
		KumQuat.batch.begin();
		KumQuat.batch.draw(Resources.getImage("interface/background"), -350, 0);
		if(buttons[0].isPressed)
		KumQuat.batch.draw(Resources.getImage("interface/buttons/play_pressed"), 205, 95);
		else
		KumQuat.batch.draw(Resources.getImage("interface/buttons/play"), 200, 100);
		KumQuat.batch.end();
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
		WIDTH = KumQuat.viewport.getWorldWidth();
		HEIGHT = KumQuat.viewport.getWorldHeight();
		Xtension = (WIDTH-800)/2;
	}
	
	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
