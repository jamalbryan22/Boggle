/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

//imported API's
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lp2552
 */

//implement Idie to the class
public class Die implements IDie {
    
    private ArrayList<String> letterOnSideOfDie = new ArrayList<String>(); //holds the letters on the side of the die
     Random randy = new Random(); // random number generator
    
    
    //adds the letter on the side of the dice
     @Override
    public void addLetter(String letter) {
        letterOnSideOfDie.add(letter);
    }
    
    //displays each letter on a side of the dice. 
    @Override
    public void displayLetters(){
        int counter = 0;
        for(String t:letterOnSideOfDie){
            System.out.print(letterOnSideOfDie.get(counter) + " ");
            counter++;
        } 
    }
    // simulate a random roll by returning a side of the die at random
    @Override
    public String rollDice() {
        int index = randy.nextInt(NUMBER_OF_SIDES);
        return letterOnSideOfDie.get(index);
  
    }

}