import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class is to create a music library to store albums and tracks, and get some random playlist
 * of the tracks.

 */
public class MusicLibrary implements Serializable
{

    private ArrayList<Album> albums;
    ArrayList<Track> allTracks = new ArrayList<Track>();

    /**
     * The constructor initialise the albums ArrayList and allTracks arrayList.
     * @param album A arraylist to store albums that added into the library.
     * @param allTracks All the tracks that are added into the library. 
     */
    public MusicLibrary()
    {
        albums=new ArrayList<Album>();
        allTracks = new ArrayList<Track>();
    }

    /**
     * This method is to add albums into the music library.
     */
    public void addAlbum(Album album){
        this.albums.add(album);
        for(Album a:albums){
            for (Track t:a.getAlbumTrack()){
                allTracks.add(t);
            }
        }
    }

    /**
     * The method to get an arraylist of all the tracks that added into the library.
     */
    public ArrayList<Track> getTracks(){
        return allTracks;
    }

    /**
     * The method to import real mp3 files in the "MP3" folder.
     * Add the mp3 files into the allTracks arraylist and get a list of albums.
     */
    public void importTracks(){
        String folder="MP3";
        String suffix=".mp3";

        ImportMP3 i=new ImportMP3(folder);
        allTracks=i.readTracks(suffix);
        albums=i.readAlbums(suffix);

    }

    /**
     * A method to add tracks into the library.
     */
    public void addTrack(Track track) {
        this.allTracks.add(track);
    }

    /**
     * A method to create a random playlist and implement the required functions.
     * The user should give a required minimum rating. Tracks in the playlist should have a rating
     * no lower than the minimum rating.
     * The user should give a required duration. The total length of the tracks in the playlist should
     * be no longer than the duration.
     * @param minRating The required minimum rating of the tracks.
     * @param duration The required total running time of the playlist.
     */
    public ArrayList<Track> createPlayList(int minRating, int duration) {
        ArrayList<Track> playList=new ArrayList<Track>();
        ArrayList<Track> sortedTracks=new ArrayList<Track>();

        sortedTracks=allTracks;
        Collections.sort(sortedTracks,new compareRating());
        int total=0;      
        while (total<=duration){
            for(int i=0;i<sortedTracks.size();i++){
                if(sortedTracks.get(i).getRate()>=minRating){
                    Track t=sortedTracks.get(i);
                    playList.add(t);
                    total=total+t.getLength();
                }
                else{
                    Track t1=sortedTracks.get(i);
                    playList.add(t1);
                    total=total+t1.getLength();
                }
                if(i==sortedTracks.size())
                {
                    break;
                }

            }
        }
        Collections.shuffle(playList);
        return playList;
    }

    /**
     * This method is to save tracks inside the ArrayList to the given file
     * @param path Path is the file where the tracks will be saved.
     * @throws IOException
     */
    public  void saveAllTracks(String path) throws IOException{
        File des=makeAbsoluteFilename(path); 
        FileOutputStream out=new FileOutputStream(des); 
        ObjectOutputStream objwrite=new ObjectOutputStream(out);
try{
        objwrite.writeObject(allTracks);
        objwrite.flush(); }
        catch (IOException e)
        {
        e.getMessage();
        }

        objwrite.close(); 
    }

    /**
     * This is a method in one exercise in the blueJ textbook.
     * This method is to create an absolute file from the given file name.
     * @throws IOException
     */
    private File makeAbsoluteFilename(String filename) throws IOException
    {
        try {
            File file = new File(filename);
            if(!file.isAbsolute()) {
                file = new File(getProjectFolder(), filename);
            }
            return file;
        }
        catch(URISyntaxException e) {
            throw new IOException("Unable to make a valid filename for " +
                filename);
        }
    }

    /**
     * I write this method by refering to a method in a exercise in the blueJ textbook.
     * This method is to determine the name of the current project folder.
     * @throws URISyntaxException
     * @return Return current project folder.
     */
    private File getProjectFolder() throws URISyntaxException
    {
        String myClassFile = getClass().getName() + ".class";
        URL url = getClass().getResource(myClassFile);
        return new File(url.toURI()).getParentFile();
    }

    /**
     * I write this method by refering to a exercise in blueJ textbook.
     * This method is to read the ArrayList of tracks that the user saved before.
     * @param path Path is the filename of the file we want to read.
     * @throws IOException, ClassNotFoundException.
     * 
     */
    public Object readObject(String path) throws IOException, ClassNotFoundException{		
        URL resource = getClass().getResource(path);
        if(resource==null){
            throw new FileNotFoundException(path);
        }
        try{
            File p = new File(resource.toURI());
            FileInputStream in=new FileInputStream(path); 
            ObjectInputStream objread=new ObjectInputStream(in);
            ArrayList<Track> readAl=(ArrayList) objread.readObject();	
            objread.close();
            return readAl;
        }
        catch (URISyntaxException e){
            throw new IOException("Unable to make a valid filename for " +
                path);
        }
    }

}

	   
