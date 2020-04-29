/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape;

import escape.board.Board;
import escape.board.coordinate.*;
import escape.piece.EscapePiece;
import escape.util.EscapeGameInitializer;

/**
 * Description
 * @version 27 abr. 2020
 * @param <C>
 */
public class SquareGame implements EscapeGameManager<SquareCoordinate>
{	
	public Board b;
	
	
	public SquareGame(Board b)
	{
		this.b = b;

	}
	/*
	 * @see escape.EscapeGameManager#move(escape.board.coordinate.Coordinate, escape.board.coordinate.Coordinate)
	 */
	@Override
	public boolean move(SquareCoordinate from, SquareCoordinate to)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see escape.EscapeGameManager#getPieceAt(escape.board.coordinate.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(SquareCoordinate coordinate)
	{
		
		return b.getPieceAt(coordinate);
	}

	/*
	 * @see escape.EscapeGameManager#makeCoordinate(int, int)
	 */
	@Override
	public SquareCoordinate makeCoordinate(int x, int y)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
