
package zoobutiken;


public class Accessories extends Stuff {
   
    protected double weight;
    protected double heigh;
    protected double length;
    protected double width;
    

//    public Accessories(String name, double price,double weight ) {
//        super(name, price);
//        this.weight=weight;
//    }

    
    public double getWeight() {
        return weight;
    }

    public void setAccessoriesParameters(double weight, double heigh,double length,double width) {
        this.weight = weight;
        this.heigh=heigh;
        this.length=length;
        this.width=width;
    }

    
    public double getHeigh() {
        return heigh;
    }

   
    public double getLength() {
        return length;
    }

    
    public double getWidth() {
        return width;
    }
    public String toString(){
    return new String(super.toString()+"|"+weight+"|"+heigh+"|"+length+"|"+width);
    }
    
}
