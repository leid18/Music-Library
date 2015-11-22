import java.util.*;
import java.text.*;
import java.io.*;

/**
 * This is a class to allow users enter and store information of a music track
 * 
 * 
 */
public class Track implements Serializable
{
    private ArrayList<Artist> trackArtist;
    private String trackTitle;
    private String trackDate;
    private int trackLength;
    private int trackRate;
    private String trackLocation;
    private long trackSize;
    private ArrayList<Artist> guestArtist;
    private int playCount;

    /**
     * The playCount is used to caculate the time one track is played.
     * @param trackArtist An ArrayList to store the artists of the track.
     * @param guestArtist A track can have guest artist. It should be stored seperately from the trackArtist.
     * It should be 0 at first.
     */
    public Track(String TrackTitle)
    {
        trackTitle=TrackTitle;
        trackArtist=new ArrayList<Artist>();
        guestArtist=new ArrayList<Artist>();
        playCount=0;
    }

    /**
     * This is the method to return the track title.
     */
    public String getTrackTitle(){
        return trackTitle;
    }

    /**
     * This is the method to add artists to the track.
     */
    public void setArtist(Artist newArtist){
        trackArtist.add(newArtist); 

    }

    /**
     * This method is to get a list of all the artist of the track.
     */
    public ArrayList<Artist> getArtist(){

        return trackArtist;
    }

    /**
     * This method is to set the recorded year of the track.
     */
    public void setTrackDate(String trackDate){
        this.trackDate=trackDate;

    }

    /**
     * This method is to get the recorded year of the track.
     */
    public String gerTrackDate(){
        return trackDate;
    }

    /**
     * This method is to add guest artist to the track.
     */
    public void setGuestArtist(Artist newGuestArtist){
        guestArtist.add(newGuestArtist);
    }

    /**
     * This method is to get a list of all the guest artists of the track.
     */
    public ArrayList<Artist> getGuestArtist(){

        return guestArtist;

    }

    /**
     * This method is to get a list of all the individuals of the track.
     * If the artist is a soloist, it would be directly returned. But is the artist is a band,
     * the members of the band would be returned.
     */
    public ArrayList<Artist> getIndividuals(){
        ArrayList<Artist> individuals = new ArrayList<Artist>();
        for(Artist a:trackArtist){

            if(a instanceof Band){
                Band b=(Band) a;
                for(int i=0;i<b.getMember().size();i++){
                    individuals.add(b.getMember().get(i));}
            }
            else{
                individuals.add(a);
            }
        }
        for(Artist c:guestArtist){

            if(c instanceof Band){
                Band d=(Band) c;
                for(int j=0;j<d.getMember().size();j++){
                    individuals.add(d.getMember().get(j));}
            }
            else{
                individuals.add(c);
            }
        }
        return individuals;
    }

    /**
     * This method is to set the rating of the track.
     * The rating of the track is set to be 0-10.
     */
    public void setRate(int trackRate){
        if(trackRate>=0&&trackRate<=10){
            this.trackRate=trackRate;
        }
        else{
            System.out.println("The rating should be 0-10");
        }
    }

    /**
     * This method is to return the rating of the track.
     */
    public int getRate(){
        return trackRate;
    }

    /**
     * This method is to set the length of the track.
     * @param trackLength The trackLength is in seconds.
     */
    public void setLength(int trackLength){
        /*this.trackHour=trackHour;
        this.trackMinute=trackMinute;
        this.trackSecond=trackSecond;*/
        /*DateFormat dateFormate=new SimpleDateFormat("HH:MM:SS");
        Calendar trackLength=Calendar.getInstance();
        trackLength.set(Calendar.HOUR,trackHour);
        trackLength.set(Calendar.MINUTE,trackMinute);
        trackLength.set(Calendar.SECOND,trackSecond);*/
        this.trackLength=trackLength;

    }

    /**
     * This method is to return teh length of the track.
     */
    public int getLength(){

        /*String trackLength=trackHour+":"+trackMinute+":"+trackSecond;*/
        return trackLength;

    }

    /**
     * This method is to set the location where the track is stored.
     */
    public void setLocation(String trackLocation){
        this.trackLocation=trackLocation;
    }

    /**
     * This method is to get the store location of the track.
     */
    public String getLocation(){
        return trackLocation;
    }

    /**
     * This method is to set the size of the track. 
     */
    public void setSize(long trackSize){
        this.trackSize=trackSize;
    }

    /**
     * This method is to get the size of the track.
     */
    public long getSize(){
        return trackSize;
    }

    /**
     * This method is to simulate the play operation of the music tracks.
     * Every time the track is played, the play count will add one.
     */
    public void playTract(){
        playCount++;
    }

    /**
     * This method is to get the play count of the track.
     */
    public int getPlayCount(){
        return playCount;
    }

}
