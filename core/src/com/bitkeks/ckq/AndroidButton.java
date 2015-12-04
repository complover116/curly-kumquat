package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AndroidButton {
	public Rectangle bounds;
	public volatile boolean isPressed = false;
	public boolean justReleased = false;
	int pointerID = 0;

	public AndroidButton(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void update() {
	if(this.justReleased) this.justReleased = false;
		if (!this.isPressed) {
			for (int i = 0; i < 4; i++) {
				if (Gdx.input.isTouched(i)) {
					Vector2 pos = KumQuat.viewport.unproject(new Vector2(Gdx.input.getX(i), Gdx.input.getY(i)));
					if (bounds.contains(pos.x, pos.y)) {
						this.pointerID = i;
						this.isPressed = true;
						break;
					}
				}
			}
		} else {
			if (!Gdx.input.isTouched(this.pointerID)) {
				this.isPressed = false;
				this.justReleased = true;
			} else {
				Vector2 pos = KumQuat.viewport
						.unproject(new Vector2(Gdx.input.getX(this.pointerID), Gdx.input.getY(this.pointerID)));
				if (!bounds.contains(pos.x, pos.y)) {
					this.isPressed = false;
				}
			}
		}
	}
}
