/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author lp2552
 */
public interface IDie {
    //provides the constant for the number of dice.
    public static final int NUMBER_OF_SIDES = 6;
    
    //method for  dice roll
    public String rollDice();
    
    //adds a letter to the die
    public void addLetter(String letter);
    
    //displays all the letter for the dice
   public void displayLetters();
}
