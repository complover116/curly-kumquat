package com.bitkeks.ckq;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by complover116 on 25.05.2015 for QAR-1 Reloaded
 */
public class Resources {
	public static HashMap<String, TextureRegion> textures = new HashMap<String, TextureRegion>();
	public static HashMap<String, Integer> cachedIDs = new HashMap<String, Integer>();
	public static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
	public static Music bitkeksDemo;
	public static Music alpaMode1;
	
	public static long fireSoundID;
	
	/*public static void loadVital() {
		textures.put("splashscreen", new Texture(Gdx.files.internal("img/Logo.png")));
		textures.put("ERROR", new Texture(Gdx.files.internal("img/ERROR.png")));
	}*/
	public static void load() {
		
		KumQuat.mainFont = new BitmapFont(Gdx.files.internal("fonts/mainFont.fnt"));
		
		Gdx.app.log("Resources", "Loading image list...");
		String imglistRaw = Gdx.files.internal("ImageList").readString();

		String imglist[] = imglistRaw.split("\n");
		Gdx.app.log("Resources", "Found " + imglist.length + " image declarations");
		int i = 1;
		for (String imagename : imglist) {
			imagename = imagename.trim();
			//MainMenuScreen.loadStep = "Loading " + imagename;
			try {
				textures.put(imagename, new TextureRegion(new Texture(Gdx.files.internal("img/" + imagename + ".png"))));
				Gdx.app.log("Resources", "("+i+"/"+imglist.length+") Loaded " + imagename);
				
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + imagename);
				//e.printStackTrace();
				//MainMenuScreen.loaded = -1;

				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {

				}
				//return;
			}
			i++;
		}
		bitkeksDemo = Gdx.audio.newMusic(Gdx.files.internal("sound/music/BitKeks-demo.ogg"));
		alpaMode1 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/alpamode1.ogg"));
		Gdx.app.log("Resources", "Loading sound list...");
		
		String soundlistRaw = Gdx.files.internal("SoundList").readString();
		
		String soundlist[] = soundlistRaw.split("\n");
		Gdx.app.log("Resources", "Found " + soundlist.length + " sound declarations");
		
		for (String soundname : soundlist) {
			soundname = soundname.trim();
			//MainMenuScreen.loadStep = "Loading " + soundname;
			try {
				sounds.put(soundname, Gdx.audio.newSound(Gdx.files.internal("sound/" + soundname + ".wav")));
				Gdx.app.log("Resources", "Loaded " + soundname);
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + soundname);
				//MainMenuScreen.loaded = -1;
				//return;
			}
		}
		fireSoundID = sounds.get("env/fire_loud").loop();
		alpaMode1.setLooping(true);
		bitkeksDemo.setVolume(0.05f);
		bitkeksDemo.setLooping(true);
		bitkeksDemo.play();
		sounds.get("env/fire").setVolume(fireSoundID,0);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		
		}
		//MainMenuScreen.loaded = 0;
		//TODO:TEMP
		FObjectType.objects.add(new FObjectType(FObjectType.TYPE_METAL, "wreck/test_1", 64));
		FObjectType.objects.add(new FObjectType(FObjectType.TYPE_METAL, "wreck/test_2", 64));
		FObjectType.objects.add(new FObjectType(FObjectType.TYPE_METAL, "wreck/test_3", 64));
		//FObjectType.objects.add(new FObjectType(FObjectType.TYPE_METAL, "wreck/test_1_small", 32));
		//FObjectType.objects.add(new FObjectType(FObjectType.TYPE_METAL, "wreck/test_2_small", 32));
		FObjectType.objects.add(new FObjectType(FObjectType.TYPE_METAL, "wreck/test_1_big", 128));
		
		FObjectType.woodenObjects.add(new FObjectType(FObjectType.TYPE_WOOD, "wreck/wood_1", 64));
		FObjectType.woodenObjects.add(new FObjectType(FObjectType.TYPE_WOOD, "wreck/wood_1_small", 32));
		FObjectType.woodenObjects.add(new FObjectType(FObjectType.TYPE_WOOD, "wreck/wood_2_small", 32));
	}

	public static TextureRegion getImage(String name) {
		if (textures.containsKey(name)) {
			return textures.get(name);
		}
		Gdx.app.error("Resources", "Requested missing texture:"+name);
		textures.put(name, textures.get("ERROR"));
		return textures.get(name);
	}

	static void playSound(String name) {
		if(sounds.get(name).play() == -1) {
			Gdx.app.error("Sound", "Error playing "+name);
		}
		//Gdx.app.debug("Sound", "Playing "+name);
		
	}
}
