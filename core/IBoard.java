/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author lp2552
 */
public interface IBoard {
      static final int NUMBER_OF_DICE = 16;
      static final int GRID = 4;
    
     //method for populating all of the dice 
    void populateDice();
    
    // method for shaking die
    ArrayList shakeDice();
}
