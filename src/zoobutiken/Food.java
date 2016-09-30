
package zoobutiken;

import java.util.Date;


public class Food extends Stuff {
    protected Date produced;
    protected Date expirationDate;
    protected boolean isAlive;
    protected int temperature;

//    public Food(String name, double price,  Date produced,Date expirationDate,boolean isAlive,int temperature) {
//        super(name, price);
//        this.produced=produced;
//        this.expirationDate=expirationDate;
//        this.isAlive=isAlive;
//        this.temperature=temperature;
//    }

    
    public Date getProduced() {
        return produced;
    }

    
    public void setFoodParameters(Date produced,Date expirationDate,boolean isAlive,int temperature) {
        this.produced = produced;
         this.expirationDate = expirationDate;
         this.isAlive = isAlive;
           this.temperature = temperature;
    }

    
    public Date getExpirationDate() {
        return expirationDate;
    }
   
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    
    public int getTemperature() {
        return temperature;
    }
       public String toString(){
    return new String("Food"+"|"+super.toString()+"|"+produced+"|"+expirationDate+"|"+isAlive+"|"+temperature);
    }
    
}
