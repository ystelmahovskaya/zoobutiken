
package zoobutiken;


public class Fish extends AliveIndividuals{
    
    protected int temperature;
    protected boolean liveInSaltWater;

//    public Fish(String breed, int age, boolean isPredator,int temperature, boolean liveInSaltWater) {
//        super(breed, age, isPredator);
//        this.temperature=temperature;
//        this.liveInSaltWater=liveInSaltWater;
//    }

    
    public int getTemperature() {
        return temperature;
    }

    public boolean isLiveInSaltWater() {
        return liveInSaltWater;
    }

    
    public void setFishParameters(int temperature,boolean liveInSaltWater) {
        this.setTemperature(temperature);
        this.setLiveInSaltWater(liveInSaltWater);
    }
    
     public String toString(){
    return new String("Fish"+"|"+super.toString()+"|"+temperature+"|"+liveInSaltWater);
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * @param liveInSaltWater the liveInSaltWater to set
     */
    public void setLiveInSaltWater(boolean liveInSaltWater) {
        this.liveInSaltWater = liveInSaltWater;
    }
    
    
}
