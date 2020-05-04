/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/
package escape.board.coordinate;

import java.awt.List;
import java.util.*;
import escape.board.*;
import escape.board.coordinate.SquareBoardAStar.Node;
import escape.exception.EscapeException;
import escape.piece.MovementPatternID;
import escape.util.PieceTypeInitializer;
import escape.util.PieceTypeInitializer.PieceAttribute;

/**
 * This is an example of how a SquareCoordinate might be organized.
 * 
 * @version Mar 27, 2020
 */
public class SquareCoordinate implements Coordinate
{
	private final int x;
	private final int y;

	private SquareCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public static SquareCoordinate makeCoordinate(int x, int y)
	{
		return new SquareCoordinate(x, y);
	}

	/*
	 * @see
	 * escape.board.coordinate.Coordinate#distanceTo(escape.board.coordinate.Coordinate)
	 */
	@Override
	public int distanceTo(Coordinate c)
	{
		if (c instanceof SquareCoordinate) {
			SquareCoordinate f = (SquareCoordinate) c;
			int ro = Math.abs(this.x - f.getX());
			int co = Math.abs(this.y - f.getY());
			if (co > ro) {
				return co;
			} else
				return ro;
		} else {
			throw new EscapeException("comparing different types of coordinate");
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
	 * 
	 * FOR ORTHOGONAL MOVEMENT
	 * 
	 */
	
	/**
	 * Find if two coordinates are in the same Orthogonal path
	 * @param from
	 * @param to
	 * @return
	 */
	public  boolean sameOrthogonal(SquareCoordinate to) {
		//check if its horizontal
		if(this.getX() == to.getX() && this.getY() != to.getY()) {
			return true;
		}
		//check if its vertical 
		else if(this.getY() == to.getY() && this.getX() != to.getX()) {
			return true;
		}
		else return false;
	}
		
	/**
	 * Description calculate if the orthogonal is clear from other pieces 
	 * @param to Coordinate to destination
	 * @param b  board 
	 * @return true if the orthogonal is clear
	 */
	public boolean orthagonalIsClear(SquareCoordinate to,SquareBoard b) {
		
		//make sure the path is orthogonal
		if(!this.sameOrthogonal(to)) {
			return false;
		}
		// check all four direction options
		else{
			
			// when going horizontal and to the rigth 
			if(this.getX() == to.getX() && this.getY() < to.getY()) {
				int ypos = this.getY()+1; 
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(this.getX(),ypos))!= null && (b.getLocationType(makeCoordinate(this.getX(), ypos)) != LocationType.BLOCK)){
						return false;
					}
					ypos++;
				}
			}
			//when going horizontal and to the left
			else if(this.getX() == to.getX() && this.getY()> to.getY()) {
				int ypos = this.getY()-1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(this.getX(),ypos))!= null && (b.getLocationType(makeCoordinate(this.getX(), ypos)) != LocationType.BLOCK)){
						return false;
					}
					ypos--;
				}
			}
			//when going vertical and up 
			else if(this.getY() == to.getY() && this.getX() < to.getX()) {
				int xpos = this.getX()+1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,this.getY()))!= null && (b.getLocationType(makeCoordinate(xpos,this.getY())) != LocationType.BLOCK)){
						return false;
					}
					xpos++;
				}
			}
			//when going vertical and down 
			else if(this.getY() == to.getY() && this.getX() > to.getX()) {
				int xpos = this.getX()-1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,this.getY()))!= null && (b.getLocationType(makeCoordinate(xpos,this.getY())) != LocationType.BLOCK)){
						return false;
					}
					xpos--;
				}
			}
			
		}
		return true;
	}
	
	/**
	 * Description calculate if the orthogonal is clear from exits
	 * @param to Coordinate to destination
	 * @param b  board 
	 * @return true if the orthogonal is clear
	 */
	public boolean orthagonalIsExitClear(SquareCoordinate to,SquareBoard b) {
		
		//make sure the path is orthogonal
		if(!this.sameOrthogonal(to)) {
			return false;
		}
		// check all four direction options
		else{
			
			// when going horizontal and to the rigth 
			if(this.getX() == to.getX() && this.getY() < to.getY()) {
				int ypos = this.getY()+1; 
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if( (b.getLocationType(makeCoordinate(this.getX(), ypos)) == LocationType.EXIT)){
						return false;
					}
					ypos++;
				}
			}
			//when going horizontal and to the left
			else if(this.getX() == to.getX() && this.getY()> to.getY()) {
				int ypos = this.getY()-1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if((b.getLocationType(makeCoordinate(this.getX(), ypos)) == LocationType.EXIT)){
						return false;
					}
					ypos--;
				}
			}
			//when going vertical and up 
			else if(this.getY() == to.getY() && this.getX() < to.getX()) {
				int xpos = this.getX()+1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if((b.getLocationType(makeCoordinate(xpos,this.getY())) == LocationType.EXIT)){
						return false;
					}
					xpos++;
				}
			}
			//when going vertical and down 
			else if(this.getY() == to.getY() && this.getX() > to.getX()) {
				int xpos = this.getX()-1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if((b.getLocationType(makeCoordinate(xpos,this.getY())) == LocationType.EXIT)){
						return false;
					}
					xpos--;
				}
			}
			
		}
		return true;
	}
	
	/**
	 * Description calculate if the orthogonal is clear from other pieces 
	 * @param to Coordinate to destination
	 * @param b  board 
	 * @return true if the orthogonal is clear
	 */
	public boolean orthagonalIsUnblocked(SquareCoordinate to,SquareBoard b) {
		
		//make sure the path is orthogonal
		if(!this.sameOrthogonal(to)) {
			return false;
		}
		// check all four direction options
		else{
			
			// when going horizontal and to the rigth 
			if(this.getX() == to.getX() && this.getY() < to.getY()) {
				int ypos = this.getY()+1; 
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if( (b.getLocationType(makeCoordinate(this.getX(), ypos)) == LocationType.BLOCK)){
						return false;
					}
					ypos++;
				}
			}
			//when going horizontal and to the left
			else if(this.getX() == to.getX() && this.getY()> to.getY()) {
				int ypos = this.getY()-1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if((b.getLocationType(makeCoordinate(this.getX(), ypos)) == LocationType.BLOCK)){
						return false;
					}
					ypos--;
				}
			}
			//when going vertical and up 
			else if(this.getY() == to.getY() && this.getX() < to.getX()) {
				int xpos = this.getX()+1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if((b.getLocationType(makeCoordinate(xpos,this.getY())) == LocationType.BLOCK)){
						return false;
					}
					xpos++;
				}
			}
			//when going vertical and down 
			else if(this.getY() == to.getY() && this.getX() > to.getX()) {
				int xpos = this.getX()-1;
				for(int i = 0; i< this.distanceTo(to)-1;i++) {
					if((b.getLocationType(makeCoordinate(xpos,this.getY())) == LocationType.BLOCK)){
						return false;
					}
					xpos--;
				}
			}
			
		}
		return true;
	}
	/**
	 * check that can jump that path, one space at a time
	 * @param to
	 * @param b
	 * @return
	 */
	public boolean isJumpableOrthoPath(SquareCoordinate to, SquareBoard b) {
		//make sure the path is orthogonal
				if(!this.sameOrthogonal(to)) {
					return false;
				}
				// check all four direction options
				else{
					
					// when going horizontal and to the rigth 
					if(this.getX() == to.getX() && this.getY() < to.getY()) {
						int ypos = this.getY()+1; 
						int jumpCounter = 0;
						for(int i = 0; i< this.distanceTo(to)-1;i++) {
							if(b.getPieceAt(makeCoordinate(this.getX(),ypos))!= null){
								jumpCounter++;
							}
							else {
								jumpCounter--;
							}
							if(jumpCounter > 1) {
								return false;
							}
							ypos++;
						}
					}
					//when going horizontal and to the left
					else if(this.getX() == to.getX() && this.getY()> to.getY()) {
						int ypos = this.getY()-1;
						int jumpCounter = 0;
						for(int i = 0; i< this.distanceTo(to)-1;i++) {
							
							if(b.getPieceAt(makeCoordinate(this.getX(),ypos))!= null){
								jumpCounter++;
							}
							else {
								jumpCounter--;
							}
							if(jumpCounter >1) {
								return false;
							}
							ypos--;
						}
					}
					//when going vertical and up 
					else if(this.getY() == to.getY() && this.getX() < to.getX()) {
						int xpos = this.getX()+1;
						int jumpCounter= 0;
						for(int i = 0; i< this.distanceTo(to)-1;i++) {
							if(b.getPieceAt(makeCoordinate(xpos,this.getY()))!= null){
								jumpCounter++;
							}
							else {
								jumpCounter--;
							}
							if(jumpCounter >1) {
								return false;
							}
							xpos++;
						}
					}
					//when going vertical and down 
					else if(this.getY() == to.getY() && this.getX() > to.getX()) {
						int xpos = this.getX()-1;
						int jumpCounter = 0;
						for(int i = 0; i< this.distanceTo(to)-1;i++) {
							if(b.getPieceAt(makeCoordinate(xpos,this.getY()))!= null){
								jumpCounter++;
							}
							else {
								jumpCounter--;
							}
							if(jumpCounter >1) {
								return false;
							}
							xpos--;
						}
					}
					
				}
				return true;
	}
	/*
	 * 
	 * FOR ORTHOGONAL MOVEMENT
	 * 
	 */
	/*
	 * 
	 * FOR DIAGONAL MOVEMENT
	 * 
	 */
	/**
	 *Find if coordinates are in the same digonal 
	 * @param to
	 * @return
	 */
	public boolean sameDiagonal(SquareCoordinate to) {
		
		if(Math.abs((this.getX() - to.getX())) == Math.abs((this.getY() - to.getY()))){
			return true;
		}
		else return false;
	}
	
	/**
	 * Check the diagonal betwen this and the given coordinate 
	 * is empty
	 * @param to end coordinate 
	 * @param b a board 
	 * @return true if the diagonal is clear false otherwise 
	 */
	public boolean diagonalIsClear(SquareCoordinate to,SquareBoard b) {
		// make sure the path is a diagonal 
		if(!this.sameDiagonal(to)) {
			return false;
		}
		// check all four direction options

		else {
			
			//when the destination Coordinate is larger both in X and Y
			if(this.getX() < to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()+1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos++;
					ypos++;
			
				}
				
			}
			//when the destination Coordinate is larger in X and not in Y 
			else if(this.getX() < to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()-1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos++;
					ypos--;
				}	
			}
			//when  both X and Y less in the destination Coordinate
			else if(this.getX() > to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()-1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos--;
					ypos--;
				}	
			}
			//when the y is larger but the x is not on the destination Coordinate 
			else if(this.getX() > to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()+1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos--;
					ypos++;
				}	
			}	
		}
		
		return true;
	}
	
	/**
	 * check if it is a jumpable path 
	 * @param to
	 * @param b
	 * @return true when can jump that path 
	 */
	public boolean isJumpableDiagonalPath(SquareCoordinate to,SquareBoard b) {
		// make sure the path is a diagonal 
		if(!this.sameDiagonal(to)) {
			return false;
		}
		// check all four direction options

		else {
			 
			//when the destination Coordinate is larger both in X and Y
			if(this.getX() < to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()+1;
				int jumpCounter = 0;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						jumpCounter++;
					}
					else {
						jumpCounter--;
					}
					if(jumpCounter > 1) {
						return false;
					}
					xpos++;
					ypos++;
			
				}
				
			}
			//when the destination Coordinate is larger in X and not in Y 
			else if(this.getX() < to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()-1;
				int jumpCounter = 0;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						jumpCounter++;
					}
					else {
						jumpCounter--;
					}
					if(jumpCounter > 1) {
						return false;
					}
					xpos++;
					ypos--;
				}	
			}
			//when  both X and Y less in the destination Coordinate
			else if(this.getX() > to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()-1;
				int jumpCounter =0;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						jumpCounter++;
					}
					else {
						jumpCounter--;
					}
					if(jumpCounter > 1) {
						return false;
					}
					xpos--;
					ypos--;
				}	
			}
			//when the y is larger but the x is not on the destination Coordinate 
			else if(this.getX() > to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()+1;
				int jumpCounter = 0;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						jumpCounter++;
					}
					else {
						jumpCounter--;
					}
					if(jumpCounter > 1) {
						return false;
					}
					xpos--;
					ypos++;
				}	
			}	
		}
		
		return true;
	}
	
	/**
	 * check that the diagonal path is unblocked
	 * @param to
	 * @param b
	 * @return true when is unlocked 
	 */
	public boolean diagonalIsUnblocked(SquareCoordinate to,SquareBoard b) {
		// make sure the path is a diagonal 
		if(!this.sameDiagonal(to)) {
			return false;
		}
		// check all four direction options

		else {
			
			//when the destination Coordinate is larger both in X and Y
			if(this.getX() < to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()+1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.BLOCK){
						return false;
					}
					xpos++;
					ypos++;
			
				}
				
			}
			//when the destination Coordinate is larger in X and not in Y 
			else if(this.getX() < to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()-1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.BLOCK){
						return false;
					}
					xpos++;
					ypos--;
				}	
			}
			//when  both X and Y less in the destination Coordinate
			else if(this.getX() > to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()-1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.BLOCK){
						return false;
					}
					xpos--;
					ypos--;
				}	
			}
			//when the y is larger but the x is not on the destination Coordinate 
			else if(this.getX() > to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()+1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.BLOCK){
						return false;
					}
					xpos--;
					ypos++;
				}	
			}	
		}
		
		return true;
	}
	/**
	 * check that the diagonal path is unblocked
	 * @param to
	 * @param b
	 * @return true when is unlocked 
	 */
	public boolean diagonalIsExitClear(SquareCoordinate to,SquareBoard b) {
		// make sure the path is a diagonal 
		if(!this.sameDiagonal(to)) {
			return false;
		}
		// check all four direction options

		else {
			
			//when the destination Coordinate is larger both in X and Y
			if(this.getX() < to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()+1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.EXIT){
						return false;
					}
					xpos++;
					ypos++;
			
				}
				
			}
			//when the destination Coordinate is larger in X and not in Y 
			else if(this.getX() < to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()+1;
				int ypos = this.getY()-1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.EXIT){
						return false;
					}
					xpos++;
					ypos--;
				}	
			}
			//when  both X and Y less in the destination Coordinate
			else if(this.getX() > to.getX() && this.getY() > to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()-1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.EXIT){
						return false;
					}
					xpos--;
					ypos--;
				}	
			}
			//when the y is larger but the x is not on the destination Coordinate 
			else if(this.getX() > to.getX() && this.getY() < to.getY()) {
				int xpos = this.getX()-1;
				int ypos = this.getY()+1;
				for(int i = 0;i < this.distanceTo(to)-1;i++) {
					if(b.getLocationType(makeCoordinate(xpos, ypos))== LocationType.EXIT){
						return false;
					}
					xpos--;
					ypos++;
				}	
			}	
		}
		
		return true;
	}
	

	/*
	 * 
	 * FOR DIAGONAL MOVEMENT
	 * 
	 */
	
	
	public boolean PathFind(SquareBoard b,SquareCoordinate to,MovementPatternID pattern,PieceAttribute[] p) {
		
		switch(pattern) {
			
			case OMNI:
				SquareBoardAStar star = new SquareBoardAStar(b, this,p);
				ArrayList<Node> l = star.findPathToOmni(to.getX(),to.getY());
				if( l != null && l.size()-1 <= PieceTypeInitializer.getMaxDistance(p)){
				return true;
				}
				else {
					return false;
				}
			case DIAGONAL:
				SquareBoardAStar stard = new SquareBoardAStar(b,this,p);
				ArrayList<Node> f = stard.findPathToDiagonal(to.getX(),to.getY());
				if( f != null && f.size()-1 <= PieceTypeInitializer.getMaxDistance(p)){
				return true;
				}
				else {
					return false;
				}
			case ORTHOGONAL:
				SquareBoardAStar staro = new SquareBoardAStar(b,this,p);
				ArrayList<Node> o = staro.findPathToOrtho(to.getX(),to.getY());
				if( o != null && o.size()-1 <= PieceTypeInitializer.getMaxDistance(p)){
				return true;
				}
				else {
					return false;
				}
				
		}
		
		return false;
		
	}


	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{

		if (!(obj instanceof SquareCoordinate)) {
			return false;
		}
		SquareCoordinate other = (SquareCoordinate) obj;
		return x == other.getX() && y == other.getY();
	}

}
