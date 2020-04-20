/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.board.coordinate;

import java.util.Objects;
import java.util.stream.Stream;
import escape.exception.EscapeException;

/**
 * Description
 * 
 * @version 13 abr. 2020
 */
public class HexCoordinate implements Coordinate
{
	private final int x;
	private final int y;

	/**
	 * Description
	 */
	private HexCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;

	}

	/**
	 * Create a new hex coordinate object with the given x and y coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return a newly created HexCoordinate object
	 */
	public static HexCoordinate makeCoordinate(int x, int y)
	{

		return new HexCoordinate(x, y);
	}

	/*
	 * @see
	 * escape.board.coordinate.Coordinate#distanceTo(escape.board.coordinate.Coordinate)
	 * implemented using the algorithm Found on:
	 * https://www.redblobgames.com/grids/hexagons/#distances on the distances section and
	 * axial coordinates sub-section
	 */
	@Override
	public int distanceTo(Coordinate c)	
	{
		if(c instanceof HexCoordinate) {
		HexCoordinate h = (HexCoordinate) c;
		return (Math.abs(this.x - h.x) + Math.abs(this.x + this.y - h.x - h.y)
				+ Math.abs(this.y - h.y)) / 2;
		}
		else {
			throw new EscapeException("wrong coordinate type");
		}

	}
	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}
	
	
	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(x, y);
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		
		if (!(obj instanceof HexCoordinate)) {
			return false;
		}
		HexCoordinate other = (HexCoordinate) obj;
		return x == other.getX() && y == other.getY();
	}

}
