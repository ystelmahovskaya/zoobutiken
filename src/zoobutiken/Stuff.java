
package zoobutiken;


public abstract class Stuff implements Product {
    
    protected String nameOfProduct;
    protected double price;
    protected String country;

    
    public String getNameOfProduct() {
        return nameOfProduct;
    }

    
    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    
    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }

    
    public String getCountry() {
        return country;
    }

    
    public void setCountry(String country) {
        this.country = country;
    }
    public String toString(){
    return new String(nameOfProduct+"|"+price+"|"+country);
    }
    
    
    
}
