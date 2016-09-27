
package zoobutiken;


public class Spider extends AliveIndividuals {
    private boolean venomous;
   private String habitat;

    public Spider(String breed, int age, boolean isPredator, boolean venomous,String habitat) {
        super(breed, age, isPredator);
        this.venomous=venomous;
        this.habitat=habitat;
    }

    
    public boolean isVenomous() {
        return venomous;
    }

    
    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    
    public String getHabitat() {
        return habitat;
    }

    
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
