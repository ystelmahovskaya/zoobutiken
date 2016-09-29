
package zoobutiken;


public class Insects extends AliveIndividuals {
    private boolean venomous;
     private boolean flying;

//    public Insects(String breed, int age, boolean isPredator, boolean venomous,boolean flying) {
//        super(breed, age, isPredator);
//        this.flying=flying;
//        this.venomous=venomous;
//    }

   
    public boolean isVenomous() {
        return venomous;
    }

   
    public void setInsectsparameters(boolean venomous,boolean flying) {
        this.venomous = venomous;
        this.flying = flying;
    }

    
    public boolean isFlying() {
        return flying;
    }
    
     public String toString(){
    return new String(super.toString()+"|"+venomous+"|"+flying);
    }

    
   
}
