
package zoobutiken;


public class LittleAnimals extends AliveIndividuals {
   private int avarageLife;

//    public LittleAnimals(String breed, int age, boolean isPredator,int avarageLife) {
//        super(breed, age, isPredator);
//        this.avarageLife=avarageLife;
//    }

    
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
