package com.jediminer543.chess.common;

import org.lwjgl.util.vector.Vector2f;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jediminer543 on 17/08/2014.
 */
public abstract class Piece implements Serializable
{
	Vector2f startPos = new Vector2f();
	Vector2f currentPos = new Vector2f();
	Vector2f lastPos = new Vector2f();

	public Piece(Vector2f startPos)
	{
		this.startPos = startPos;
	}

	public boolean moveTo(Vector2f newPos)
	{
		if (this.isValidMove(newPos))
		{
			this.lastPos = currentPos;
			this.currentPos = newPos;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isValidMove(Vector2f newPos)
	{
		return this.getValidMoves().contains(newPos);
	}

	public abstract ArrayList<Vector2f> getValidMoves();
}
