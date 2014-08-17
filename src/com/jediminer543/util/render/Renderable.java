package com.jediminer543.util.render;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public abstract class Renderable
{
	public boolean drawnFrame = false;
	public RenderLayer layer = RenderLayer.base;
	public RenderType type = RenderType.Ortho;

	public abstract void render();
}
