import java.util.*;
/**
 * This class is to help the createPlayList method in the MusicLibrary class
 * This class contains a compare method to compare two tracks by the rating.
 */
public class compareRating implements Comparator<Track>
{ 
    // instance variables - replace the example below with your own

    public compareRating (){
    }
    public int compare(Track t1,Track t2){
        return t1.getRate()-t2.getRate();
    }
    

}
