
package zoobutiken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Factory {
     private static final Logger LOG = LogManager.getLogger(Factory.class);
    
    private Factory() {
    }
    
    public static Factory getInstance() {
        return FactoryHolder.INSTANCE;
    }
    
    private static class FactoryHolder {

        private static final Factory INSTANCE = new Factory();
    }
    
    public Product getProduct(String type){
    if(type == null){
         return null;
      }
    if(type.equalsIgnoreCase("Fish")){
        LOG.info("new fish added");
    return new Fish();
    }
    else if(type.equalsIgnoreCase("Amphibium")){
        LOG.info("new amphibium added");
    return new Amphibium();
    }
    else if(type.equalsIgnoreCase("LittleAnimals")){
        LOG.info("new little animal added");
    return new LittleAnimals();
    }
    else if(type.equalsIgnoreCase("Spider")){
        LOG.info("new spider added");
    return new Spider();
    }
    else if(type.equalsIgnoreCase("Insects")){
        LOG.info("new insect added");
    return new Insects();
    }
    else if(type.equalsIgnoreCase("Food")){
        LOG.info("new food item added");
    return new Food();
    }
    else if(type.equalsIgnoreCase("Accessories")){
        LOG.info("new accessories item added");
    return new Accessories();
    }
    else {
    return null;
    }
    }
}
