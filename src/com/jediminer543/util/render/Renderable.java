package com.jediminer543.util.render;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public abstract class Renderable
{
	public Vector3f pos = new Vector3f();
	public Vector3f rot = new Vector3f();
	public boolean drawnFrame = false;
	public RenderLayer layer = RenderLayer.base;
	public RenderType type = RenderType.Ortho;

	public abstract void render();
}
