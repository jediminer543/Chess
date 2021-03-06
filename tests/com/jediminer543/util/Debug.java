package com.jediminer543.util;

import com.jediminer543.chess.globals.GLOBALS;
import com.jediminer543.util.config.Config;
import com.jediminer543.util.render.Texture;
import com.jediminer543.util.render.camera.Camera;
import com.jediminer543.util.render.camera.DebugCamera;
import com.jediminer543.util.render.model.Model;
import com.jediminer543.util.render.model.ObjectLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jediminer543 on 14/08/2014.
 */
public class Debug
{
	public static File configFile;
	public static Config config;
	static Model model;
	static Model modelKing;
	static Model modelPawn;
	static Texture texture;
	static Camera camera = new DebugCamera();

	public static void main(String[] args)
	{
		loadConfig();
		init();
		try {
			model = ObjectLoader.loadModel(new File("res/model/chessBoard/ChessBoardMK5.obj"));
			model.init();
			//model.x = 10;
			//textureOld = new TextureOld("res/model/iso/iso.png");
			modelKing = ObjectLoader.loadModel(new File("res/model/pieces/PieceKing.obj"));
			modelKing.init();
			modelKing.pos.y = 2;
			modelKing.scale.x = 0.25f;
			modelKing.scale.y = 0.25f;
			modelKing.scale.z = 0.25f;
			modelKing.rot.x = -90;
			modelPawn = ObjectLoader.loadModel(new File("res/model/pieces/PiecePawn.obj"));
			modelPawn.init();
			modelPawn.pos.y = 2;
			modelPawn.pos.x = 2;
			modelPawn.scale.x = 0.25f;
			modelPawn.scale.y = 0.25f;
			modelPawn.scale.z = 0.25f;
			modelPawn.rot.x = -90;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		loop();


	}

	public static void init()
	{
		initDisplay();
		initGL();
	}

	public static void loadConfig()
	{
		configFile = new File(System.getProperty("user.dir") + "\\DebugConfig.cfg");
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
			//Display.setInitialBackground(0, 0, 255);
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
		GLU.gluLookAt(5, 4, 0, 0, 0, 0, 0, 1, 0);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, GLOBALS.Matricies.Projection.Project);
		//GL11.glLoadIdentity();
		//GL11.glOrtho(-250, 250, -250, 250, 0.1, 100);
		//GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, GLOBALS.Matricies.Projection.Ortho);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1600, 900, 0, 0.1, 100);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, GLOBALS.Matricies.Projection.Ortho);
		GL11.glLoadMatrix(GLOBALS.Matricies.Projection.Project);

		//Init Modelview
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
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
			camera.tick();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			//GL11.glClearColor(0.2f, 6.08f, 8.08f, 0);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadMatrix(GLOBALS.Matricies.Projection.Project);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
//			GL11.glEnable(GL11.GL_BLEND);
//			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			model.render();
			modelKing.render();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			modelPawn.render();
//			GL11.glDisable(GL11.GL_BLEND);

			Display.update();
			Display.sync(120);
		}
	}

}
