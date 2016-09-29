
package zoobutiken;


public class Amphibium extends AliveIndividuals {
    
    private boolean venomous;
    private boolean poison;
    private boolean liveInSaltWater; 

//    public Amphibium(String breed, int age, boolean isPredator,boolean venomous,boolean poison,boolean liveInSaltWater) {
//        super(breed, age, isPredator);
//        this.venomous=venomous;
//        this.poison=this.poison;
//        this.liveInSaltWater=liveInSaltWater;
//    }

 
    public boolean isVenomous() {
        return venomous;
    }

    
    public void setAmphibiumParameters(boolean venomous,boolean poison,boolean liveInSaltWater) {
        this.venomous = venomous;
         this.poison = poison;
         this.liveInSaltWater = liveInSaltWater;
    }

    
    public boolean isPoison() {
        return poison;
    }
        
    public boolean isLiveInSaltWater() {
        return liveInSaltWater;
    }
    
}
