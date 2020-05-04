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

import java.util.*;
import escape.board.*;
import escape.board.coordinate.*;
import escape.piece.*;
import escape.rule.GameHashMapOfRules;
import escape.rule.SquareGameRules.Rules;
import escape.util.*;

/**
 * Description
 * @version 27 abr. 2020
 * @param <C>
 */
public class SquareGame implements EscapeGameManager<SquareCoordinate>
{	
	public SquareBoard b;
	public HashMap<PieceName,PieceTypeInitializer> PieceTypes;
	
	public SquareGame(SquareBoard b,HashMap<PieceName,PieceTypeInitializer> PieceTypes)
	{
		this.PieceTypes = PieceTypes;
		this.b = b;
	}
	/*
	 * @see escape.EscapeGameManager#move(escape.board.coordinate.Coordinate, escape.board.coordinate.Coordinate)
	 */
	@Override
	public boolean move(SquareCoordinate from, SquareCoordinate to)
	{
		GameHashMapOfRules rules = new GameHashMapOfRules();
		if(b.getPieceAt(from) == null) {
			return false;
		}
		
		MovementPatternID pattern = this.PieceTypes.get(b.getPieceAt(from).getName()).getMovementPattern();
		ArrayList<Rules> list = rules.squareMovePattern.get(pattern);
		for(Rules r: list) {
			if(r.TestRule(from, to, this)) {
				EscapePiece temp = b.getPieceAt(from);
				b.putPieceAt(temp,to);
				b.removePieceFrom(from);
				if(b.getLocationType(to) == LocationType.EXIT) {
					b.removePieceFrom(to);
				}
				return true;
			}
		}
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
		return SquareCoordinate.makeCoordinate(x, y);
	}

}
