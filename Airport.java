
/**
 * Enumerated type identifying airports (by their code and city) to be used as destinations for trips.
 * @author Sam-School
 *
 */
public enum Airport {
ATL("Atlanta"), CLT("Charlotte"), CUN("Cancun"), IAD("Washington"), LAX("Los Angeles"), MIA("Miami"), PHF("Newport News"), SAN("San Diego"), SEA("Seattle"), SFO("San Francisco"), YVR("Vancouver"), YYZ("Toronto");

   private String City; 
   /**
    * A constructor that initalizes the city variable
    * @param takes a Sting and sets it equal to the private City variable
    */
   private Airport(String City){
       this.City = City;
   }
   /**
    * Returns the name of the city where this airport is located.
    * @return Name of the city.
    */
   public String getCity(){
       //System.out.println(City);
         return City;
   }
   /**
    * String representation of an airport. The String is formatted as "Airport[code,city]".
    * @return String representation of an airport.
    */
   public String toString(){
       String s = "Airport["+name()+","+getCity()+"]";//valueOf(getCity())+","+getCity()+"]";
       //System.out.println(s);
       return s;
   }
   
   
   
}


