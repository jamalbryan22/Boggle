/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.io.File;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author lp2552
 */
public class ReadDataFile implements IReadDataFile 
{
    //reads in data from the user
    private Scanner s1;
    private String fileName; //used to set the name of the file 
    private ArrayList<String> dataFile; //stores data from the file.

    //sets the name of the file from the patameter
    //returns the dictionarydata 
    public ReadDataFile(String nameOfDataFile){
        fileName=nameOfDataFile;
        dataFile = new ArrayList<>();
        
    }

    @Override
    public void populateData() 
    {
        System.out.println(fileName);
        try 
        {
                URL url = getClass().getResource(fileName);
                File file = new File(url.toURI());
                
                s1 = new Scanner(file);
               
                while(s1.hasNext())
                {
                    dataFile.add(s1.next());

                }     
        }
        catch(IOException | URISyntaxException ex)
    {
        System.out.println(ex.toString());
            ex.printStackTrace();
    }
        finally
        {
            if(s1 != null)
                s1.close();
        }
 }
    public ArrayList<String> getData() {
        return dataFile;
    }
}