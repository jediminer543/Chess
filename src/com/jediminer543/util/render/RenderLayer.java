package com.jediminer543.util.render;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public enum RenderLayer
{
	base(0),
	layerOne(1),
	layerTwo(2),
	layerThree(3),
	layerFour(4),
	layerFive(5);


	private int layer;

	private RenderLayer(int layer)
	{
		this.layer = layer;
	}

	public int getLayer()
	{
		return layer;
	}
}
