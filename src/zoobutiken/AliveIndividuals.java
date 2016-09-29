
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
    return new String(getNameOfProduct()+"|"+price+"|"+age+"|"+isPredator);
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
        this.age = age;
         this.isPredator = isPredator;
    }

    
    public boolean isPredator() {
        return isPredator;
    }

    
    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }

    
   
}
