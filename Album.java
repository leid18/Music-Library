import java.util.*;
import java.io.*;

/**
 * The album class can add tracks and get every artists in the tracks automatically.
 * The Album class also have methods to caculate the total running time, total size and average rating.
 */
public class Album implements Serializable
{
    // instance variables - replace the example below with your own
    private ArrayList<Artist> albumArtist;
    private String albumTitle;
    private ArrayList<Track> albumTrack;
    private String albumType;
    private int totalLength;
    private int totalHours;
    private int totalMinutes;
    private int totalSeconds;
    private long totalSize;

    private double averRate;

    /**
     * The constructor initialize the ArrayList albumArtist and ArrayList albumTrack.
     */
    public Album(String albumTitle)
    {
        this.albumTitle=albumTitle;
        albumArtist=new ArrayList<Artist>();
        albumTrack=new ArrayList<Track>();
       totalLength=0;
        averRate=0;

    }

    /**
     * This method is to get the every artists inside the album's tracks.
     * 
     */
    public ArrayList<Artist> getAlbumArtist(){
        for(Track t : albumTrack){
        albumArtist.addAll(t.getArtist());
        }
        return albumArtist;
    }

    /**
     * The method to add tracks into the album.
     */
    public void setAlbumTrack(Track albumTrack){
        this.albumTrack.add(albumTrack);
    }

    /**
     * The method to return a list of all the tracks of the album.
     */
    public ArrayList<Track> getAlbumTrack(){
        return albumTrack;

    }

    /**
     * The method to set the type of the album.
     */
    public void setType(String albumType){
        this.albumType=albumType;
    }

    /**
     * The method to return the type of the album.
     */
    public String getType(){
        return albumType;
    }

    /**
     * The method to get the total running time of the album.
     * @param totalHours It is used to transform the second time to standard time.
     * @param totalMinutes This method is to simulate the play operation of the music tracks.
     * @param totalSeconds This method is to simulate the play operation of the music tracks.
     */
    public int getTotalLength(){
        int i=0;
        while(i<albumTrack.size()){
            totalLength+=albumTrack.get(i).getLength();
            i++;
        
        }
        totalHours=totalLength/3600;
        totalMinutes=totalLength%3600/60;
        totalSeconds=totalLength%3600%60;
        return totalLength;
    }
    
    /**
     * The method will return a String to show running time in format "hh:mm:ss"
     */
    public String getStandardLength(){
    String standardLength=totalHours+":"+totalMinutes+":"+totalSeconds;
    return standardLength;
    }
    
    /**
     * The method to get the total size of the album.
     */
    public long getTotalSize(){
    for(int i=0;i<albumTrack.size();i++){
    totalSize=totalSize+albumTrack.get(i).getSize();
  
    }
    return totalSize;
    }

    /**
     * The method to get the average rating of the track.
     */
    public double getRate(){
        for(int i=0;i<albumTrack.size();i++){
            averRate = averRate + albumTrack.get(i).getRate();
        }
        averRate=((double)averRate)/(albumTrack.size());
        return averRate;
    }
}

