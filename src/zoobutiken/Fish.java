
package zoobutiken;


public class Fish extends AliveIndividuals{
    
    private int temperature;
    private boolean liveInSaltWater;

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
        this.temperature = temperature;
        this.liveInSaltWater = liveInSaltWater;
    }
    
     public String toString(){
    return new String("Fish"+"|"+super.toString()+"|"+temperature+"|"+liveInSaltWater);
    }
    
    
}
