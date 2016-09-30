
package zoobutiken;

import java.util.ArrayList;
import java.util.List;


public class Shop {
    
    protected String branchName;
    protected List <Product> listOfProducts= new ArrayList<Product>();
    
    public Shop(String branchName){
    this.branchName=branchName;
    }

    @Override
    public String toString() {
        return getBranchName();
    }

    
    public List <Product> getListOfProducts() {
        return listOfProducts;
    }

    public String getBranchName() {
        return branchName;
    }
    
    
    
}
