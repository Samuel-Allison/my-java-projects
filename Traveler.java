/**
 * Class representing a traveler on a trip. A traveler is an immutable object that
 * has a name and an airport of destination and implements the interface comparable to 
 * provide a natural ordering of travelers
 * travelers have a name and a Airport destination
 * @author Sam-School
 *
 */

public class Traveler implements Comparable<Traveler>{
    private String name;
    private Airport destination;
    /**
     * Constructs a new Traveler with a name and an airport of destination
     * @param name - name of the traveler
     * @param destination - Airport of destination
     * @throws NullPointerException if either destination or name are null
     */
    public Traveler(String name, Airport destination){
        if( destination == null){
            throw new NullPointerException("Destination cannot be null");
        }
        if(name == null){
            throw new NullPointerException("Name cannot be null");
        }
        this.name= name;
        this.destination= destination;
    }
    /**
     * Provides the natural order between this traveler and the one provided. Returns a negative
     * integer, zero or a positive depending on whether this traveler compares lower, the same or 
     * greater than the specified traveler. Comparisons are made by destination and(if not sufficient)by name.
     * @param Traveler - Traveler provided for comparison
     * @return Returns : integer denoting the result of the comparison.
     * @throws Throws: NullPointerException if the traveler is null
     * 
     */
    public int compareTo(Traveler traveler){
        if(traveler == null){
            throw new NullPointerException("Traveler cannot be null");
        }
        String a = traveler.getDestination().getCity();
        String travName = traveler.getName();
        String b = destination.getCity();
        

      System.out.println(a+" "+b);
      //System.out.println();
      System.out.println(travName+" " +this.name);
      //System.out.println(); 
        if(destination.getCity()!= a || a != destination.getCity()){
            int cdm = a.compareTo(b);
            int dft = b.compareTo(a);
            if(dft *-1 == cdm){
                return cdm;
            }
        }
        else if(name != travName || travName != name){
            int cdm = name.compareTo(travName);
            int dft = travName.compareTo(name);
            if(dft * -1 == cdm)
                return cdm;
        }
        //else if(destination.getCity() == a){
          //  return a.compareTo(b);
        //}
        //else
          //  return name.compareTo(travName);
            return 0;   
    }
    /**
     * Identifies whether this traveler is equal to the object provided. Two
     * travelers are considered the same if they have the same name and traveling destination
     * @param Takes a Object to be compared
     * @return Returns true if the traveler and object are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof String){
            return false;
        }
        if(obj instanceof Integer){
            return false;
        }
        if(obj.getClass()!= Traveler.class)
            return false;
            Traveler a = (Traveler) obj;
            boolean result = false;
            if(this.getName().equals(a.getName()) && (this.getDestination().toString().equals(a.getDestination().toString()))){
                
                result= true;

            }
            System.out.println(result);
            return result;
        
    }
    /**
     * Returns the destination of this traveler
     * @return Airport of destination
     */
    public Airport getDestination(){
        return destination;
    }
    /**
     * returns the name of this traveler
     * @return String with the traveler's name.
     */
    public String getName(){
        return name;
    }
    /**
     * Returns the hash code of this traveler. The hash code is calculated by
     * the following formula:(32*hashcode of the name)+hashcode of the destination
     * @return Returns: Integer reflecting the traveler's hash code.
     * 
     */
    @Override
    public int hashCode(){
        int hashcode = 32 * name.hashCode();
        hashcode += destination.hashCode();
        return hashcode;
    }
    /**
     * String representation of a traveler. the String is formatted as
     * "Traveler[name,destination]" where name and destination are the values held in this 
     * traveler object
     * @return Returns : String representation of a traveler.
     */
    public String toString(){
        String name = "Traveler["+ getName() +","+destination.toString()+"]";
        return name;
    }

}

