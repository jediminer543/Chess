package com.jediminer543.util;

/**
 * Created by Jediminer543 on 22/08/2014.
 */
public interface Positionable
{
	public float x = 0;
	public float y = 0;
	public float z = 0;

	float getX();
	float getY();
	float getZ();

	void setX(float x);
	void setY(float y);
	void setZ(float z);
}
