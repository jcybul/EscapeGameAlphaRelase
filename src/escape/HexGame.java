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
import escape.board.coordinate.HexCoordinate;
import escape.piece.*;
import escape.rule.GameHashMapOfRules;
import escape.rule.HexGameRules.HRules;
import escape.rule.OrthoGameRules.ORules;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * @version 28 abr. 2020
 */
public class HexGame implements EscapeGameManager<HexCoordinate>
{

	/**
	 * Description
	 *
	 */
	
	public HexBoard b;
	public HashMap<PieceName,PieceTypeInitializer> PieceTypes;
	
	public HexGame(HexBoard b,HashMap<PieceName,PieceTypeInitializer> PieceTypes )
	{
		this.PieceTypes = PieceTypes;
		this.b = b;
	}

	/*
	 * @see escape.EscapeGameManager#move(escape.board.coordinate.Coordinate, escape.board.coordinate.Coordinate)
	 */
	@Override
	public boolean move(HexCoordinate from, HexCoordinate to)
	{
		GameHashMapOfRules rules = new GameHashMapOfRules();
		if(b.getPieceAt(from) == null) {
			return false;
		}
		
		MovementPatternID pattern = this.PieceTypes.get(b.getPieceAt(from).getName()).getMovementPattern();
		ArrayList<HRules> list = rules.hexMovePattern.get(pattern);
		for(HRules r: list) {
			if(r.hTest(from, to, this)) {
				if( b.getPieceAt(to) != null && b.getPieceAt(from).getPlayer() == b.getPieceAt(to).getPlayer()) {
					return false;
				}
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
	public EscapePiece getPieceAt(HexCoordinate coordinate)
	{
		if(b.getPieceAt(coordinate) == null) {
			return null;
		}
		return b.getPieceAt(coordinate);
	}

	/*
	 * @see escape.EscapeGameManager#makeCoordinate(int, int)
	 */
	@Override
	public HexCoordinate makeCoordinate(int x, int y)
	{
		return HexCoordinate.makeCoordinate(x, y);
	}

}
