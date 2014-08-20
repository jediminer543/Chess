package com.jediminer543.util.handlers;

import com.jediminer543.chess.globals.GLOBALS;
import com.jediminer543.util.render.RenderLayer;
import com.jediminer543.util.render.RenderType;
import com.jediminer543.util.render.Renderable;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public class RenderHandler
{
	public static void resetDraw()
	{
		for (Renderable r : GLOBALS.renderables) {
			r.drawnFrame = false;
		}
	}

	public static void drawOrtho(int level)
	{
		for (Renderable r : GLOBALS.renderables) {
			if (!r.drawnFrame) {
				if (r.type == RenderType.Ortho) {
					if (r.layer.getLayer() == level) {
						r.render();
					}
				}
			}
		}
	}

	public static void drawProject(int level)
	{
		for (Renderable r : GLOBALS.renderables) {
			if (!r.drawnFrame) {
				if (r.type == RenderType.Project) {
					if (r.layer.getLayer() == level) {
						r.render();
					}
				}
			}
		}
	}

	public static void drawAll()
	{
		for (RenderLayer layer : RenderLayer.values()) {
			drawOrtho(layer.getLayer());
			drawProject(layer.getLayer());
		}
	}
}