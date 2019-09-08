/**
 * Class representing an airplane with the capacity to accommodate travelers
 * @author Sam-School
 *
 */
public class Airplane{
    private int capacity=0;
    /**
     * Constructs a new airplane with the given traveler capacity
     * @param Capacity - Travelers this airplane can accommodate.
     * @throws IllegalArgumentException - If the given capacity is less than 1.
     */
    public Airplane(int capacity){
        if(capacity < 1){
            throw new java.lang.IllegalArgumentException("Capacity cannot be less than 1");
        }
        this.capacity= capacity;
    }
    /**
     * Identifies whether this airplane is equal to the object provided. Two 
     * airplanes are considered the same if they have the same capacity.
     * @return True if the airplane and object are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        
        if(obj == null)
            return false;
        if(obj instanceof String)
            return false;
        if(obj instanceof Integer)
            return false;
        if(obj.getClass() != Airplane.class)
            return false;
        
            Airplane a = (Airplane) obj;
            if(this.getCapacity() == a.getCapacity()){
                return  true;
          //  }
        }
        return false;
    }
    /**
     * Returns the maximum number of travelers that can be accommodated in this airplane
     * @return Maximum number of travelers that can be accommodated in 
     * this airplane.
     */
    public int getCapacity(){
        return capacity;
    }
    /**
     * Returns the hash code of this airplane. the hash code is the value of the 
     * airplane's capacity modulus 16.
     * @return Integer reflecting the airplanes's hash code.
     */
    @Override
    public int hashCode(){
        int cap = getCapacity()%16;
        return cap;
    }
    /**
     * String representation of an airplane. The string is formatted as "Airplane[capacity]"
     * where capacity is the capacity of this airplane.
     * @return String representation of an airplane.
     */
    @Override
    public String toString(){
        String airline = "Airplane["+getCapacity()+"]";
        return airline;
    }
    
    
}

