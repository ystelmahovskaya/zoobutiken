
package zoobutiken;


public class Accessories extends Stuff {
   
    protected double weight;

    public Accessories(String name, double price,double weight ) {
        super(name, price);
        this.weight=weight;
    }

    
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
}
