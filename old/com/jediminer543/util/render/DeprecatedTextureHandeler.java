package com.jediminer543.util.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Ben on 21/08/2014.
 */
public class DeprecatedTextureHandeler
{
	private static ArrayList<ByteBuffer> textureBuffer = new ArrayList<ByteBuffer>();
	private static int currentTextureID = 0;

	private static int id;
	static int size;

	public static int addTexture(ByteBuffer byteBuffer)
	{
		textureBuffer.add(byteBuffer);
		return textureBuffer.indexOf(byteBuffer);
	}

	public static ArrayList<ByteBuffer> getTextureBuffer()
	{
		return textureBuffer;
	}

	public static float getRforGL()
	{
		return (float)(currentTextureID + 0.5)/textureBuffer.size();
	}

	public static void setCurrentTextureID(int id)
	{
		currentTextureID = id;
	}

	public static void init()
	{
		for (ByteBuffer buffer1: textureBuffer)
		{
			size += buffer1.array().length;
		}
		ByteBuffer buffer = BufferUtils.createByteBuffer(size);
		for (ByteBuffer buffer1: textureBuffer)
		{
			buffer.put(buffer1);
		}
		GL11.glEnable(GL12.GL_TEXTURE_3D);
		id = GL11.glGenTextures();
		GL11.glBindTexture(GL12.GL_TEXTURE_3D, id);
		GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL12.GL_TEXTURE_WRAP_R, GL11.GL_REPEAT);
		GL12.glTexImage3D(GL12.GL_TEXTURE_3D, 0, GL11.GL_RGBA, 1024, 1024, textureBuffer.size(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
	}
}
