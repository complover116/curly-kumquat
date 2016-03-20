package com.bitkeks.ckq.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bitkeks.ckq.KumQuat;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Burning Maze";
		config.width = 1200;
		config.height = 675;
		new LwjglApplication(new KumQuat(), config);
	}
}
