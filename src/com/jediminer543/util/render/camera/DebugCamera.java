package com.jediminer543.util.render.camera;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Jediminer543 on 23/08/2014.
 */
public class DebugCamera extends Camera
{
	Vector3f lookVelocity = new Vector3f();

	@Override
	public void tick()
	{
		detectInput();
		moveCamera();
		convertLook();
	}

	public void moveCamera()
	{
		GL11.glLoadIdentity();
		GL11.glRotatef(rot.x, 0, 1, 0);
		GL11.glRotatef(rot.y, 1, 0, 0);
		GL11.glTranslatef(pos.getX(), pos.getY(), pos.getZ());
//		GL11.glRotatef(rot.z, 0, 0, 0);
	}

	public void detectInput()
	{
		if (Mouse.isGrabbed()) {
			int DX = Mouse.getDX();
			int DY = Mouse.getDY();

			rot.x += DX * 0.15;
			rot.y += -DY * 0.1;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F1)) {
			Mouse.setGrabbed(!Mouse.isGrabbed());
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			lookVelocity.x += (float) (10 * 0.01);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			lookVelocity.x += (float) (-10 * 0.01);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			lookVelocity.z += (float) (-10 * 0.01);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			lookVelocity.z += (float) (10 * 0.01);
		}
	}

	public void convertLook()
	{
		float Hypotonuse = lookVelocity.z; //TODO add delta
		float Ajacent = Hypotonuse * (float) Math.cos(Math.toRadians(rot.x));
		float Oposite = Hypotonuse * (float) Math.sin(Math.toRadians(rot.x)); pos.z += Ajacent; pos.x -= Oposite;

		float HypotonuseP = lookVelocity.x; //TODO add delta
		float AjacentP = HypotonuseP * (float) Math.cos(Math.toRadians(rot.x - 90));
		float OpositeP = HypotonuseP * (float) Math.sin(Math.toRadians(rot.x - 90)); pos.z += AjacentP;
		pos.x -= OpositeP;

		lookVelocity = new Vector3f();
	}
}
