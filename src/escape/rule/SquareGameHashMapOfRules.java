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

package escape.rule;

import java.util.*;
import escape.piece.MovementPatternID;
import escape.rule.SquareGameRules.Rules;

/**
 * Description
 * @version 29 abr. 2020
 */
public class SquareGameHashMapOfRules
{
	public HashMap<MovementPatternID,ArrayList<Rules>> movePattern = new HashMap<>();
	ArrayList<Rules> LinearRules = new ArrayList<Rules>();
	ArrayList<Rules> DiagonalRules = new ArrayList<Rules>();
	ArrayList<Rules> OmnilRules = new ArrayList<Rules>();
	ArrayList<Rules> OrthoRules = new ArrayList<Rules>();
	public SquareGameHashMapOfRules()
	{
	//Linear rules
	LinearRules.add(SquareGameRules.LinearDiagonal);
	LinearRules.add(SquareGameRules.LinearOrthogonal);
	//omni rules
	OmnilRules.add(SquareGameRules.OmniRules);
	//diagonal rules
	DiagonalRules.add(SquareGameRules.DiagonalRules);
	//ortho rules 
	OrthoRules.add(SquareGameRules.OrthoRules);
	
	movePattern.put(MovementPatternID.LINEAR, LinearRules);
	movePattern.put(MovementPatternID.DIAGONAL, DiagonalRules);
	movePattern.put(MovementPatternID.OMNI, OmnilRules);
	movePattern.put(MovementPatternID.ORTHOGONAL, OrthoRules);

	
	}


}
