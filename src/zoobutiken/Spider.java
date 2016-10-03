
package zoobutiken;


public class Spider extends AliveIndividuals {
    protected boolean venomous;
   protected String habitat;

//    public Spider(String breed, int age, boolean isPredator, boolean venomous,String habitat) {
//        super(breed, age, isPredator);
//        this.venomous=venomous;
//        this.habitat=habitat;
//    }

    
    public boolean isVenomous() {
        return venomous;
    }

    
    public void setSpidersParameters(boolean venomous,String habitat) {
        this.setVenomous(venomous);
        this.setHabitat(habitat);
    }

    
    public String getHabitat() {
        return habitat;
    }
    public String toString(){
    return new String("Spider"+"|"+super.toString()+"|"+venomous+"|"+habitat);
    }

    /**
     * @param venomous the venomous to set
     */
    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    /**
     * @param habitat the habitat to set
     */
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

}
