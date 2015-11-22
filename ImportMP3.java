/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import java.util.*;

/**
 *
 * @author Cheng Peng
 * This class is to help the MusicLibrary class to import real mp3 files via jAudioTagger library.
 * Go to http://www.jthink.net/jaudiotagger/ for more details.
 */
public class ImportMP3 {

    private ArrayList<Track> tracks;
    private ArrayList<Album> albums;
    private MP3File mp3File;
    private String trackTitle;
    private String trackDate;
    private int trackLength;
    private int trackRate;
    private String trackLocation;
    private long trackSize;
    private Artist artist;
    private String folder;
    private String tempTitle;
    private Album album;
    private String albumTitle;
    private String albumType;

    /**
     * The constructor initialise the folder field. 
     */
    public ImportMP3(String folder) {
        this.folder = folder;

    }

    /**
     * @suffix suffix of the file.
     * This method is to read music file in the defined folder.
     * @param folder folder is the folder in the computer where the mp3 files are stored.
     * @throws NullPointerException
     */
    public ArrayList<Track> readTracks(final String suffix) {
        File audioFolder = new File(folder);
        ArrayList<Track> tracks = new ArrayList<Track>();

        File[] audioFiles = audioFolder.listFiles(new FilenameFilter() {
                    /**
                     * Accept files with matching suffix.
                     *
                     * @param dir The directory containing the file.
                     * @param name The name of the file.
                     * @return true if the name ends with the suffix.
                     */
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(suffix);
                    }
                });

        // Put all the matching files into the organizer.
        try{
            for (File file : audioFiles) {
                Track trackDetails = getNewTracks(file);
                tracks.add(trackDetails);
            }
        }
        catch(NullPointerException e){
            e.getMessage();
        }
        return tracks;

    }

    /**
     * The method to return all albums of the imported tracks.
     */
    public ArrayList<Album> returnAllAlbums(){
        return albums;
    }

    /**
     * The method to read albums from the imported tracks.
     * @throws NullPointerException
     */
    public ArrayList<Album> readAlbums(final String suffix) {
        File audioFolder = new File(folder);
        ArrayList<Album> albums = new ArrayList<Album>();
        File[] audioFiles = audioFolder.listFiles(new FilenameFilter() {
                    /**
                     * Accept files with matching suffix.
                     *
                     * @param dir The directory containing the file.
                     * @param name The name of the file.
                     * @return true if the name ends with the suffix.
                     */
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(suffix);
                    }
                });
        // Put all the matching files into the organizer.
        try{
            for (File file : audioFiles) {
                Album albumDetails=getNewAlbums(file);
                albums.add(albumDetails);
            }
        }
        catch (NullPointerException e){
            e.getMessage();
        }
        return albums;

    }

    /**
     * This method is to get the impormation of the real mp3 file.
     * @return Return a track called t that contains the details.
     
     * @param tempTitle The file name of the mp3 file. tempTitle may not be the track name.
     */
    public Track getNewTracks(File file) {
        tempTitle=file.getName();
        trackLocation = file.getPath();
        setMp3TrackLength();
        setMp3TrackSize();
        setMp3TrackTitle();
        setMp3TrackDate();
        setMp3TrackArtist();
        Track t = new Track(trackTitle);
        t.setArtist(artist);
        t.setLength(trackLength);
        t.setLocation(trackLocation);
        t.setSize(trackSize);
        t.setTrackDate(trackDate);
        return t;

    }

    /**
     * This method is to get the imformation of the album from the tracks.
     * @return Return a album called a that contain s the details.
     */
    public Album getNewAlbums(File file){
        setMp3AlbumTitle();
        setMp3AlbumType();
        Album a=new Album(albumTitle);
        a.setType(albumType);
        return a;
    }

    /**
     * Get the length of the mp3 file and give the value to the field trackLength.
     */
    public void setMp3TrackLength() {
        try {
            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            trackLength=audioHeader.getTrackLength();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Get the size of the mp3 file and pass the value to the field trackSize.
     */
    public void setMp3TrackSize() {
        try {
            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            trackSize=audioHeader.getMp3StartByte();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Get the title of the mp3 file and pass the value to the field trackTitle.
     */
    public void setMp3TrackTitle() {
        try {
            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);
            Tag tag = mp3File.getTag();
            trackTitle = tag.getFirst(FieldKey.TITLE);
            if (mp3File.hasID3v2Tag()) {
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Get the recorded year of the mp3 file and pass the value to the field trackDate.
     */
    public void setMp3TrackDate() {
        try {
            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);

            Tag tag = mp3File.getTag();
            trackDate = tag.getFirst(FieldKey.YEAR);

            if (mp3File.hasID3v2Tag()) {
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Get the artist name of the mp3 file 
     * and initialise a new artist object using the artist name.
     * 
     */
    public void setMp3TrackArtist() {

        try {

            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);

            Tag tag = mp3File.getTag();
            artist=new Artist(tag.getFirst(FieldKey.ARTIST));

            if (mp3File.hasID3v2Tag()) {
                File file1 = new File(folder+"/"+tempTitle);
                mp3File = (MP3File) AudioFileIO.read(file1);

                Tag tag1 = mp3File.getTag();
                artist=new Artist(tag1.getFirst(ID3v24Frames.FRAME_ID_ARTIST));

            }
        }
        catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Get the album title of the mp3 file 
     * and initialise a new album object using the album title.
     */
    public void setMp3AlbumTitle() {
        try {
            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);

            Tag tag = mp3File.getTag();
            albumTitle = tag.getFirst(FieldKey.ALBUM);

            if (mp3File.hasID3v2Tag()) {
                File file1 = new File(folder+"/"+tempTitle);
                mp3File = (MP3File) AudioFileIO.read(file1);

                Tag tag1 = mp3File.getTag();
                albumTitle=tag1.getFirst(ID3v24Frames.FRAME_ID_ALBUM);
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Get the genre( track type) of the mp3 file and pass the value to the field albumType.
     */
    public void setMp3AlbumType() {
        try {
            File file = new File(folder+"/"+tempTitle);
            mp3File = (MP3File) AudioFileIO.read(file);

            Tag tag = mp3File.getTag();
            albumType=tag.getFirst(FieldKey.GENRE);

            if (mp3File.hasID3v2Tag()) {
                File file1 = new File(folder+"/"+tempTitle);
                mp3File = (MP3File) AudioFileIO.read(file1);

                Tag tag1 = mp3File.getTag();
                albumType=tag1.getFirst(ID3v24Frames.FRAME_ID_GENRE);
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
