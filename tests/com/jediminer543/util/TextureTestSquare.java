package com.jediminer543.util;

import org.lwjgl.opengl.GL11;

/**
 * Created by Jediminer543 on 16/08/2014.
 */
public class TextureTestSquare
{

	public static void render()
	{
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);

		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, 50);

		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(50, 50);

		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(50, 0);

		GL11.glEnd();
	}
}
