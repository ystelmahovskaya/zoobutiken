
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
        this.setWeight(weight);
        this.setHeigh(heigh);
        this.setLength(length);
        this.setWidth(width);
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
    return new String("Accesories"+"|"+super.toString()+"|"+weight+"|"+heigh+"|"+length+"|"+width);
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @param heigh the heigh to set
     */
    public void setHeigh(double heigh) {
        this.heigh = heigh;
    }

    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }
    
}
