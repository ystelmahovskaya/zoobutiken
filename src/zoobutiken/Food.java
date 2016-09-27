
package zoobutiken;

import java.util.Date;


public class Food extends Stuff {
    protected Date produced;
    protected Date expirationDate;
    protected boolean isAlive;
    protected int temperature;

    public Food(String name, double price,  Date produced,Date expirationDate,boolean isAlive,int temperature) {
        super(name, price);
        this.produced=produced;
        this.expirationDate=expirationDate;
        this.isAlive=isAlive;
        this.temperature=temperature;
    }

    
    public Date getProduced() {
        return produced;
    }

    
    public void setProduced(Date produced) {
        this.produced = produced;
    }

    
    public Date getExpirationDate() {
        return expirationDate;
    }

    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    
    public boolean isIsAlive() {
        return isAlive;
    }

    
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    
    public int getTemperature() {
        return temperature;
    }

    
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    
}
