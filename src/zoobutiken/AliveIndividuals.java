
package zoobutiken;

import java.util.List;


public abstract class AliveIndividuals implements Product {
    
    protected String breed;
    protected int age;
    protected boolean isPredator;
    protected List <Food> food;

 
    
    public AliveIndividuals(String breed, int age, boolean isPredator){
    this.breed= breed;
    this.age=age;
    this.isPredator=isPredator;
    }
    
    public String toString(){
        String s= " Is a predator";
        if(isIsPredator()==false){
        s="Is not a predator";
        }
    return new String("Breed "+getBreed()+" age "+getAge()+s);
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

   
    public void setBreed(String breed) {
        this.breed = breed;
    }

    
    public int getAge() {
        return age;
    }

   
    public void setAge(int age) {
        this.age = age;
    }

    
    public boolean isIsPredator() {
        return isPredator;
    }

   
    public void setIsPredator(boolean isPredator) {
        this.isPredator = isPredator;
    }

    
    public List <Food> getFood() {
        return food;
    }

   
    public void setFood(List <Food> food) {
        this.food = food;
    }
}
