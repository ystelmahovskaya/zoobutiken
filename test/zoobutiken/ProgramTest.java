/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoobutiken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.naming.directory.DirContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProgramTest {

  

    @Test
    public void testPopulateUsersList() throws Exception {
  
        System.out.println("populateUsersList");
        Program instance = new Program();
        instance.admin=true;
         instance.activeBranch=0;
         instance.addUser(new User("Test","Test","Test"));
         int userCount1=instance.users.size();
         instance.saveUsersToFile("users.json");
         instance.deleteUser(true, 0);
         instance.populateUsersList();
         int userCount2=instance.users.size();
         assertEquals(userCount1,userCount2);
    }

    @Test
    public void testPrintToFile() throws Exception {
        System.out.println("printToFile");
        Program instance = new Program();
        instance.shops.add(new Shop("TestShop"));
        instance.activeBranch = 0;
        instance.shops.get(0).getListOfProducts().add(new Birds());
        instance.printToFile();
    }
 @Test
    public void testPopulateBranchList() throws Exception {
        System.out.println("populateBranchList");
        Program instance = new Program();
        int counter=0;
         File folder = new File(instance.currentUsersHomeDir + File.separator + "branches" + File.separator);
         File listOfFiles[] = folder.listFiles();
         for(File file:listOfFiles){
         if(file.getName().contains("branch_")){
         counter++;
         }
         }
        instance.populateBranchList();
        int branchSize=instance.shops.size();
                assertEquals(counter, branchSize);

    }

    @Test
    public void testPopulateListFromFile() throws Exception {
        System.out.println("populateListFromFile");
        List<Product> products = new ArrayList<Product>();
        String filename = "branch_TestShop.json";
        Program instance = new Program();
        instance.activeBranch=0;
        instance.shops.add(new Shop("TestShop"));
        instance.shops.get(0).listOfProducts.add(new Birds());
        instance.printToFile();
        String fullName = instance.currentUsersHomeDir + File.separator + "branches" + File.separator + filename;
        products = instance.populateListFromFile(fullName);
        System.out.println("products size " + products.size() + " " + fullName);
         assertEquals(products.get(0).getClass(), instance.shops.get(0).listOfProducts.get(0).getClass());
    }


    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "Abc";
        String password = "1234";
        Program instance = new Program();
        instance.users.add(new User("Abc","Abc","1234"));
        boolean expResult = true;
        boolean result = instance.login(username, password);
        assertEquals(expResult, result);
       
    }

    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        boolean admin = true;
        int index = 0;
        Program instance = new Program();
        instance.users.add(new User("Abc","Abc","1234"));
        int expResult=instance.users.size()-1;
        instance.deleteUser(admin, index);
        int result=instance.users.size();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testDeleteItem() throws Exception {
        System.out.println("deleteItem");
        int index = 0;
        int activeBranch = 0;
         
        Program instance = new Program();
        instance.shops.add(new Shop("TestShop"));
        
        instance.shops.get(activeBranch).getListOfProducts().add(new Birds());
       int expResult=instance.shops.get(activeBranch).getListOfProducts().size()-1;
     instance.deleteItem(index,activeBranch);
        int result=instance.shops.get(activeBranch).getListOfProducts().size();
         assertEquals(expResult, result);
    }

    
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        User user = new User("Abc","Abc","1234");
        Program instance = new Program();
        instance.activeBranch=0;
        instance.admin=true;
        
       int expResult=instance.users.size()+1;
   instance.addUser(user);
        int result=instance.users.size();
          assertEquals(expResult, result);
    }

   
    @Test
    public void testPrintList() {
        System.out.println("printList");
        List<Product> products = null;
        Program instance = new Program();
        instance.shops.add(new Shop("TestShop"));
        instance.activeBranch = 0;
        instance.shops.get(0).getListOfProducts().add(new Birds());
        products = instance.shops.get(0).getListOfProducts();
        instance.printList(products);
    }

    
    @Test
    public void testSearchingByName() {
        System.out.println("searchingByName");
        String name = "Abc";
        Program instance = new Program();
        instance.activeBranch = 0;
       instance.shops.add(new Shop("TestShop"));
       instance.shops.get(0).getListOfProducts().add(new Birds());       
       instance.shops.get(0).getListOfProducts().get(0).setNameOfProduct("Abc");
     
        List<Product> expResult = new ArrayList<Product>();
        expResult.add(instance.shops.get(0).getListOfProducts().get(0));
        
        List<Product> result = instance.searchingByName(name);
                
       assertEquals(expResult.get(0).getNameOfProduct(), result.get(0).getNameOfProduct());
        
    }

  

    @Test
    public void testSearchingByPrice() {
        System.out.println("searchingByPrice");
        double price = 2.2;
        Program instance = new Program();
         instance.activeBranch = 0;
       instance.shops.add(new Shop("TestShop"));
       instance.shops.get(0).getListOfProducts().add(new Birds());
       
       instance.shops.get(0).getListOfProducts().get(0).setPrice(2.2);
            List<Product> expResult = new ArrayList<Product>();
        expResult.add(instance.shops.get(0).getListOfProducts().get(0));
        List<Product> result = instance.searchingByPrice(price);          
       assertEquals(expResult.get(0).getPrice(), result.get(0).getPrice(),0.0);
    }


    @Test
    public void testSearchingByType() {
        System.out.println("searchingByType");
        int answer = 0;
        
        Program instance = new Program();
         instance.activeBranch = 0;
       instance.shops.add(new Shop("TestShop"));
       instance.shops.get(0).getListOfProducts().add(new Birds());
        List<Product> expResult = new ArrayList<Product>();
        expResult.add(instance.shops.get(0).getListOfProducts().get(0));
        List<Product> result = instance.searchingByType(answer);
        assertEquals(expResult.get(0).getClass(), result.get(0).getClass());
       
    }

  
    @Test
    public void testSaveUsersToFile() throws Exception {
        System.out.println("saveUsersToFile");
        String fileName = "users.json";
        File file= new File(fileName);
        long startSize=file.length();
        Program instance = new Program();
        instance.users.add(new User("Test1","Test2","Test1"));
        
        instance.saveUsersToFile(fileName);
        long sizeAfterWriting=file.length();
        assertNotEquals(startSize, sizeAfterWriting);
      
    }

}
