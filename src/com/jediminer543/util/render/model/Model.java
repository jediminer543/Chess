package com.jediminer543.util.render.model;

import com.jediminer543.util.render.Renderable;
import com.jediminer543.util.render.Texture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jediminer543 on 13/08/2014.
 */
public class Model extends Renderable
{
	public MTL mtl;
	public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
	public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
	public ArrayList<Vector2f> textures = new ArrayList<Vector2f>();
	public ArrayList<Face> faces = new ArrayList<Face>();
	boolean hasTextures;
	boolean hasNormals;

	MTLMaterial currentMaterial;

	public File path;

	public void init()
	{
		initTextures();
	}

	public void initTextures()
	{
		for (MTLMaterial material:mtl.materials)
		{
			try {
				material.texture = new Texture(path.getParent() + "\\" + material.textureName);
				material.slickTexture = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", org.newdawn.slick.util.ResourceLoader.getResourceAsStream(path.getParent() + "\\" + material.textureName));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render()
	{
		GL11.glPushMatrix();
/*		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(x, 1, 0, 0);
		GL11.glRotatef(y, 0, 1, 0);
		GL11.glRotatef(z, 0, 0, 1);*/
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (Face f:faces) {
			if (currentMaterial == null) {
				for (MTLMaterial material : mtl.materials) {
					if (material.name.equals(f.materialName)) {
						GL11.glEnd();
						currentMaterial = material;
						currentMaterial.texture.bind();
						GL11.glBegin(GL11.GL_TRIANGLES);
					}
				}
			}
			else if (!currentMaterial.name.equals(f.materialName)) {
				for (MTLMaterial material : mtl.materials) {
					if (material.name.equals(f.materialName)) {
						GL11.glEnd();
						currentMaterial = material;
						currentMaterial.texture.bind();
						GL11.glBegin(GL11.GL_TRIANGLES);
					}
				}
			}
			f.render();

		}
		GL11.glEnd();
		GL11.glPopMatrix();
		currentMaterial = null;
	}
}
