/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

//imported java classes
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author lp2552
 */ 

//create board class implementing IBoard
public class Board implements IBoard {
    
    private ArrayList<String> dieData; //Stores dice data
    private ArrayList<String> dictData; // sores dictionary data
    private ArrayList<Die> gameDice; //stores 16 game dice
    static public ArrayList<String> gameData; //stores the game board data

    
    //sets the dice && sets the data for the dictionary
    public Board(ArrayList<String> die, ArrayList<String> dictionaryData){
      dieData = die;
      dictData = dictionaryData;
      gameDice = new ArrayList<>();
      gameData = new ArrayList<>();
}
    
    /**
     *
     */
    @Override
    //this calculates the number of dice and assignes the letters for each side of the die
    public void populateDice(){
        Die die;
        int counter = 0;
        
            //Determines the numberof nice to create
        for(int i = 0; i < NUMBER_OF_DICE; i++){
            die = new Die();
               
            //adds a letter to each side
            for(int side = 0; side < die.NUMBER_OF_SIDES; side++)
            {
                
              die.addLetter(dieData.get(counter).toString());
              counter++;
              
            } 
            
            //print out the  populated dice
            System.out.print("Die " + i + ": ");
            die.displayLetters();
            System.out.println();
          
            //add the die to the die array
            gameDice.add(die);
        }
    }

        //simulate a shake of the dice, selecting them in a random order
    @Override
    public ArrayList shakeDice() {
      
       Random random = new Random();        
       ArrayList <Die> tempArray = new ArrayList(); //temporary array for the switch 
       Die temp = new Die(); //temporary die for the switch 
       int i, value;
            
            gameData.clear();
            //Loop through the 16 Dice store them in the game data array in random order
            for(i = 0; i < NUMBER_OF_DICE; i++) {
              value = random.nextInt(gameDice.size());

              temp = gameDice.get(value); //store the random die in the temp array
              gameData.add(temp.rollDice()); //store the dies data into the gameData array

              tempArray.add(temp);    //account for the die that was selected
              //gameData.remove(temp);       

            }
            gameDice = tempArray; // store tempArray with the dice data back into gameDice

        return gameData; 
     
    }

    //return the game board data
    public ArrayList<String> getGameData() {
        return gameData;
    }
        
    //display the board data in a format to match the figure two of the assignment. 
    public void displayOutput()
    {
        System.out.println("\nBoggle Board");
        int counter = 0;
        
        for(String data: gameData)
        {
            System.out.print(data + " ");
            counter++;
            
            if(counter % 4 == 0)
                System.out.println();
        }
        System.out.println();
    }
    
 }
    

