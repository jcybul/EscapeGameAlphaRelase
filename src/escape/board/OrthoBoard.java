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

package escape.board;

import java.util.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.EscapePiece;

/**
 * Description
 * @version 18 abr. 2020
 */


public class OrthoBoard  implements Board<OrthoSquareCoordinate> 
{
	Map<OrthoSquareCoordinate, LocationType> ortho;
	Map<OrthoSquareCoordinate, EscapePiece> pieces;
	
	private final int xMax,yMax;
	
	
	OrthoBoard(int xmax,int ymax)
	{
		this.xMax = xmax;
		this.yMax = ymax;
		this.ortho = new HashMap<OrthoSquareCoordinate,LocationType>();
		this.pieces = new HashMap<OrthoSquareCoordinate,EscapePiece>();
		
	}

	/*
	 * @see escape.board.Board#getPieceAt(escape.board.coordinate.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(OrthoSquareCoordinate coord)
	{
		return pieces.get(coord);
	}

	/*
	 * @see escape.board.Board#putPieceAt(escape.piece.EscapePiece, escape.board.coordinate.Coordinate)
	 */
	@Override
	public void putPieceAt(EscapePiece p, OrthoSquareCoordinate coord) 
	{
		
		if(coord.getX() > xMax || coord.getY() > yMax || coord.getX() < 1 || coord.getY() < 1) {
			throw new EscapeException("Coordinate out of Board");
		}
		else if(getLocationType(coord) == LocationType.BLOCK) {
			throw new EscapeException("Location is Bloqued");
		}
		else {
			if(getLocationType(coord) != LocationType.EXIT)
		pieces.put(coord, p);
		setLocationType(coord, LocationType.CLEAR);
		}
		
	}
	
	public void setLocationType(OrthoSquareCoordinate c, LocationType lt)
	{
		ortho.put(c, lt);
	}
	
	public LocationType getLocationType(OrthoSquareCoordinate coord) {
		
		if(ortho.get(coord) == null) {
			return LocationType.CLEAR;
		}
		return ortho.get(coord);
		
	}


}
