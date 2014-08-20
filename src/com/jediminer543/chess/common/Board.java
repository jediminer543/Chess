package com.jediminer543.chess.common;

import java.util.ArrayList;

/**
 * Created by Jediminer543 on 17/08/2014.
 */
public class Board
{
	//TODO


	public static ArrayList<Piece> getPieces()
	{
		return pieces;
	}

	public static void addPiece(Piece piece)
	{
		pieces.add(piece);
	}

	public static void delPiece(Piece piece)
	{
		pieces.remove(piece);
	}

	static ArrayList<Piece> pieces = new ArrayList<Piece>();

	public static void init()
	{
		//TODO
	}

}
