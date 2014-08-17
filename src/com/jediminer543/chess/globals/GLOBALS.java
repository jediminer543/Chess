package com.jediminer543.chess.globals;

import com.jediminer543.util.render.Renderable;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public class GLOBALS
{
	public static ArrayList<Renderable> renderables;

	public static class Screen
	{
		public static boolean fullscreen;
		public static boolean vsync;
		public static int width;
		public static int height;
	}

	public static class Matricies
	{
		public static class Projection
		{
			public static FloatBuffer Project = BufferUtils.createFloatBuffer(16);
			public static FloatBuffer Ortho = BufferUtils.createFloatBuffer(16);
		}

		public static class Modelview
		{
			public static FloatBuffer base = BufferUtils.createFloatBuffer(16);
			public static FloatBuffer textReady = BufferUtils.createFloatBuffer(16);
		}
	}
}
