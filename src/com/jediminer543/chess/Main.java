package com.jediminer543.chess;

import com.jediminer543.chess.globals.GLOBALS;
import com.jediminer543.util.config.Config;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public class Main
{
	public static File configFile;
	public static Config config;

	public static void main(String[] args)
	{
		loadConfig();
		init();
		loop();
	}

	public static void init()
	{
		initDisplay();
		initGL();
	}

	public static void loadConfig()
	{
		configFile = new File(System.getProperty("user.dir") + "\\Config.cfg");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				config = new Config(configFile);
				config.set("Screen.vsync", false);
				config.set("Screen.width", 1600);
				config.set("Screen.height", 900);
				config.set("Screen.fullscreen", false);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				config = new Config(configFile);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		GLOBALS.Screen.fullscreen = config.readBoolean("Screen.fullscreen");
		GLOBALS.Screen.vsync = config.readBoolean("Screen.vsync");
		GLOBALS.Screen.width = config.readInteger("Screen.width");
		GLOBALS.Screen.height = config.readInteger("Screen.height");

	}

	public static void initDisplay()
	{
		try {
			//Sets up screen
			Display.setDisplayMode(new DisplayMode(GLOBALS.Screen.width, GLOBALS.Screen.height));
			Display.setTitle("Chess Game");
			Display.create();
		}
		catch (LWJGLException e) {
			//Terminates the program if the display won't load
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void initGL()
	{
		//Init Project
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(90, Display.getWidth() / Display.getHeight(), 0.001f, 100);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, GLOBALS.Matricies.Projection.Project);
		GL11.glLoadIdentity();
		GL11.glOrtho(-250, 250, -250, 250, 0.1, 100);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, GLOBALS.Matricies.Projection.Ortho);
		GL11.glLoadMatrix(GLOBALS.Matricies.Projection.Project);

		//Init Modelview
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, GLOBALS.Matricies.Modelview.base);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, GLOBALS.Matricies.Modelview.textReady);
		GL11.glLoadIdentity();
		GL11.glLoadMatrix(GLOBALS.Matricies.Modelview.base);
	}

	public static void loop()
	{
		while (!Display.isCloseRequested()) {
			Display.update();
			Display.setVSyncEnabled(GLOBALS.Screen.vsync);
		}
	}
}
