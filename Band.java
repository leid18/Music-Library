import java.util.*;
/**
 * This is the subclass of the Artist class.
 * The Band class need to have a collection of members.
 * 
 */
public class Band extends Artist
{
    // instance variables - replace the example below with your own
    private ArrayList<Soloist> bandMembers;

    /**
     * Constructor for objects of class Band
     * @param bandMembers bandMembers is an ArrayList to store soloist objects.
     */
    public Band(String bandName){
        // initialise instance variables
        super(bandName);
        bandMembers= new ArrayList<Soloist>();
        

    }

    /**
     * This method add soloist into the bandMembers ArrayList.
     */
    public void addBandMember(Soloist bandMember){
        bandMembers.add(bandMember);
    }
    
    /**
     * This method returns all the elements inside the bandMember sArrayList.
     */
    public ArrayList<Soloist> getMember(){
    return bandMembers;
    }
}
