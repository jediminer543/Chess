package com.jediminer543.util.render.model;

import com.jediminer543.util.render.Renderable;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Created by Jediminer543 on 13/08/2014.
 */
public class Face extends Renderable
{

	ArrayList<Vertex> vertexes = new ArrayList<Vertex>();

	public boolean hasNormal = true;
	public boolean hasTexture = true;

	public String materialName;

	public MTLMaterial material;

	@Override
	public void render()
	{
		for (Vertex vertex: vertexes)
		{
			if (hasTexture) {
				GL11.glTexCoord2f(vertex.texture.getX(), vertex.texture.getY());
			}
			if (hasNormal) {
				GL11.glNormal3f(vertex.normal.getX(), vertex.normal.getY(), vertex.normal.getZ());
			}
			GL11.glVertex3f(vertex.vertex.getX(), vertex.vertex.getY(), vertex.vertex.getZ());
		}

	}
}
