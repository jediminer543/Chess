package com.jediminer543.util.render.camera;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Jediminer543 on 23/08/2014.
 *
 * Base camera class
 */
public abstract class Camera
{
	public Vector3f pos = new Vector3f();
	public Vector3f rot = new Vector3f();

	public abstract void tick();
}
