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

import escape.board.coordinate.CoordinateID;

/**
 * Description
 * @version 18 abr. 2020
 */
public interface BoardFactory 
{
	public static Board createB(CoordinateID co,int x ,int y) {
		
		Board ret =  null;
		switch(co){
			case SQUARE:
				ret = new SquareBoard(x, y);
				
				break;
			case HEX:
				ret = new HexBoard(x, y);
				break;
				
			case ORTHOSQUARE:
				ret = new OrthoBoard(x, y);
				break;

		}
		return ret;	
	}

}
