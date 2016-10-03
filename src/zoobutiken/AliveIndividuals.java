
package zoobutiken;

import java.util.List;


public abstract class AliveIndividuals implements Product {
    
    
    protected String nameOfProduct;
    protected double price;
    protected int age;
    protected boolean isPredator;
    

 
    
//    public AliveIndividuals(String nameOfProduct, int age, boolean isPredator){
//    this.nameOfProduct= nameOfProduct;
//    this.age=age;
//    this.isPredator=isPredator;
//    }
    
    public String toString(){
    return new String(getNameOfProduct()+"|"+price+"|"+age+"|"+isIsPredator());
    }

    /**
     * @return the nameOfProduct
     */
    public String getNameOfProduct() {
        return nameOfProduct;
    }

   
    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    
    public int getAge() {
        return age;
    }

   
    public void setAliveIndividualsParameters(int age, boolean isPredator) {
        this.setAge(age);
        this.setIsPredator(isPredator);
    }

    
    public boolean isPredator() {
        return isIsPredator();
    }

    
    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the isPredator
     */
    public boolean isIsPredator() {
        return isPredator;
    }

    /**
     * @param isPredator the isPredator to set
     */
    public void setIsPredator(boolean isPredator) {
        this.isPredator = isPredator;
    }

    
   
}
