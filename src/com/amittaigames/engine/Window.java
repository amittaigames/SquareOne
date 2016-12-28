package com.amittaigames.engine;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

	private static long window;
	
	private static CoreGame game;
	private static Render render;
	
	public static void init(String title, int width, int height, CoreGame game) {
		if (!glfwInit()) {
			System.err.println("Could not initialize window system!");
			System.exit(1);
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		
		window = glfwCreateWindow(width, height, title, 0, 0);
		if (window == 0) {
			glfwTerminate();
			System.err.println("Could not create window!");
			System.exit(1);
		}
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();

		GLFWVidMode vidMode = glfwGetVideoMode(window);
		glfwSetWindowPos(window, (vidMode.width() / 2) - (width / 2), (vidMode.height() / 2) - (height / 2));
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		Window.game = game;
		Window.render = new Render();
		
		start();
	}
	
	private static void start() {
		game.init();
		
		double last;
		double now;
		
		float delta = 1.0f;
		
		while (!glfwWindowShouldClose(window)) {
			last = glfwGetTime();
			
			game.render(render);
			game.update(delta);
			
			glfwSwapBuffers(window);
			glfwPollEvents();
			
			now = glfwGetTime();
			delta = (float)(now - last) * 10.0f;
		}
		
		game.cleanUp();
		
		glfwDestroyWindow(window);
		glfwTerminate();
		
		System.exit(0);
	}
	
	public static boolean isKeyDown(int key) {
		int code = glfwGetKey(window, key);
		
		if (code == GLFW_PRESS)
			return true;
		else
			return false;
	}

}
