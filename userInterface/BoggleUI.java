/*
 * Jamal Bryan
 * Assignment 6
 * COP3330
 */
package userInterface;

import static boggle.Boggle.tempDictionary;
import core.Board;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.AttributedString;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



/**
 *
 * @author lp2552
 */
public class BoggleUI {
        
        //Memeber variables
        private JFrame frame;
        private JMenuBar menuBar;
        private JMenu menu;
        private JMenuItem menuItem1;
        private JMenuItem menuItem2;
        private JPanel panel1;
        private JButton button1;
        private JPanel panel2;
        private JScrollPane scrollPane;
        private JTextPane textPane;
        private JLabel label1;
        private JButton button2;
        private JPanel panel3;
        private JLabel label2;
        private JButton button3;
        private JLabel label3;
        private JButton boardButton1;
        private JButton boardButton2;
        private JButton boardButton3;
        private JButton boardButton4;
        private JButton boardButton5;
        private JButton boardButton6;
        private JButton boardButton7;
        private JButton boardButton8;
        private JButton boardButton9;
        private JButton boardButton10;
        private JButton boardButton11;
        private JButton boardButton12;
        private JButton boardButton13;
        private JButton boardButton14;
        private JButton boardButton15;
        private JButton boardButton16;
        private JButton [][] boardButton;
        private JTextArea textArea;
        private JLabel timeLeft;
        private JButton shakeDieButton;
        private int seconds;
        private int minutes;
        private int count;
        private Timer timer;
        private timeEvent timeHandler;
        private newBoard newBoardHandler;        
        private exitEvent exitHandler;
        private Board resetBoard;
        private Timer resetTimer;
        public int playerScore;
        private wordSubmit submitHandler;
        private recordLetter letterHandler;
        private String currentWord;
        public ArrayList <String> foundWords;
        private ArrayList <String> computerWords;
        private String score;
        public int counter;
        public int points;
        private buttonEnabler buttonController;
        private Random rand;
        
         
        //BoggleUI Constructor
        public BoggleUI(Board board) {
        resetBoard = board;
        initComponents();
}

    public void initComponents() {
        //initialize each member variable
        frame = new JFrame();
        menuBar = new JMenuBar();
        menu = new JMenu("Boggle");
        menuItem1 = new JMenuItem("New Game");
        menuItem2 = new JMenuItem("Exit");
        panel1 = new JPanel();
        button1 = new JButton("Submit Word");
        panel2 = new JPanel();
        textArea = new JTextArea(10,20);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textPane = new JTextPane();
        label1 = new JLabel("Current Word                                                                                                                                                        ");
        button2 = new JButton();
        panel3 = new JPanel();
        label2 = new JLabel("           Current Word                                   ");
        button3 = new JButton();
        label3 = new JLabel("               Score:");
        currentWord = null;
        rand = new Random();
        
                
        //create buttons for the GUI
        boardButton = new JButton[4][4];
        
        //initalize player Score
        counter = 0;
        
        //initialize the arraylist containing the found words by the user and the computer
        foundWords = new ArrayList<>();
        computerWords = new ArrayList<>();
        
        //initalize the game buttons
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                boardButton[row][col] = new JButton();
            }
        }
         //pre-fill the buttons with the game data
         for(int row = 0; row < 4; row++){
            for(int col = 0; col < 4; col++){
                boardButton[row][col].setText(Board.gameData.get(counter));
                   counter++;
            }
        }
            
                    
        //create the frame to hold each element of the gui
        BorderLayout border = new BorderLayout();
        frame.setSize(800,600);
        frame.setTitle("Boggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(border);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Container pane = frame.getContentPane();
                
        //create the menu bar and add the menu options
        frame.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(menuItem1);
        menu.add(menuItem2);
       
        
        //construct the panel for the created word
        label1.setSize(800, 100);
        panel1.setPreferredSize(new Dimension(200,80));
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue,1));
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(button1);
        panel1.add(label3);
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
       
        //add panel 1 to the frame
        pane.add(panel1, BorderLayout.SOUTH);
        
        //construct a panel for the JButtons of the dice
        panel2.setLayout(new GridLayout(4,4,1,1));
        
        for(int row = 0; row < 4; row++){      
            for(int col = 0; col < 4; col++){        
                panel2.add(boardButton[row][col]);
            }
        }
        
        
        //add panel 2 to the frame 
        pane.add(panel2, BorderLayout.WEST);
        
        //construct the panel for the game info(text area, timer, shake die button)
        Box boxLayout = Box.createVerticalBox();
        scrollPane.setPreferredSize(new Dimension(300,300)); //scroll pane containing the JText area
        scrollPane.setBounds(5, 5, 300, 20);
        boxLayout.add(scrollPane);
        panel3.add(boxLayout);
        timeLeft = new JLabel("Time Left: 3:00"); //Timer
        boxLayout.add(timeLeft);
        shakeDieButton = new JButton("Shake Dice");//Shake die button
        boxLayout.add(shakeDieButton);
        panel3.add(boxLayout);
        
        //add the last panel to the frame
        pane.add(panel3,BorderLayout.EAST); 
        
       //Register the inner class that implements interface ActionListener for resetting the game board to member variables
       newBoardHandler = new newBoard();
       shakeDieButton.addActionListener(newBoardHandler);
       menuItem1.addActionListener(newBoardHandler);
        
        //Write an inner class to create an ActionListener that is registered to the JMenuItem with the text Exit.
        exitHandler = new exitEvent();
        menuItem2.addActionListener(exitHandler);
        
        //create and start the timer object
        seconds = 0;
        minutes =3;
        timeHandler = new timeEvent();
        timer = new Timer(1000,timeHandler);
        timer.start();
        
        //add action listener to submit button
        submitHandler = new wordSubmit();
        button1.addActionListener(submitHandler);
        button1.setEnabled(false);
        
        //add actions listener to record letters onto the  board buttons
        letterHandler = new recordLetter();
        for(int row = 0; row < 4; row++){
           for(int col = 0; col < 4; col++){
               boardButton[row][col].addActionListener(letterHandler);
            }
        }
        
        //add actions listener to enable and disable die onto the  board buttons
        buttonController = new buttonEnabler();
        for(int row = 0; row < 4; row++){
           for(int col = 0; col < 4; col++){
               boardButton[row][col].addActionListener(buttonController);
            }
        }
        
        
        //pack the frame for asthetics 
        //set visible to the contents are visible
        frame.pack();
        frame.setVisible(true);

     
    }

    private class newBoard implements ActionListener {
        
        private int counter =0;
        public void actionPerformed(ActionEvent newBoardHandler){
                 
                //handler for the shake dice button
            if (newBoardHandler.getSource()==shakeDieButton){
                    
                    //re-call the shakeDice method on the board instance
                    resetBoard.shakeDice();
                    
                    //set the game board equal to each element in the array that stores the letters from the die in the board class
                    for(int row = 0; row < 4; row++){
                       for(int col = 0; col < 4; col++){
                         boardButton[row][col].setText(Board.gameData.get(counter));
                         counter++;
                       }
                    }
                    
                    //reset the game fields
                    textArea.setText(" ");
                    label3.setText("               Score: 0");
                    label1.setText("Current Word                                                                                                                                                        ");
                    timeLeft.setText("Time Left: 3:00"); 
                    frame.revalidate();
                    frame.repaint();
                    
                    //stop and reset the timer
                    timer.stop();
                    seconds = 0;
                    minutes = 3;
                    timer.start();                 
            }
            
                //handler for the "New Game" menu item
            if (newBoardHandler.getSource()== menuItem1){  
                
                    //re-call the shakeDice method on the board instance
                    resetBoard.shakeDice();
                    
                    //set the game board equal to each element in the array that stores the letters from the die in the board class
                    for(int row = 0; row < 4; row++){
                        for(int col = 0; col < 4; col++){
                          boardButton[row][col].setText(Board.gameData.get(counter));
                          counter++;
                        }
                    }
                    
                    //reset the game fields
                    textArea.setText(" ");
                    label3.setText("               Score: 0");
                    label1.setText("Current Word                                                                                                                                                        ");
                    timeLeft.setText("Time Left: 3:00"); 
                    frame.revalidate();
                    frame.repaint();
                    
                    //stop and reset the timer
                    timer.stop();
                    seconds = 0;
                    minutes = 3;
                    timer.start();
            }       
             
            counter = 0;
        }
    }
    
    //creating the handler for exit menu option
    private class exitEvent implements ActionListener {
        
        public void actionPerformed(ActionEvent exitHandler){
            
            //prompt the user on if they wish to exit
            int x = JOptionPane.showConfirmDialog(null,"Do you wish to exit","Exit" , JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                
                //if the user chooses to exit them exit the system
            if (x == JOptionPane.YES_OPTION ){
                  
                System.exit(0);
            }
            
        }
    }
    
    //create a class for the timer handler
    private class timeEvent implements ActionListener {
       
        public void actionPerformed(ActionEvent timeHandler){
            
            //if the timer is zero stop the timer object from firing
          if (minutes == 0 && seconds ==0){
              
                timer.stop();
            
            //if the seconds are zero but at least one minute still remains subtract the minute and change seconds to 59    
          }else if(seconds == 0){

                  seconds = 59;
                  minutes--;
                
          }
          
          //if there is not more time left let the user know
          if (seconds == 0 ){
              
                timeLeft.setText("Times Up!");
                
              /*try {
                  strikethrough();
                  
                  //if seconds are below 10 catenate a 0 to the number of remaining seconds
              } catch (BadLocationException ex) {
                  Logger.getLogger(BoggleUI.class.getName()).log(Level.SEVERE, null, ex);
              }*/ //I tried
              
          }else if(seconds < 10) {
              
                timeLeft.setText("Time Remaining: "+ minutes + ":0" + seconds);
           
            //if seconds are above 10 there is no need to catenate a 0, so just print the time remaining    
          } else if(seconds > 10){
              
                timeLeft.setText("Time Remaining: "+ minutes + ":" + seconds);
                
         }
          //decrement the second after all the condition checks have been completed. 
          seconds--;
          
        }  
        
    }
    
    //action listener to submit the words
    private class wordSubmit implements ActionListener
    {
        int row, column, max = 3, min = 0, tempRow = -1, tempCol = -1;
        
        public void actionPerformed(ActionEvent e)
        {   
            if(tempDictionary.contains(label2.getText().toLowerCase()) == true)
            {
                if(foundWords.contains(label2.getText()) == false)
                {
                    updateTextArea();
                    modifyScore();
                    foundWords.add(label2.getText());
                    label3.setText("Score:" + playerScore);
                }
                
                label2.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invaild Word");
                label2.setText("");
            }
            //Renable all buttons
            for(row = 0; row <= max; row++)
            {
                for(column = 0; column <= max; column++)
                {
                    boardButton[row][column].setEnabled(true);
                    if(e.getSource().equals(boardButton[row][column]))
                    {
                        tempRow = row;
                        tempCol = column;
                    }
                }
            }
        }
    }
    
 private class recordLetter implements ActionListener
    { 
      //Function that takes dice information
        @Override
        public void actionPerformed(ActionEvent e)
        {

            //To add first letter to current label
            if(button1.isEnabled() == false)
            {
                String temp;
                temp = e.getActionCommand(); 
                label2.setText(temp);
                button1.setEnabled(true);
            }
            //when there is text in currentlabel
            else
            {
                String temp2;
                temp2 = e.getActionCommand();
                label2.setText(label2.getText() + temp2);
            }
        
    }

      
    }
 
    private class buttonEnabler implements ActionListener
    {
        int row, column, max = 3, min = 0, tempRow = -1, tempCol = -1;
        
        public void actionPerformed(ActionEvent e)
        { 
            for(row = 0; row <= max; row++)
            {
                for(column = 0; column <= max; column++)
                {
                    boardButton[row][column].setEnabled(false);
                    if(e.getSource().equals(boardButton[row][column]))
                    {
                        tempRow = row;
                        tempCol = column;
                    }
                }
            }
            
            //Buttons for the left
            if(tempRow - 1 >= min)
            {
                boardButton[tempRow - 1][tempCol].setEnabled(true);
                if(tempCol - 1 >= min)
                {
                    boardButton[tempRow - 1][tempCol - 1].setEnabled(true);
                }
                if(tempCol + 1 <= max)
                {
                    boardButton[tempRow - 1][tempCol + 1].setEnabled(true);
                }
            }
            
            //Buttons for the right 
            if(tempRow + 1 <= max)
            {
                boardButton[tempRow + 1][tempCol].setEnabled(true);
                if(tempCol - 1 >= min)
                {
                    boardButton[tempRow + 1][tempCol - 1].setEnabled(true);
                }
                if(tempCol + 1 <= max)
                {
                    boardButton[tempRow + 1][tempCol + 1].setEnabled(true);
                }
            }
            
            //Buttons for the top
             if(tempCol - 1 >= min)
                boardButton[tempRow][tempCol - 1].setEnabled(true);
            
            if(tempCol + 1 <= max)
                boardButton[tempRow][tempCol + 1].setEnabled(true);
            
        }
    }


    
    
    
    
    private void updateTextArea(){
        textArea.append(label2.getText()+"\n");
    }
    
    private void modifyScore(){
     
        int length = label2.getText().length();

        if(length < 3){
            points = 0;
        }else if (length == 3 ){
            points = 1;
        }else if (length == 4 ){
            points = 1;
        }else if (length == 5 ){
            points = 2;
        }else if (length == 6 ){
            points = 3;
        }else if (length == 7 ){
            points = 4;
        }else if (length >= 8 ){
            points = 11;
        }

           playerScore += points;
        }  
    
    
    /*private void strikethrough() throws BadLocationException{
        
       
        int randomNumber = rand.nextInt(foundWords.size());
       
        for(int c = 0; c <= randomNumber;c++){
            
           computerWords.set(c, foundWords.get(c));      
       }
       
          StyledDocument styleDocument = (StyledDocument) textArea.getDocument();
          Style primaryStyle = styleDocument.addStyle("nonstrike", null);
          Style secondaryStyle = styleDocument.addStyle("strike", null);
          StyleConstants.setStrikeThrough(secondaryStyle, true);

            
        for(int v = 0; v <= computerWords.size(); v++){
            styleDocument.insertString(v, computerWords.get(v), secondaryStyle);
            
                if( v == computerWords.size()){
                    for(int b =0; b <= foundWords.size(); b++){
                        if(computerWords.get(b) != foundWords.get(b)){
                            styleDocument.insertString(v+b, computerWords.get(b), null);
                        }
                    }
                }
        }
    }*/
  
        
       
        
        
}
        
    

