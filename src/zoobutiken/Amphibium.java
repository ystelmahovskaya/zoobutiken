
package zoobutiken;


public class Amphibium extends AliveIndividuals {
    
    protected boolean venomous;
    protected boolean poison;
    protected boolean liveInSaltWater; 

 
    public boolean isVenomous() {
        return venomous;
    }

    
    public void setAmphibiumParameters(boolean venomous,boolean poison,boolean liveInSaltWater) {
        this.setVenomous(venomous);
        this.setPoison(poison);
        this.setLiveInSaltWater(liveInSaltWater);
    }

    
    public boolean isPoison() {
        return poison;
    }
        
    public boolean isLiveInSaltWater() {
        return liveInSaltWater;
    }
    public String toString(){
    return new String("Amphibium"+"|"+super.toString()+"|"+venomous+"|"+poison+"|"+liveInSaltWater);
    }

    /**
     * @param venomous the venomous to set
     */
    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    /**
     * @param poison the poison to set
     */
    public void setPoison(boolean poison) {
        this.poison = poison;
    }

    /**
     * @param liveInSaltWater the liveInSaltWater to set
     */
    public void setLiveInSaltWater(boolean liveInSaltWater) {
        this.liveInSaltWater = liveInSaltWater;
    }
    
}
