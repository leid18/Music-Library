import java.util.*;
import java.io.*;

/**
 * This class is to define the basic information of an artist, like name and age.
 * @author Cheng Peng
 * @version V1107
 */
public class Artist implements Serializable
{
    // instance variables - replace the example below with your own
    private String name;
    private int age;

    /**
     * Constructor for objects of class Artist
     * This constructor is to create an artist object whose name is using the parameter artistName.
     */
    public Artist(String artistName)
    {
        // initialise instance variables
        name=artistName;

    }

    /**
     * This method is to get the name of the artist.
     */
    public  String getArtistName(){
        return name;
    }

    /**
     * This method is to set the age of the artist.
     */
    public void setAge(int artistAge){
        age=artistAge;
    }

    /**
     * This method is to return the age of the artist.
     */
    public int getAge(){
        return age;
    }

    // public boolean isBand(){
    // return isBand;
    // }

}
