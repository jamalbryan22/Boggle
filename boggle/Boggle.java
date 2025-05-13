/*Jamal Bryan
Assignment 6
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boggle;

//imported API's
import core.Board;
import inputOutput.ReadDataFile;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import userInterface.BoggleUI;
/**
 *
 * @author lp2552
 */


public class Boggle {   
    
    //Member Variables
    public static ArrayList<String> boggleData = new ArrayList(); // stores the board data
    public static ArrayList<String> Dictionary = new ArrayList();// stores data from data file with dictionary data
    public static String dataFileName =new String( "../data/BoggleData.txt"); //stores data from the boggle file
    public static String dictionaryFileName = new String("../data/Dictionary.txt"); // stores data from data file with dictionary data
    public static ArrayList<String> tempDictionary = new ArrayList();
    
    public static void main(String[] args) {
        //Welcome to the game printed to the console 
        System.out.println("Welcome to boggle");
        
        //pop-up dialogbox inviting user to play boggle
        JOptionPane.showMessageDialog(null,"Lets Play Boggle");
       
        //instantiating a new game and populating the data for the dictionary and dice
        ReadDataFile data = new ReadDataFile(dataFileName);
        data.populateData();
        ReadDataFile dictionaryData = new ReadDataFile(dictionaryFileName);
        dictionaryData.populateData();
        tempDictionary = dictionaryData.getData();
        
        
        //instatiating a new board
        Board board1 = new Board(data.getData(),dictionaryData.getData());
        
        //set dice for the game
        board1.populateDice();
        
        //print out the number of items in the dictionary
        System.out.println("The number of items in the dictionary is " + dictionaryData.getData().size());
        
        //print blank line for spacing
        System.out.println();
        
        //populate and display data for the game board
        boggleData=board1.shakeDice();
        board1.displayOutput();
        
        
        //Instantiate an instance of class BoggleUi, passing the reference object of class Board as an argument
        BoggleUI userinterface = new BoggleUI(board1);
                
    }
}
