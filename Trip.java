
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a trip in which travelers are flown from one airport to the
 * next. Each trip has an airplane for traveling, a number of travelers and a sequence of airports
 * to visit on the trip, where the initial and final airports in the sequence are the departure and
 * destination airports.
 * @author Sam-School
 *
 */
public class Trip {
    private Airport[] airports;
    private Airplane airplane;
    private List<Traveler> travel;//= new ArrayList<Traveler>();
    private List<Airport> airport;
    private int numOfAirports;
    
    /**
     * Creates a new trip. A trip is created with an airplane and a sequence of 
     * airports, where the first and last airports are the departing and the 
     * final destination respectively.
     * @param airports Sequence of airports visited in this trip.
     * @param airplane Airplane to be used in this trip.
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException - if the sequence of airports is
     * ill-formed, happends if there are less then 2 airports in the sequence, any airports are null
     * two consecutive airports are the same
     */
    public Trip(Airport[] airports, Airplane airplane){
        if(airplane == null){
            throw new java.lang.NullPointerException("Airplane cannot be null");
        }
        if(airports == null){
            throw new java.lang.NullPointerException("Airports cannot be null");
        }
        if(airports.length<2){
            throw new java.lang.IllegalArgumentException("Airports cannot be less than 2: found "+ airports.length);
        }
        for(int i = 0; i < airports.length; i++){
            if(airports[i] == null){
                throw new java.lang.IllegalArgumentException("Airport cannot be null: index "+ i);
            }
        }
        for(int i = 1; i < airports.length; i++){
            if(airports[i-1]== airports[i]){
                throw new java.lang.IllegalArgumentException("Repeated airport: "+airports[i].name()+" at index "+(i-1));
            }
        }
        this.airports= airports;
        this.airplane= airplane;
        travel = new ArrayList<Traveler>();
        airport = new ArrayList<Airport>();
        for(int i = 0; i < airports.length; i++){
            airport.add(airports[i]);
        }
        numOfAirports= airports.length-1;
    }
    
    
    /**
     * Returns all airports given to this trip
     * @return list with all airports in this trip
     */
    public List<Airport> getAirports(){
        //airport = Arrays.asList(airports);
        return airport;
    }
    
    
    /**
     * returns the airplane used in this trip
     * @return an airplane object
     */
    public Airplane getAirplane(){
        return airplane;
    }
    
    
    /**
     * Returns the current travlers in sorted order.
     * @return list with all current travelers in sorted order
     */
    public List<Traveler> getTravelers(){
        Collections.sort(travel);
        return travel;
        
    }
    
    /**
     * Adds a new Traveler to the trip. The Traveler is added to the trip if,
     * it is not already on the trip, the airport of travel is one 
     * of the airports yet to be traveled on this trip or, there is no room in the 
     * airplane to seat another traveler
     * @param traveler Traveler to add to the trip.
     * @return true if the traveler was added; false otherwise
     * @throws NullPointerException if the parameter is null
     */
    public boolean addTraveler(Traveler traveler){

        System.out.println(travel.size());
        if(traveler == null){
            throw new java.lang.NullPointerException("Traveler cannot be null");
        }
        
        boolean correct = false;
        if(hasRoom() == true){
            for(int i = 0; i < airports.length; i++){
                if(traveler.getDestination()== airports[i]){
                    for(int j = 0; j< travel.size(); j++){
                        if(traveler.getName().equals(travel.get(j).getName())){
                            correct =false;
                            break;
                        }
                    }
                    travel.add(traveler);
                    correct = true;
                }
            }
        }

        Collections.sort(travel);
        return correct;
    }
    
    
    /**
     * Indicates whether there are places available in this trip. Availability
     * is given by the difference between number of travelers and airplane
     * capacity.
     * @return True if another traveler can be added: false otherwise.
     */
    public boolean hasRoom(){
        if(travel.isEmpty()){
            return true;
        }
        boolean correct = false;
        if(airplane.getCapacity()-travel.size() > 0){
            correct = true;
        }
        return correct;
    }
    
    
    /**
     * Returns the current airport in this trip. If the trip has not started then
     * this airport is the first airport in the trip. If the trip has ended then it is
     *  the last airport in the trip.
     * @return Current airport in this trip
     */
    public Airport at(){
        Airport first = airport.get(0);
        return first;
    }
    
    
    /**
     * Advances the trip to the next airport. An exception is thrown if the trip has reached its last airport
     * already. Once this method is executed, all passengers whose
     * destination match the reached airport are removed
     * from the list of travelers.
     * @throws NoSuchElementException - If there is no next airport the trip is over.
     */
    public void next(){
        if(hasNext()==false){
            throw new java.util.NoSuchElementException("No more airports available");
        }
        //System.out.println(airport.size());
        if(hasNext() == true){
            System.out.println(airport.size());
           Airport a = airport.remove(0);
           for(int i = 0; i< travel.size(); i++){
               if(a.getCity()== travel.get(i).getDestination().getCity()){
                   travel.remove(i);
               }
           }
           numOfAirports--;
        }
        else{
            //throw new java.util.NoSuchElementException("No more airports available");
        }
    }
    
    
    /**
     * Indicates whether there is at least one airport yet to be reached in this trip.
     * @return True if we could travel to another airport next: false if at end of trip
     */
    public boolean hasNext(){
        if(numOfAirports==0){
            return false;
        }
        else if(numOfAirports>0){
            return true;
        }
        return true;
    }   
}

