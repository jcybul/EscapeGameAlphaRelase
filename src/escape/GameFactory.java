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

import escape.board.*;
import escape.board.coordinate.*;
/**
 * Description
 * @version 28 abr. 2020
 */
public interface GameFactory
{
	
	
	/**
	 * Create the aproppiate game given the board and Coordinate Type 
	 * @param id
	 * @param b
	 * @return
	 */
	public static EscapeGameManager CreateGame(CoordinateID id,Board b) {
		
		EscapeGameManager ret = null;
		switch (id) {
			// switch depending on the CoordinateID
			case SQUARE:
				ret = new SquareGame(b) {
				};
				break;
			case HEX:
				ret = new HexGame(b);
				break;

			case ORTHOSQUARE:
				ret = new OrthoGame(b);
				break;
		}
		return ret;
	}
		
		
	}


