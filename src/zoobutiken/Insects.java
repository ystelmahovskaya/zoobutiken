
package zoobutiken;


public class Insects extends AliveIndividuals {
    protected boolean venomous;
     protected boolean flying;

//    public Insects(String breed, int age, boolean isPredator, boolean venomous,boolean flying) {
//        super(breed, age, isPredator);
//        this.flying=flying;
//        this.venomous=venomous;
//    }

   
    public boolean isVenomous() {
        return venomous;
    }

   
    public void setInsectsparameters(boolean venomous,boolean flying) {
        this.setVenomous(venomous);
        this.setFlying(flying);
    }

    
    public boolean isFlying() {
        return flying;
    }
    
     public String toString(){
    return new String("Insect"+"|"+super.toString()+"|"+venomous+"|"+flying);
    }

    /**
     * @param venomous the venomous to set
     */
    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    /**
     * @param flying the flying to set
     */
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    
   
}
