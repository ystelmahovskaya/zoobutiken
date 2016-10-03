
package zoobutiken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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
        this.setProduced(produced);
        this.setExpirationDate(expirationDate);
         this.isAlive = isAlive;
         this.setTemperature(temperature);
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
           DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
           
    return new String("Food"+"|"+super.toString()+"|"+format.format(getProduced()) +"|"+format.format(getExpirationDate())+"|"+isIsAlive()+"|"+temperature);
    }

    /**
     * @param produced the produced to set
     */
    public void setProduced(Date produced) {
        this.produced = produced;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the isAlive
     */
    public boolean isIsAlive() {
        return isAlive;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    
}
