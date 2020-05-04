/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2020 Gary F. Pollice
 *******************************************************************************/

package escape;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
import escape.board.coordinate.*;
import escape.piece.*;

/**
 * Description
 * @version Apr 24, 2020
 */
class BetaEscapeGameTests
{
    /**
     * Example of how the game manager tests will be structured.
     * @throws Exception
     */
	// move  a frog from a position to other on a clean path trough diagonal
    @Test
    void LinearDiagonal1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(7, 7)));
        
    }
    
    @Test
    void LinearOrthogonal1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(5, 1)));

        
    }
    @Test
    void LinearRandom() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(8, 3)));
        
    }
    @Test
    void LinearMoreThanDistance() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(5, 11)));
    }
    
    @Test
    void JumoOverOnePiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
    }
    
    @Test
    void JumoOverTwoPieces() throws Exception
    {
    	
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(3,5), emg.makeCoordinate(3, 2)));
    }
    @Test
    void FlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(7,4), emg.makeCoordinate(7, 1)));
    }
    
    @Test
    void UnblockAPosition() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(1,5), emg.makeCoordinate(1,2)));
    }
    @Test
    void FlyOverBlocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(4,8), emg.makeCoordinate(1,8)));
    }
    
    @Test
    void LandOnOverBlocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(4,8), emg.makeCoordinate(3,8)));
    }
    
    @Test
    void DiagonalJumpOverPiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,3), emg.makeCoordinate(3,1)));
        //since is true lets test the move was made
        EscapePiece frog = new EscapePiece(Player.PLAYER2,PieceName.FROG);
        assertTrue(emg.getPieceAt((emg.makeCoordinate(5, 3))) == null);
        assertTrue(frog.equals(emg.getPieceAt(emg.makeCoordinate(3, 1))));
    }
    
    @Test
    void DiagonalJumpOverTwoPiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(5,8), emg.makeCoordinate(2,5)));
    }
    
    @Test
    void DiagonalFlyOverTwoPiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(8,7), emg.makeCoordinate(11,4)));
    }
    
    @Test
    void DiagonalBlockedPosition() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(8,2), emg.makeCoordinate(10,4)));
    }
    
    @Test
    void DiagonalFlyOverBlockedPosition() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,6), emg.makeCoordinate(13,9)));
    }
    
    
    /*
     * Diagonal on square board 
     */
    
    @Test
    void Diagonaldir1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(7,3)));
    }
    @Test
    void Diagonaldir2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(3,3)));
    }

    @Test
    void Diagonaldir3() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(7,7)));
    }

    @Test
    void Diagonaldir4() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(3,7)));
    }
   
    @Test
    void Diagonaljumpone1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(12,3)));
    }
    @Test
    void Diagonaljumpone1DirChange() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(9,15), emg.makeCoordinate(12,14)));
    }
    
    @Test
    void Diagonaljumpone2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(12,7)));
    }
    @Test
    void Diagonaljumpone3() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(8,7)));
    }
    @Test
    void Diagonaljumpone4() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(8,3)));
    }

    @Test
    void DiagonaljumponeTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(2,1), emg.makeCoordinate(5,4)));
    }
    
    @Test
    void DiagonalFlyTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,6), emg.makeCoordinate(8,9)));
    }
    
    
    @Test
    void DiagonalRandom() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(7,8), emg.makeCoordinate(6,8)));
    }
    
    @Test
    void DiagonalUnblockAbility() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,5), emg.makeCoordinate(1,7)));
    }
    
    @Test
    void DiagonalFlyOverBlock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,9), emg.makeCoordinate(12,11)));
    }
    
    @Test
    void JumpOverTwoNoneDiagonalPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(13,8), emg.makeCoordinate(18,13)));
    }
    
    
    //OMNI ON A SQUARE BOARD
    @Test
    void PathFindTest1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(2,8), emg.makeCoordinate(5,8)));
    }
    
    @Test
    void PathFindNoPath() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(17,14), emg.makeCoordinate(17,12)));
    }
    
    @Test
    void TooFar() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(16,6)));
    }
    
    @Test
    void pathFindJumpOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(11,14), emg.makeCoordinate(11,12)));
    }
    
    @Test
    void pathFindJumpTwoNonConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(2,2), emg.makeCoordinate(6,2)));
    }
    
    @Test
    void pathFindJumpTwoConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(3,12), emg.makeCoordinate(7,12)));
    }
    @Test
    void pathFindUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,16), emg.makeCoordinate(5,16)));
    }
    
    @Test
    void FlyoverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(8,7), emg.makeCoordinate(8,4)));
    }
    
   //ORTHO ON SQUARE BOARD
    
    @Test
    void pathFindOrtho() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(6,7), emg.makeCoordinate(8,5)));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /*
     * ORTHO GAME TEST
     */
    
    // ortho 
    @Test
    void OrthoPathFind() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,17), emg.makeCoordinate(6,15)));
    }
    
    @Test
    void OrthoPathUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(4,6), emg.makeCoordinate(6,6)));
    }
    @Test
    void OrthoPathLandOnBlock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,9), emg.makeCoordinate(12,8)));
    }
    @Test
    void OrthoPathTooFar() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,14), emg.makeCoordinate(14,14)));
    }
    
    @Test
    void OrthoPathFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(15,4), emg.makeCoordinate(18,4)));
    }
    
    @Test
    void OrthoPathJumpTwoPiecesNonConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(15,10), emg.makeCoordinate(17,8)));
    }
    @Test
    void OrthoPathExitInPath() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(9,18), emg.makeCoordinate(11,18)));
    }
    @Test
    void OrthoPathExitInPathFly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,1), emg.makeCoordinate(5,1)));
    }
    @Test
    void OrthoPathJumpTwoToAttack() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,3), emg.makeCoordinate(8,3)));
    }
    @Test
    void OrthoPathLandOnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(16,16), emg.makeCoordinate(18,17)));
      //check piece has been removed
        assertNull(emg.getPieceAt(emg.makeCoordinate(18,17)));
    }
    
    @Test
    void OrthoPathAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,10), emg.makeCoordinate(8,11)));

    }
    @Test
    void OrthoPathAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(7,10), emg.makeCoordinate(7,11)));

    }
    
    
    
    
    // omni
    
    @Test
    void OrthoPathFindOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,17), emg.makeCoordinate(6,15)));
    }
    
    @Test
    void OrthoPathUnblockOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(4,6), emg.makeCoordinate(6,6)));
    }
    @Test
    void OrthoPathLandOnBlockOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,9), emg.makeCoordinate(12,8)));
    }
    @Test
    void OrthoPathTooFarOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,14), emg.makeCoordinate(14,14)));
    }
    
    @Test
    void OrthoPathFlyOverTwoPiecesOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(15,4), emg.makeCoordinate(18,4)));
    }
    
    @Test
    void OrthoPathJumpTwoPiecesNonConsecutiveOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(15,10), emg.makeCoordinate(17,8)));
    }
    @Test
    void OrthoPathExitInPathOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(9,18), emg.makeCoordinate(11,18)));
    }
    @Test
    void OrthoPathExitInPathFlyOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,1), emg.makeCoordinate(5,1)));
    }
    @Test
    void OrthoPathJumpTwoToAttackOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,3), emg.makeCoordinate(8,3)));
    }
    
    @Test
    void OrthoPathLandOnExitOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(16,16), emg.makeCoordinate(18,17)));
        //check piece has been removed
        assertNull(emg.getPieceAt(emg.makeCoordinate(18,17)));
    }
    
    @Test
    void OrthoPathAttackAllyOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,10), emg.makeCoordinate(8,11)));

    }
    
    @Test
    void OrthoPathAttackEnemyOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(7,10), emg.makeCoordinate(7,11)));

    }
    
    
    //linear ortho 
    @Test
    void OrthoPathFindLinearFourDir() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(11,8)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(11,4)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(13,6)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(9,6)));
    }
    
    @Test
    void OrthoPathFindLinearUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(12,15), emg.makeCoordinate(14,15)));
 
    }
    @Test
    void OrthoPathFindLinearLandOnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertFalse(emg.move(emg.makeCoordinate(5,14), emg.makeCoordinate(5,16)));
        
    }
    @Test
    void OrthoPathFindLinearMaxDistanceAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertFalse(emg.move(emg.makeCoordinate(14,3), emg.makeCoordinate(20,3)));
        
    }
    @Test
    void OrthoPathFindLinearFlyTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(17,13), emg.makeCoordinate(17,16)));
        
    }
    @Test
    void OrthoPathFindLinearJumpTwoNonConsecutivePieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(3,3), emg.makeCoordinate(7,3)));
        
    }
    @Test
    void OrthoPathFindLinearJumpExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        
        assertFalse(emg.move(emg.makeCoordinate(9,15), emg.makeCoordinate(9,17)));
        
    }
    
    @Test
    void OrthoPathFindLinearFlyExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        
        assertTrue(emg.move(emg.makeCoordinate(3,14), emg.makeCoordinate(3,16)));
        
    }
    @Test
    void OrthoPathFindLinearLandExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(16,6), emg.makeCoordinate(16,7)));
      //check piece has been removed
        assertNull(emg.getPieceAt(emg.makeCoordinate(16,7)));
    }
    
    @Test
    void OrthoPathFindLinearAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        
        assertTrue(emg.move(emg.makeCoordinate(7,7), emg.makeCoordinate(7,8)));
        
    }
    
    @Test
    void OrthoPathFindLinearAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame); 
        assertFalse(emg.move(emg.makeCoordinate(6,7), emg.makeCoordinate(6,8)));    
    }
    
    @Test
    void OrthoPathJumpAndCapture() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame); 
        assertTrue(emg.move(emg.makeCoordinate(7,13), emg.makeCoordinate(7,15)));
        
    }

    
    
    /*
     * ORTHO GAME TEST
     */
    
    @Test
	void OrthoSquareOrthoMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/OrthoSquareOrthoEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(4, 2)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 2)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece at time, multi times -> true
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(6, 2)));
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 4)));
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 3)));
		
		//unblock true -> can pass over  block
		assertTrue(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 5)));
		
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(2, 7), emg.makeCoordinate(4, 7)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(1, 6)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(4, 7)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(8, 7)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(3, 5)));
	   
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(6, 3), emg.makeCoordinate(8, 8)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(6, 3), emg.makeCoordinate(8, 5)));
	    
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(8, 5)));
	    
	    //can't move diagonal
	    assertFalse(emg.move(emg.makeCoordinate(4, 4), emg.makeCoordinate(8, 8)));
	    
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(10, 1), emg.makeCoordinate(10, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(9, 1), emg.makeCoordinate(9, 6)));
	}
    
    
	@Test
	void SquareDiagonalMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/SquareDiagonalEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 1), emg.makeCoordinate(4, 4)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 4)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 4)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 4)));
		emg = egb.makeGameManager();//reset board
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 3)));
		
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 1), emg.makeCoordinate(3, 3)));
	 	
	 	emg = egb.makeGameManager();//reset board
	 	
	 	//jump over one piece at time, multi times -> true
	 	assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(7, 3)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(2, 6), emg.makeCoordinate(4, 8)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(3, 3), emg.makeCoordinate(2, 4)));
		
		//unblock true -> can pass over block
		assertTrue(emg.move(emg.makeCoordinate(3, 3), emg.makeCoordinate(1, 5)));
		
		emg = egb.makeGameManager();//reset board
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(9, 9), emg.makeCoordinate(11, 11)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(8, 8), emg.makeCoordinate(7, 9)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(8, 8), emg.makeCoordinate(10, 12)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(8, 8), emg.makeCoordinate(14, 14)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(8, 8), emg.makeCoordinate(13, 13)));
	  
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(16, 1), emg.makeCoordinate(21, 6)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(16, 1), emg.makeCoordinate(20, 5)));
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(20, 5)));
	    
	    emg = egb.makeGameManager();//reset board
	    //can't move non-diagonal
	    assertFalse(emg.move(emg.makeCoordinate(7, 2), emg.makeCoordinate(7, 1)));
	    assertFalse(emg.move(emg.makeCoordinate(7, 2), emg.makeCoordinate(8, 2))); 
	    assertFalse(emg.move(emg.makeCoordinate(7, 2), emg.makeCoordinate(7, 3)));
	    assertFalse(emg.move(emg.makeCoordinate(7, 2), emg.makeCoordinate(6, 2)));
	    
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(30, 1), emg.makeCoordinate(34, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(22, 1), emg.makeCoordinate(27, 6)));
	  
	}
	
	@Test
	void SquareOmniMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/SquareOmniEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(4, 2)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 2)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece at time, multi times -> true
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(6, 2)));
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 4)));
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 3)));
		
		//unblock true -> can pass over  block
		assertTrue(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 5)));
		
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(2, 7), emg.makeCoordinate(4, 7)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(1, 6)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(4, 7)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(8, 2)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(6, 2)));
	    
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(6, 1), emg.makeCoordinate(8, 8)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(6, 1), emg.makeCoordinate(8, 5)));
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(8, 5)));
	    
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(11, 1), emg.makeCoordinate(11, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(9, 1), emg.makeCoordinate(9, 6)));
	    
	}
	
	@Test
	void SquareLinearMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/SquareLinearEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(4, 2)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 2)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece at time, multi times -> true
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(6, 2)));
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 4)));
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 3)));
		
		//unblock true -> can pass over  block
		assertTrue(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 5)));
		
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(2, 7), emg.makeCoordinate(4, 7)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(1, 6)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(4, 7)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(8, 7)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(6, 2)));
	   
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(8, 8)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(8, 5)));
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(8, 5)));
	    
	    //cant do multi directions
	    assertFalse(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(6, 4)));
	    assertFalse(emg.move(emg.makeCoordinate(8, 6), emg.makeCoordinate(4, 5)));
	    
	    //can move diagonal
	    assertTrue(emg.move(emg.makeCoordinate(4, 4), emg.makeCoordinate(8, 8)));
	    
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(10, 1), emg.makeCoordinate(10, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(9, 1), emg.makeCoordinate(9, 6)));
	}
	
	@Test
	void SquareOrthoMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/SquareOrthoEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(4, 2)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 2)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece at time, multi times -> true
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(6, 2)));
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 4)));
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 3)));
		
		//unblock true -> can pass over  block
		assertTrue(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 5)));
		
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(2, 7), emg.makeCoordinate(4, 7)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(1, 6)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(4, 7)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(8, 7)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(3, 5)));
	   
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(6, 3), emg.makeCoordinate(8, 8)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(6, 3), emg.makeCoordinate(8, 5)));
	    
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(8, 5)));
	    
	    //can't move diagonal
	    assertFalse(emg.move(emg.makeCoordinate(4, 4), emg.makeCoordinate(8, 8)));
	    
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(10, 1), emg.makeCoordinate(10, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(9, 1), emg.makeCoordinate(9, 6)));
	}
    
	@Test
	void OrthoSquareOmniMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/OrthoSquareOmniEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(4, 2)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 2)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece at time, multi times -> true
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(6, 2)));
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 4)));
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 3)));
		
		//unblock true -> can pass over  block
		assertTrue(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 5)));
		
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(2, 7), emg.makeCoordinate(4, 7)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(1, 6)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(4, 7)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(8, 7)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(3, 5)));
	   
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(6, 3), emg.makeCoordinate(8, 8)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(6, 3), emg.makeCoordinate(8, 5)));
	    
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(8, 5)));
	    
	    //can't move diagonal
	    assertFalse(emg.move(emg.makeCoordinate(4, 4), emg.makeCoordinate(8, 8)));
	    
	    emg = egb.makeGameManager();//reset board
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(10, 1), emg.makeCoordinate(10, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(9, 1), emg.makeCoordinate(9, 6)));
	    
	}
	
	@Test
	void OrthoSquareLinearMasterTest() throws Exception {
		EscapeGameBuilder egb = new EscapeGameBuilder(new File("config/OrthoSquareLinearEXTRA.xml"));
		EscapeGameManager emg = egb.makeGameManager();
		// Exercise the game now: make moves, check the board, etc.
		
		//jump over two pieces -> false
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(4, 2)));
		
		//jump over one piece at time -> true
		assertNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(4, 2)));
		assertNotNull(emg.getPieceAt(emg.makeCoordinate(4, 2)));
		emg = egb.makeGameManager();//reset board
		
		//jump over one piece at time, multi times -> true
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(6, 2)));
		
		emg = egb.makeGameManager();//reset board
		//capture enemy piece ->
		assertTrue(emg.move(emg.makeCoordinate(2, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock false -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 3)));
		
		//unblock false -> can't pass over block
		assertFalse(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(1, 4)));
		
		//jump over one piece to then capture enemy piece -> true
	 	assertTrue(emg.move(emg.makeCoordinate(1, 2), emg.makeCoordinate(3, 2)));
		
		emg = egb.makeGameManager();//reset board
		//unblock true -> can't land on block
		assertFalse(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 3)));
		
		//unblock true -> can pass over  block
		assertTrue(emg.move(emg.makeCoordinate(3, 2), emg.makeCoordinate(3, 5)));
		
		//jump false -> can't jump
		assertFalse(emg.move(emg.makeCoordinate(2, 7), emg.makeCoordinate(4, 7)));
		
		//Fly -> can't end on block
		assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(1, 6)));
		
		//Fly -> can jump many pieces 
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(4, 7)));
		
	    emg = egb.makeGameManager();//reset board
	    //Fly -> can't go past distance set (5)
	    assertFalse(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(8, 7)));
	    
	    //Fly -> can go to max distance set (5)
	    assertTrue(emg.move(emg.makeCoordinate(1, 7), emg.makeCoordinate(6, 7)));
	   
	    //Distance -> can't go past distance set (4)
	    assertFalse(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(8, 8)));
	    
	    //Distance -> can go to max distance set (4)
	    assertTrue(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(8, 5)));
	    
	    //Exit removes piece
	    assertNull(emg.getPieceAt(emg.makeCoordinate(8, 5)));
	    
	    //cant do multi directions
	    assertFalse(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(6, 4)));
	    assertFalse(emg.move(emg.makeCoordinate(8, 6), emg.makeCoordinate(4, 5)));
	    assertFalse(emg.move(emg.makeCoordinate(8, 1), emg.makeCoordinate(4, 5)));
	    
	    //can't move diagonal
	    assertFalse(emg.move(emg.makeCoordinate(4, 4), emg.makeCoordinate(8, 8)));
	    
	    emg = egb.makeGameManager();//reset board
	    //can't pass over an exit
	    assertFalse(emg.move(emg.makeCoordinate(10, 1), emg.makeCoordinate(10, 5)));
	    
	    //FLY -> can pass over an exit
	    assertTrue(emg.move(emg.makeCoordinate(9, 1), emg.makeCoordinate(9, 6)));
	    
	}
    
}
