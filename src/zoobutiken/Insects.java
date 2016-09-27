
package zoobutiken;


public class Insects extends AliveIndividuals {
    private boolean venomous;
     private boolean flying;

    public Insects(String breed, int age, boolean isPredator, boolean venomous,boolean flying) {
        super(breed, age, isPredator);
        this.flying=flying;
        this.venomous=venomous;
    }

   
    public boolean isVenomous() {
        return venomous;
    }

   
    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    
    public boolean isFlying() {
        return flying;
    }

    
    public void setFlying(boolean flying) {
        this.flying = flying;
    }
}
