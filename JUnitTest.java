

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class JUnitTest.
 *
 * @author  Cheng Peng
 * @version V1107
 */
public class JUnitTest
{
    /**
     * Default constructor for test class JUnitTest
     */
    public JUnitTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAlbum()
    {
        Soloist soloist1 = new Soloist("1");
        Soloist soloist2 = new Soloist("2");
        Soloist soloist3 = new Soloist("3");
        Band band1 = new Band("A");
        band1.addBandMember(soloist1);
        band1.addBandMember(soloist2);
        Track track1 = new Track("NT");
        track1.setArtist(band1);
        track1.setGuestArtist(soloist3);
        track1.setRate(3);
        track1.setSize(2000);
        track1.setLength(180);
        track1.setTrackDate("2012-10-12");
        Track track2 = new Track("NT2");
        track2.setArtist(soloist1);
        track2.setRate(5);
        track2.setSize(1000);
        track2.setLength(200);
        track2.setTrackDate("2013-10-29");
        Album album1 = new Album("NA");
        album1.setAlbumTrack(track1);
        album1.setAlbumTrack(track2);
        album1.setType("Metal");
        java.util.ArrayList<Track> arrayLis1 = album1.getAlbumTrack();
        assertSame(arrayLis1, arrayLis1);
        assertEquals(4.0, album1.getRate(), 0.1);
        assertEquals(3000, album1.getTotalSize());
        assertEquals(380, album1.getTotalLength());
        assertEquals("0:6:20", album1.getStandardLength());
        java.util.ArrayList<Artist> arrayLis2 = album1.getAlbumArtist();
        assertEquals(arrayLis2, arrayLis2);
    }


    @Test
    public void testMusicLibrary()
    {
        Soloist soloist1 = new Soloist("1");
        Soloist soloist2 = new Soloist("2");
        Soloist soloist3 = new Soloist("3");
        Soloist soloist4 = new Soloist("4");
        Band band1 = new Band("A");
        Band band2 = new Band("B");
        band1.addBandMember(soloist1);
        band1.addBandMember(soloist2);
        band2.addBandMember(soloist3);
        band2.addBandMember(soloist4);
        Track track1 = new Track("T1");
        Track track2 = new Track("T2");
        Track track3 = new Track("T3");
        Track track4 = new Track("T4");
        track1.setRate(4);
        track2.setRate(5);
        track3.setRate(2);
        track4.setRate(7);
        track1.setLength(300);
        track2.setLength(400);
        track3.setLength(800);
        track4.setLength(180);
        Album album1 = new Album("A1");
        Album album2 = new Album("A2");
        album1.setAlbumTrack(track1);
        album1.setAlbumTrack(track2);
        album2.setAlbumTrack(track3);
        album2.setAlbumTrack(track4);
        MusicLibrary musicLib1 = new MusicLibrary();
        musicLib1.addAlbum(album1);
        musicLib1.addAlbum(album2);
        java.util.ArrayList<Track> arrayLis1 = musicLib1.getTracks();
        assertEquals(arrayLis1, arrayLis1);
        java.util.ArrayList<Track> arrayLis2 = musicLib1.createPlayList(8, 1500);
        assertEquals(arrayLis2, arrayLis2);
    }

    @Test
    public void textImportTracks()
    {
        MusicLibrary musicLib1 = new MusicLibrary();
        musicLib1.importTracks();
        java.util.ArrayList<Track> arrayLis1 = musicLib1.getTracks();
        assertEquals(arrayLis1, arrayLis1);
    }


}






