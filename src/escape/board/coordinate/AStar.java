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

package escape.board.coordinate;

import java.util.*;
import escape.board.*;
import escape.util.PieceTypeInitializer;
import escape.util.PieceTypeInitializer.PieceAttribute;

/**
 * Description using alogrithm explanide here:
 * https://rosettacode.org/wiki/A*_search_algorithm#Java
 * @version 1 may. 2020
 */
public class AStar
{
	private final ArrayList<Node> open;
    private final ArrayList<Node> closed;
    private final ArrayList<Node> path;
    private final SquareBoard b;
    private Node now;
    private PieceAttribute[] p;
    private final int xstart;
    private final int ystart;
    private int xend, yend;
 
    // Node class for convienience
    static class Node implements Comparable {
        public Node parent;
        public int x, y;
        public double g;
        public double h;
        Node(Node parent, SquareCoordinate coord, double g, double h) {
            this.parent = parent;
            this.x = coord.getX();
            this.y = coord.getY();
            this.g = g;
            this.h = h;
       }
       // Compare by f value (g + h)
       @Override
       public int compareTo(Object o) {
           Node that = (Node) o;
           return (int)((this.g + this.h) - (that.g + that.h));
       }
   }
 
    AStar(SquareBoard b, SquareCoordinate coord, PieceAttribute[] p) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.b = b;
        this.now = new Node(null, coord, 0, 0);
        this.xstart = coord.getX();
        this.ystart = coord.getY();
        this.p = p;
    }
    /*
    ** Finds path to xend/yend or returns null
    **
    ** @param (int) xend coordinates of the target position
    ** @param (int) yend
    ** @return (List<Node> | null) the path
    */
    public ArrayList<Node> findPathToOmni(int xend, int yend) {
    	
        this.xend = xend;
        this.yend = yend;
        this.closed.add(this.now);
        addNeigborsToOpenListOmni();
        while (this.now.x != this.xend || this.now.y != this.yend) {
            if (this.open.isEmpty()) { // Nothing to examine
                return null;
            }
            this.now = this.open.get(0); // get first node (lowest f score)
            this.open.remove(0); // remove it
            this.closed.add(this.now); // and add to the closed
            addNeigborsToOpenListOmni();
        }
        this.path.add(0, this.now);
        while (this.now.x != this.xstart || this.now.y != this.ystart) {
            this.now = this.now.parent;
            this.path.add(0, this.now);
        }
        if(constectuivePieces(this.path) && !PieceTypeInitializer.canFly(p)) {
        	return null;
        }

        return this.path;
    }
    /*
     ** Finds path to xend/yend or returns null
     **
     ** @param (int) xend coordinates of the target position
     ** @param (int) yend
     ** @return (List<Node> | null) the path
     */
     public ArrayList<Node> findPathToDiagonal(int xend, int yend) {
     	
         this.xend = xend;
         this.yend = yend;
         this.closed.add(this.now);
         addNeigborsToOpenListDiagonal();
         while (this.now.x != this.xend || this.now.y != this.yend) {
             if (this.open.isEmpty()) { // Nothing to examine
                 return null;
             }
             this.now = this.open.get(0); // get first node (lowest f score)
             this.open.remove(0); // remove it
             this.closed.add(this.now); // and add to the closed
             addNeigborsToOpenListDiagonal();
         }
         this.path.add(0, this.now);
         while (this.now.x != this.xstart || this.now.y != this.ystart) {
             this.now = this.now.parent;
             this.path.add(0, this.now);
         }
         if(constectuivePieces(this.path) && !PieceTypeInitializer.canFly(p)){
         	return null;
         }
 
         return this.path;
     }
     
     /*
      ** Finds path to xend/yend or returns null
      **
      ** @param (int) xend coordinates of the target position
      ** @param (int) yend
      ** @return (List<Node> | null) the path
      */
      public ArrayList<Node> findPathToOrtho(int xend, int yend) {
      	
          this.xend = xend;
          this.yend = yend;
          this.closed.add(this.now);
          addNeigborsToOpenListOrtho();
          while (this.now.x != this.xend || this.now.y != this.yend) {
              if (this.open.isEmpty()) { // Nothing to examine
                  return null;
              }
              this.now = this.open.get(0); // get first node (lowest f score)
              this.open.remove(0); // remove it
              this.closed.add(this.now); // and add to the closed
              addNeigborsToOpenListOrtho();
          }
          this.path.add(0, this.now);
          while (this.now.x != this.xstart || this.now.y != this.ystart) {
              this.now = this.now.parent;
              this.path.add(0, this.now);
          }
          if(constectuivePieces(this.path) && !PieceTypeInitializer.canFly(p) ){
          	return null;
          }

          return this.path;
      }
    /*
    ** Looks in a given List<> for a node
    **
    ** @return (bool) NeightborInListFound
    */
    private static boolean findNeighborInList(ArrayList<Node> array, Node node) {
        return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
    }
    /*
    ** Calulate distance between this.now and xend/yend
    **
    ** @return (int) distance
    */

    private void addNeigborsToOpenListOmni() {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                node = new Node(this.now, SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y), this.now.g,(double)SquareCoordinate.makeCoordinate(now.x, now.y).distanceTo(SquareCoordinate.makeCoordinate(x, y)));
                if ((x != 0 || y != 0) // not this.now
                    && this.now.x + x >= 1 && this.now.x + x < b.getXMax() // check maze boundaries
                    && this.now.y + y >= 1 && this.now.y + y < b.getYMax()
                    && 
                    (
                    //check location is not  blocked or can fly or can unlblock
                    b.getLocationType(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) != LocationType.BLOCK ||
                    PieceTypeInitializer.canUnblock(p) ||
                    PieceTypeInitializer.canFly(p) 
                    )
                    &&
                    (
                    //location it wants to go to is empty 
                    b.getPieceAt(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) == null ||
                    // can jump or the location it comes from is empty ot itself 
                    PieceTypeInitializer.canJump(p)
                    ||
                    PieceTypeInitializer.canFly(p)
                    || (SquareCoordinate.makeCoordinate(xend, yend).equals(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) && b.getPieceAt(SquareCoordinate.makeCoordinate(xend, yend)).getPlayer() != b.getPieceAt(SquareCoordinate.makeCoordinate(xstart, ystart)).getPlayer())
                	)
                    && // location is an exit unless is last one 
                    (b.getLocationType(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) != LocationType.EXIT || PieceTypeInitializer.canFly(p)|| SquareCoordinate.makeCoordinate(xend, yend).equals(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)))
                    && SquareCoordinate.makeCoordinate(node.x, node.y).distanceTo(SquareCoordinate.makeCoordinate(now.x,now.y)) <= PieceTypeInitializer.getMaxDistance(p)
                    && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not already done
                        node.g = node.parent.g + 1; // Horizontal/vertical cost = 1.0
                        node.g += (double)SquareCoordinate.makeCoordinate(now.x, now.y).distanceTo(SquareCoordinate.makeCoordinate(x, y));
                     // add movement cost for this square

                        this.open.add(node);
                }
            }
        }
        Collections.sort(this.open);
    }
    
    private void addNeigborsToOpenListDiagonal() {
        Node node;
        for (int x = -1; x < 2; x=x+2) {
            for (int y = -1; y < 2; y = y+2) {
                node = new Node(this.now, SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y), this.now.g,(double)SquareCoordinate.makeCoordinate(now.x, now.y).distanceTo(SquareCoordinate.makeCoordinate(x, y)));
                if ((x != 0 || y != 0) // not this.now
                    && this.now.x + x >= 1 && this.now.x + x < b.getXMax() // check maze boundaries
                    && this.now.y + y >= 1 && this.now.y + y < b.getYMax()
                    && 
                    (
                    //check location is not  blocked or can fly or can unlblock
                    b.getLocationType(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) != LocationType.BLOCK ||
                    PieceTypeInitializer.canUnblock(p) ||
                    PieceTypeInitializer.canFly(p) 
                    )
                    &&
                    (
                    //location it wants to go to is empty 
                    b.getPieceAt(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) == null ||
                    // can jump or the location it comes from is empty ot itself 
                    PieceTypeInitializer.canJump(p) 
                    ||
                    PieceTypeInitializer.canFly(p)
                    || (SquareCoordinate.makeCoordinate(xend, yend).equals(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) && b.getPieceAt(SquareCoordinate.makeCoordinate(xend, yend)).getPlayer() != b.getPieceAt(SquareCoordinate.makeCoordinate(xstart, ystart)).getPlayer())
                	)
                    && (b.getLocationType(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) != LocationType.EXIT || PieceTypeInitializer.canFly(p) || SquareCoordinate.makeCoordinate(xend, yend).equals(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)))
                    && SquareCoordinate.makeCoordinate(node.x, node.y).distanceTo(SquareCoordinate.makeCoordinate(now.x,now.y)) <= PieceTypeInitializer.getMaxDistance(p)
                    && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not already done
                        node.g = node.parent.g + 1; // Horizontal/vertical cost = 1.0
                        node.g += (double)SquareCoordinate.makeCoordinate(now.x, now.y).distanceTo(SquareCoordinate.makeCoordinate(x, y));
                     // add movement cost for this square

                        this.open.add(node);
                }
            }
        }
        Collections.sort(this.open);
    }
    
    private void addNeigborsToOpenListOrtho() {
        Node node;
        for (int x = -1 ; x < 2; x++) {
            for (int y = -1; y < 2;y++) {
            	if ( x != 0 && y != 0) {
                    continue; // skip if diagonal movement is not allowed
                }
     
                node = new Node(this.now, SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y), this.now.g,(double)SquareCoordinate.makeCoordinate(now.x, now.y).distanceTo(SquareCoordinate.makeCoordinate(x, y)));
                if ((x != 0 || y != 0) // not this.now
                    && this.now.x + x >= 1 && this.now.x + x < b.getXMax() // check maze boundaries
                    && this.now.y + y >= 1 && this.now.y + y < b.getYMax()
                    && 
                    (
                    //check location is not  blocked or can fly or can unlblock
                    b.getLocationType(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) != LocationType.BLOCK ||
                    PieceTypeInitializer.canUnblock(p) ||
                    PieceTypeInitializer.canFly(p) 
                    )
                    &&
                    (
                    //location it wants to go to is empty 
                    b.getPieceAt(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) == null ||
                    // can jump or the location it comes from is empty ot itself 
                    PieceTypeInitializer.canJump(p) 
                    ||
                    PieceTypeInitializer.canFly(p)
                    || (SquareCoordinate.makeCoordinate(xend, yend).equals(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) && b.getPieceAt(SquareCoordinate.makeCoordinate(xend, yend)).getPlayer() != b.getPieceAt(SquareCoordinate.makeCoordinate(xstart, ystart)).getPlayer())
                	)
                    && (b.getLocationType(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)) != LocationType.EXIT || PieceTypeInitializer.canFly(p) || SquareCoordinate.makeCoordinate(xend, yend).equals(SquareCoordinate.makeCoordinate(this.now.x + x, this.now.y + y)))
                    && SquareCoordinate.makeCoordinate(node.x, node.y).distanceTo(SquareCoordinate.makeCoordinate(now.x,now.y)) <= PieceTypeInitializer.getMaxDistance(p)
                    && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not already done
                        node.g = node.parent.g + 1; // Horizontal/vertical cost = 1.0
                        node.g += (double)SquareCoordinate.makeCoordinate(now.x, now.y).distanceTo(SquareCoordinate.makeCoordinate(x, y));
                     // add movement cost for this square

                        this.open.add(node);
                }
            
            }
        }
        Collections.sort(this.open);
    }
    
    /**
     * check for consecutive pieces or if it is the last two and it can jump and the
     * last piece is an enemy then it can jump and kill
     * @param arr
     * @return
     */
    private boolean constectuivePieces(ArrayList<Node> arr) {
    	for(int i = 0;i < arr.size()-2;i++) {
    		if(b.getPieceAt(SquareCoordinate.makeCoordinate(arr.get(i).x,arr.get(i).y)) != null 
    				&& b.getPieceAt(SquareCoordinate.makeCoordinate(arr.get(i+1).x,arr.get(i+1).y)) != null
    				&& b.getPieceAt(SquareCoordinate.makeCoordinate(arr.get(i+2).x,arr.get(i+2).y))!= null){
    			
    			if(b.getPieceAt(SquareCoordinate.makeCoordinate(arr.get(arr.size()-2).x,arr.get(path.size()-2).y)) != null
    			        	&&b.getPieceAt(SquareCoordinate.makeCoordinate(arr.get(arr.size()-1).x,arr.get(arr.size()-1).y)) != null
    			        	&& b.getPieceAt(SquareCoordinate.makeCoordinate(arr.get(arr.size()-1).x,arr.get(arr.size()-1).y)).getPlayer() !=
    			        			b.getPieceAt(SquareCoordinate.makeCoordinate(xstart,ystart)).getPlayer()) {
    				return false;
    			}
    			return true;
    					
    				}
    	}
    	return false;
	
    }
 
}
