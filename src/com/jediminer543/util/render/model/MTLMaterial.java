package com.jediminer543.util.render.model;

import com.jediminer543.util.render.Texture;

/**
 * Created by Jediminer543 on 13/08/2014.
 */
public class MTLMaterial
{
	public String name;
	public String textureName;
	public Texture texture;
	public org.newdawn.slick.opengl.Texture slickTexture;

	//TODO Learn MTL CODE and how to use materials in LWJGL
	public MTLMaterial(String name)
	{
		this.name = name;
	}
}
