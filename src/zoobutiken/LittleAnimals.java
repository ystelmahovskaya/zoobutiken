
package zoobutiken;


public class LittleAnimals extends AliveIndividuals {
   private int avarageLife;
  
    public int getAvarageLife() {
        return avarageLife;
    }

    
    public void setAvarageLife(int avarageLife) {
        this.avarageLife = avarageLife;
    }
    public String toString(){
    return new String("LittleAnimal"+"|"+super.toString()+"|"+avarageLife);
    }
   
}
