package com.amittaigames.engine;

import com.amittaigames.engine.graphics.Render;
import com.amittaigames.engine.graphics.Window;

public abstract class CoreGame {

	/**
	 * Used to initialize any objects that require OpenGL context
	 */
	public abstract void init();

	/**
	 * Used to render any OpenGL objects
	 * @param r Render object
	 */
	public abstract void render(Render r);

	/**
	 * Used to run logic with delta time
	 * @param delta Delta for fixed update
	 */
	public abstract void update(float delta);

	/**
	 * Used to delete any OpenGL objects
	 */
	public abstract void cleanUp();

	/**
	 * Call for the window to close, and resources to be cleaned up
	 */
	public void quit() {
		Window.close();
	}

}
