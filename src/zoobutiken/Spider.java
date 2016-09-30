
package zoobutiken;


public class Spider extends AliveIndividuals {
    private boolean venomous;
   private String habitat;

//    public Spider(String breed, int age, boolean isPredator, boolean venomous,String habitat) {
//        super(breed, age, isPredator);
//        this.venomous=venomous;
//        this.habitat=habitat;
//    }

    
    public boolean isVenomous() {
        return venomous;
    }

    
    public void setSpidersParameters(boolean venomous,String habitat) {
        this.venomous = venomous;
        this.habitat = habitat;
    }

    
    public String getHabitat() {
        return habitat;
    }
    public String toString(){
    return new String("Spider"+"|"+super.toString()+"|"+venomous+"|"+habitat);
    }

}
