package zoobutiken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
public class Program {

    protected List<Shop> shops = new ArrayList<Shop>();
    protected List<User> users = new ArrayList<User>();
    boolean admin =false;
    Reader reader = new Reader();
    Gson gsonUser = new Gson();
    final RuntimeTypeAdapterFactory<Product> adapter
            = RuntimeTypeAdapterFactory
            .of(Product.class)
            .registerSubtype(Product.class)
            .registerSubtype(AliveIndividuals.class)
            .registerSubtype(Stuff.class)
            .registerSubtype(Birds.class)
            .registerSubtype(Spider.class)
            .registerSubtype(Fish.class)
            .registerSubtype(LittleAnimals.class)
            .registerSubtype(Amphibium.class)
            .registerSubtype(Food.class)
            .registerSubtype(Accessories.class)
            .registerSubtype(Insects.class);
    final Gson gsonProduct = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
    protected String types[] = {"Bird", "Fish", "LittleAnimals", "Amphibium", "Spider", "Insects", "Food", "Accessories"};
    int activeBranch = -1;
    String currentUsersHomeDir = System.getProperty("user.home");

    public void run() throws IOException {
        boolean run = true;
        int answer;

        boolean isAuthorized = false;
        do {

            if (users.isEmpty()) {
                do {
                    System.out.println("[1]Create admin user");
                    System.out.println("[0]Quit");
                    answer = reader.usersIntInput();
                    if (answer != 1 && answer != 0) {
                        System.out.println("Wrong data");
                    }
                } while (answer != 1 && answer != 0);
                if (answer == 0) {
                    run = false;
                } else if (answer == 1) {
                    admin = true;
                    addUser(addNewUser());
                    users.get(0).setIsAdmin(true);
                    System.out.println("is admin: " + users.get(0).isAdmin);
                    saveUsersToFile("users.json");
                    isAuthorized = true;
                }
            }

            if (isAuthorized == false) {
                do {
                    System.out.println("Welcome!");
                    System.out.println("[1]Loggin");
                    System.out.println("[0]Quit");
                    answer = reader.usersIntInput();
                    if (answer != 1 && answer != 0) {
                        System.out.println("Wrong data");
                    }
                } while (answer != 1 && answer != 0);
                if (answer == 0) {
                    run = false;
                } else if (answer == 1) {
                    System.out.println("Write your username");
                    String username = reader.usersStringInput();
                    System.out.println("Write your password");
                    String password = reader.usersStringInput();
                    isAuthorized = login(username, password);
                }
            }
            answer = -1;

            if (isAuthorized) {
                if (activeBranch == -1) {
                    activeBranch = branchChoice();
                }

                do {
                    System.out.println("[1]Print the list of products");
                    System.out.println("[2]Add new product");
                    System.out.println("[3]Update item");
                    System.out.println("[4]Sort by product's name");
                    System.out.println("[5]Sort by product's price");
                    System.out.println("[6]Sort by product's type");
                    System.out.println("[7]Find by name");
                    System.out.println("[8]Find by price");
                    System.out.println("[9]Find by type");
                    System.out.println("[10]Delete item");
                    System.out.println("[11]Change the branch");
                    if (admin == true) {
                        System.out.println("[12]Add new user");
                        System.out.println("[13]Delete user");
                        System.out.println("[14]Update user");
                    }
                    System.out.println("[0]Quit");
                    answer = reader.usersIntInput();
                } while (answer != 1 && answer != 2 && answer != 3 && answer != 4 && answer != 5
                        && answer != 6 && answer != 7 && answer != 8 && answer != 9 && answer != 10
                        && answer != 11 && answer != 12 && answer != 13 && answer != 14
                        && answer != 0);
                if (answer == 1) {
                    print1();
                } else if (answer == 2) {
                    int answer2 = -1;

                    do {
                        System.out.println("Choose the type of product which do you want to add. Write the number.");
                        for (int i = 0; i < types.length; i++) {
                            System.out.println((i + 1) + " " + types[i]);
                        }
                        answer2 = reader.usersIntInput();
                    } while (answer2 != 1 && answer2 != 2 && answer2 != 3 && answer2 != 4 && answer2 != 5
                        && answer2 != 6 && answer2 != 7 && answer2 != 8);
                    shops.get(activeBranch).getListOfProducts().add(addProductFromKeyboard(answer2-1));
                    printToFile();
                } else if (answer == 3) {
                    changeItem();
                } 
                else if(answer==4){
                sortByName();
                }
                else if(answer==5){
                sortByPrice();
                }
                else if(answer==6){
                sortByType();
                }
                else if (answer == 7) {
                    System.out.println("Write the name of the product which do you want to find");
                    String name = reader.usersStringInput();
                    printList(searchingByName(name));
                } else if (answer == 8) {
                      System.out.println("Write the price of the product which do you want to find");
        double price = reader.usersDoubleInput();
                    printList( searchingByPrice(price));
                } else if (answer == 9) {
                    
                    System.out.println("Choose the type which do you want to find");
        
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + " " + types[i]);
            }
        int    answer9 = reader.usersIntInput() - 1;
        if(answer9>=0&&answer9<types.length){
                    printList(   searchingByType(answer9));
        }
                } else if (answer == 10) {
                    print1();
                    System.out.println("Write the number in the list which do you want to remove");
                    int index = reader.usersIntInput() - 1;
                    deleteItem(index, activeBranch);
                    printToFile();
                } else if (answer == 11) {
                    printToFile();
                    activeBranch = -1;
                    activeBranch = branchChoice();
                } else if (answer == 12) {
                    if(admin){
                    addUser(addNewUser());
                    saveUsersToFile("users.json");
                    } else {
                        System.out.println("Wrong number");}
                } else if (answer == 13) {
                    if(admin){
                    System.out.println("Choose the user which do you want to delete from users' list");
                    for (int i = 0; i < users.size(); i++) {
                        System.out.println((i + 1) + " " + users.get(i));
                    }
                    int index = reader.usersIntInput() - 1;
                    deleteUser(admin, index);
                     saveUsersToFile("users.json");}
                    else{
                        System.out.println("Wrong number");}
                } else if (answer == 14) {
                    if(admin){
                    updateUser();
                    } else{System.out.println("Wrong number");}
                } else if (answer == 0) {
                    printToFile();
                    run = false;
                }

            }
        } while (run);
    }

    public void deleteItem(int index, int activeBranch) throws IOException {

        if (index >= 0 && index < shops.get(activeBranch).getListOfProducts().size()) {
            shops.get(activeBranch).getListOfProducts().remove(index);

        } else {
            System.out.println("Wrong number try again");
        }

    }

    public void addUser(User user) throws IOException {
        if (admin) {
            users.add(user);
        } else {
            System.out.println("you have no admin rights");
        }
    }

    private User addNewUser() {

        System.out.println("Write the name");
        String name = reader.usersStringInput();
        System.out.println("Write the username");
        String username = reader.usersStringInput();
        System.out.println("Choose a password");
        String password = reader.usersStringInput();
        if (userVerification(name, password)) {
            try {
                return new User(name, username, password);
            } catch (Exception e) {
                System.out.println("Wrong data input, try again");
                return addNewUser();
            }
        } else {
            System.out.println("Verificaton failed. Try again");
            return addNewUser();
        }
    }

    private boolean userVerification(String name, String password) {
        if (password.length() < 4) {
            return false;
        }
        for (User userFromList : users) {
            if (name.equalsIgnoreCase(userFromList.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public void deleteUser(boolean admin, int index) throws IOException {
        if (admin) {

            if (index >= 0 && index < users.size()) {
                users.remove(index);
            }
           
        } else {
            System.out.println("you don't have admin rights");
        }
    }

    public boolean login(String username, String password) {

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (user.isAdmin == true) {
                    admin = true;
                }
                System.out.println("Welcome, " + user.getName());
                return true;
            }
        }
        System.out.println("Login failed check your data or create a user");
        return false;
    }

    public void populateUsersList() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream("users.json");
            String strLine;
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((strLine = br.readLine()) != null) {
                User user = gsonUser.fromJson(strLine, User.class);
                users.add(user);
            }
            br.close();

        } catch (FileNotFoundException e) {
           
        }
    }

    public void updateUser() throws IOException {
        if (admin) {
            System.out.println("Choose the user which do you want to delete from users' list");
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + " " + users.get(i));
            }
            int index = reader.usersIntInput() - 1;
            if (index >= 0 && index < users.size()) {
                System.out.println("The current name is " + users.get(index).getName());
                System.out.println("do you want to change it? Y/N");
                boolean answer = reader.YN_UsersAnswer();
                if (answer) {
                    System.out.println("Write the new name ");
                    users.get(index).setName(reader.usersStringInput());
                }
                System.out.println("The username name is " + users.get(index).getUsername());
                System.out.println("do you want to change it? Y/N");
                answer = reader.YN_UsersAnswer();
                if (answer) {
                    System.out.println("Write the new username");
                    users.get(index).setUsername(reader.usersStringInput());
                }
                System.out.println("The password is " + users.get(index).getPassword());
                System.out.println("do you want to change it? Y/N");
                answer = reader.YN_UsersAnswer();
                if (answer) {
                    System.out.println("Write the new password");
                    users.get(index).setPassword(reader.usersStringInput());
                }
                System.out.println("admin status is " + users.get(index).isIsAdmin());
                System.out.println("do you want to change it?Y/N");
                answer = reader.YN_UsersAnswer();
                if (answer) {
                    System.out.println("do you want to give admin rights to this user? Changes will be avaliable after reload. Y/N");
                    users.get(index).setIsAdmin(reader.YN_UsersAnswer());
                }
                saveUsersToFile("users.json");
            }
        } else {
            System.out.println("you don't have admin rights");
        }
    }

    public void saveUsersToFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bfw = new BufferedWriter(fw);
        for (User user : users) {
            String userJson = gsonUser.toJson(user);
            bfw.write(userJson);
            bfw.newLine();
        }
        bfw.close();
    }

    public List<Product> searchingByName(String name) {

        List<Product> sorted = new ArrayList<Product>();
    
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            if (shops.get(activeBranch).getListOfProducts().get(i).getNameOfProduct().equalsIgnoreCase(name)) {
                
                sorted.add(shops.get(activeBranch).getListOfProducts().get(i));
            }
        }
        if (sorted.size()==0) {
            System.out.println("No matches found");
        }
        return sorted;
    }

    public  List<Product> searchingByPrice(double price) {
      
      List<Product> sorted = new ArrayList<Product>();
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            if (shops.get(activeBranch).getListOfProducts().get(i).getPrice() == price) {
               
                sorted.add(shops.get(activeBranch).getListOfProducts().get(i));
            }
        }
        if (sorted.size()==0) {
            System.out.println("No matches found");
        }
        return sorted;
    }

    public List<Product> searchingByType(int answer) {
        
       List<Product> sorted = new ArrayList<Product>();
       
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            if (shops.get(activeBranch).getListOfProducts().get(i).getClass().toString().contains(types[answer])) {
                
               sorted.add( shops.get(activeBranch).getListOfProducts().get(i));

            }
        }
        if (sorted.size()==0) {
            System.out.println("No matches found");
        }
        return sorted;
    }

    public void printToFile() throws IOException {

        String filename = "branch_" + shops.get(activeBranch).getBranchName() + ".json";
        String folder = currentUsersHomeDir + File.separator + "branches" + File.separator;
        
        File f = new File(folder);
        if (f != null) {
            f.mkdirs();
        }

        FileWriter fw = new FileWriter(folder + filename);
        BufferedWriter bfw = new BufferedWriter(fw);

        for (Product product : shops.get(activeBranch).getListOfProducts()) {
            bfw.write(gsonProduct.toJson(product, Product.class));
            bfw.newLine();
        }
        bfw.close();
    }

    public void printList(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void print1() {
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            System.out.println((i + 1) + "" + shops.get(activeBranch).getListOfProducts().get(i));
        }
    }

    public void populateBranchList() throws IOException {

        try {

            File folder = new File(currentUsersHomeDir + File.separator + "branches" + File.separator);
            File listOfFiles[] = folder.listFiles();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                   // System.out.println(file);
                }

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("branch_")) {
                        Shop shop = new Shop(listOfFiles[i].getName().substring(7, listOfFiles[i].getName().length() - 5));
                        shop.setListOfProducts(populateListFromFile(listOfFiles[i].getAbsolutePath()));
                        shops.add(shop);

                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Product> populateListFromFile(String filename) throws IOException {
        List<Product> products = new ArrayList<Product>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            String strLine;
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((strLine = br.readLine()) != null) {
                Product product = gsonProduct.fromJson(strLine, Product.class);
                products.add(product);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return products;
    }

    public Product addProductFromKeyboard(int answer) {

        Product product = Factory.getInstance().getProduct(types[answer]);
        System.out.println("Write the name of the product");
        String name = reader.usersStringInput();
        System.out.println("Write the price");
        double price = reader.usersDoubleInput();
        product.setNameOfProduct(name);
        product.setPrice(price);

        if (product instanceof AliveIndividuals) {
            System.out.println("Write the age");
            int age = reader.usersIntInput();
            System.out.println("Is it a predator? Y/N");
            boolean isPredator = reader.YN_UsersAnswer();
            ((AliveIndividuals) product).setAliveIndividualsParameters(age, isPredator);

            if (product instanceof Birds) {

                System.out.println("Write the species");
                ((Birds) product).setBirdsParameters(reader.usersStringInput());
                return product;
            } else if (product instanceof LittleAnimals) {
                System.out.println("Set the avarade life length of the individum");
                ((LittleAnimals) product).setAvarageLife(reader.usersIntInput());
                return product;
            } else if (product instanceof Fish) {
                System.out.println("Write the temperature");
                int temperature = reader.usersIntInput();
                System.out.println("Is it live in salt water?Y/N");
                boolean liveInSaltWater = reader.YN_UsersAnswer();
                ((Fish) product).setFishParameters(temperature, liveInSaltWater);
                return product;

            } else if (product instanceof Spider) {
                System.out.println("Is it venomous?Y/N");
                boolean isVenomous = reader.YN_UsersAnswer();
                System.out.println("Write the spiders habitat");
                String habitat = reader.usersStringInput();
                ((Spider) product).setSpidersParameters(isVenomous, habitat);
                return product;
            } else if (product instanceof Insects) {
                System.out.println("Is it venomous?Y/N");
                boolean isVenomous = reader.YN_UsersAnswer();
                System.out.println("Can it fly?Y/N");
                boolean isFlying = reader.YN_UsersAnswer();
                ((Insects) product).setInsectsparameters(isVenomous, isFlying);
                return product;
            } else if (product instanceof Amphibium) {
                System.out.println("Is it venomous?Y/N");
                boolean isVenomous = reader.YN_UsersAnswer();
                System.out.println("Is it poisonous?Y/N");
                boolean isPoison = reader.YN_UsersAnswer();
                System.out.println("Is it live in salt water?Y/N");
                boolean liveInSaltWater = reader.YN_UsersAnswer();
                ((Amphibium) product).setAmphibiumParameters(isVenomous, isPoison, liveInSaltWater);
                return product;
            }
        } else if (product instanceof Stuff) {
            System.out.println("Write the country");
            ((Stuff) product).setCountry(reader.usersStringInput());
            if (product instanceof Food) {
                System.out.println("Write the date of production");
                Date produced = reader.usersDateInput();
                System.out.println("Write the expiration date");
                Date expirationDate = reader.usersDateInput();
                System.out.println("Is it alive?Y/N");
                boolean isAlive = reader.YN_UsersAnswer();
                System.out.println("Recomended temperature?");
                int temperature = reader.usersIntInputNegativeIncluded();
                ((Food) product).setFoodParameters(produced, expirationDate, isAlive, temperature);
                return product;
            } else if (product instanceof Accessories) {
                System.out.println("Write the weigh");
                double weight = reader.usersDoubleInput();
                System.out.println("Write the heigh");
                double heigh = reader.usersDoubleInput();
                System.out.println("Write the length");
                double length = reader.usersDoubleInput();
                System.out.println("Write the width");
                double width = reader.usersDoubleInput();
                ((Accessories) product).setAccessoriesParameters(weight, heigh, length, width);
                return product;
            }
        }

        return addProductFromKeyboard(answer);

    }

    public int branchChoice() {
        int answer = -1;
        int avaliable = 1;
        do {
            do {
                System.out.println("[1]Create new branch");
                if (shops.size() > 0) {
                    System.out.println("[2]Choose the branch");
                    avaliable = 2;
                }
                answer = reader.usersIntInput();
            } while (answer != 1 && answer != 2);
            if (answer == 1) {
                System.out.println("Write the name of new branch");
                String name = reader.usersStringInput();
                shops.add(new Shop(name));
                avaliable = 2;

            } else if (answer == 2 && avaliable == 2) {
                answer = -1;
                do {
                    System.out.println("Choose the number of the shop");
                    for (int i = 0; i < shops.size(); i++) {
                        System.out.println((i + 1) + " " + shops.get(i));
                    }
                    activeBranch = reader.usersIntInput() - 1;
                    if (activeBranch < 0 || activeBranch >= shops.size()) {
                        System.out.println("Wrong data");
                    }
                } while (activeBranch < 0 || activeBranch >= shops.size());
            }
        } while (activeBranch == -1);
        return activeBranch;

    }

    public void changeItem() {
        print1();
        System.out.println("Choose the item");
        int index = reader.usersIntInput() - 1;
        if (index >= 0 && index < shops.get(activeBranch).getListOfProducts().size()) {
            if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Product) {
                System.out.println("The current name is " + shops.get(activeBranch).getListOfProducts().get(index).getNameOfProduct());
                System.out.println("do you want to change it? Y/N");
                boolean answer = reader.YN_UsersAnswer();
                if (answer) {
                    System.out.println("Write the new name ");
                    shops.get(activeBranch).getListOfProducts().get(index).setNameOfProduct(reader.usersStringInput());
                }
                System.out.println("The current price is " + shops.get(activeBranch).getListOfProducts().get(index).getPrice());
                System.out.println("do you want to change it? Y/N");
                answer = reader.YN_UsersAnswer();
                if (answer) {
                    System.out.println("Write the new price");
                    shops.get(activeBranch).getListOfProducts().get(index).setPrice(reader.usersDoubleInput());
                }

                if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Stuff) {
                    System.out.println("The current producer country is " + ((Stuff) shops.get(activeBranch).getListOfProducts().get(index)).getCountry());
                    System.out.println("do you want to change it? Y/N");
                    answer = reader.YN_UsersAnswer();
                    if (answer) {
                        System.out.println("Write the new country");
                        ((Stuff) shops.get(activeBranch).getListOfProducts().get(index)).setCountry(reader.usersStringInput());
                    }
                    if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Accessories) {
                        System.out.println("The current weight is " + ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).getWeight());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new weight");
                            ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).setWeight(reader.usersDoubleInput());
                        }
                        System.out.println("The current width is " + ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).width);
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new width");
                            ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).setWidth(reader.usersDoubleInput());
                        }
                        System.out.println("The current heigh is " + ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).getHeigh());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new heigh");
                            ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).setHeigh(reader.usersDoubleInput());
                        }
                        System.out.println("The current length is " + ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).getLength());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new length");
                            ((Accessories) shops.get(activeBranch).getListOfProducts().get(index)).setLength(reader.usersDoubleInput());
                        }
                    } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Food) {
                        System.out.println("The current produced date is " + ((Food) shops.get(activeBranch).getListOfProducts().get(index)).getProduced());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new date");
                            ((Food) shops.get(activeBranch).getListOfProducts().get(index)).setProduced(reader.usersDateInput());
                        }
                        System.out.println("The current exp date is " + ((Food) shops.get(activeBranch).getListOfProducts().get(index)).getExpirationDate());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new date");
                            ((Food) shops.get(activeBranch).getListOfProducts().get(index)).setExpirationDate(reader.usersDateInput());
                        }
                        System.out.println("Now is alive " + ((Food) shops.get(activeBranch).getListOfProducts().get(index)).isIsAlive());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it alive? Y/N");
                            ((Food) shops.get(activeBranch).getListOfProducts().get(index)).setIsAlive(reader.YN_UsersAnswer());
                        }
                        System.out.println("Current temperature for keeping is " + ((Food) shops.get(activeBranch).getListOfProducts().get(index)).getTemperature());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new temperature");
                            ((Food) shops.get(activeBranch).getListOfProducts().get(index)).setTemperature(reader.usersIntInput());
                        }

                    }
                } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof AliveIndividuals) {
                    System.out.println("The current age is " + ((AliveIndividuals) shops.get(activeBranch).getListOfProducts().get(index)).getAge());
                    System.out.println("do you want to change it? Y/N");
                    answer = reader.YN_UsersAnswer();
                    if (answer) {
                        System.out.println("Write the new age");
                        ((AliveIndividuals) shops.get(activeBranch).getListOfProducts().get(index)).setAge(reader.usersIntInput());
                    }

                    System.out.println("This is a predator " + ((AliveIndividuals) shops.get(activeBranch).getListOfProducts().get(index)).isIsPredator());
                    System.out.println("do you want to change it? Y/N");
                    answer = reader.YN_UsersAnswer();
                    if (answer) {
                        System.out.println("is it a predator? Y/N");
                        ((AliveIndividuals) shops.get(activeBranch).getListOfProducts().get(index)).setIsPredator(reader.YN_UsersAnswer());
                    }

                    if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Birds) {

                        System.out.println("The current species is " + ((Birds) shops.get(activeBranch).getListOfProducts().get(index)).getSpecies());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new species");
                            ((Birds) shops.get(activeBranch).getListOfProducts().get(index)).setBirdsParameters(reader.usersStringInput());
                        }
                    } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Amphibium) {
                        System.out.println("This is venomous " + ((Amphibium) shops.get(activeBranch).getListOfProducts().get(index)).isVenomous());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it venomous? Y/N");
                            ((Amphibium) shops.get(activeBranch).getListOfProducts().get(index)).setVenomous(reader.YN_UsersAnswer());
                        }
                        System.out.println("This is poisonous " + ((Amphibium) shops.get(activeBranch).getListOfProducts().get(index)).isPoison());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it poisonous? Y/N");
                            ((Amphibium) shops.get(activeBranch).getListOfProducts().get(index)).setPoison(reader.YN_UsersAnswer());
                        }

                        System.out.println("lives in salt water " + ((Amphibium) shops.get(activeBranch).getListOfProducts().get(index)).isLiveInSaltWater());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it live in salt water? Y/N");
                            ((Amphibium) shops.get(activeBranch).getListOfProducts().get(index)).setLiveInSaltWater(reader.YN_UsersAnswer());
                        }
                    } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Fish) {

                        System.out.println("Current temperature is " + ((Fish) shops.get(activeBranch).getListOfProducts().get(index)).getTemperature());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new temperature");
                            ((Fish) shops.get(activeBranch).getListOfProducts().get(index)).setTemperature(reader.usersIntInput());
                        }

                        System.out.println("lives in salt water " + ((Fish) shops.get(activeBranch).getListOfProducts().get(index)).isLiveInSaltWater());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it live in salt water? Y/N");
                            ((Fish) shops.get(activeBranch).getListOfProducts().get(index)).setLiveInSaltWater(reader.YN_UsersAnswer());
                        }
                    } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Insects) {
                        System.out.println("This is venomous " + ((Insects) shops.get(activeBranch).getListOfProducts().get(index)).isVenomous());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it venomous? Y/N");
                            ((Insects) shops.get(activeBranch).getListOfProducts().get(index)).setVenomous(reader.YN_UsersAnswer());
                        }
                        System.out.println("It can fly " + ((Insects) shops.get(activeBranch).getListOfProducts().get(index)).isFlying());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it venomous? Y/N");
                            ((Insects) shops.get(activeBranch).getListOfProducts().get(index)).setFlying(reader.YN_UsersAnswer());
                        }
                    } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof LittleAnimals) {
                        System.out.println("Currentavarage length of life is " + ((LittleAnimals) shops.get(activeBranch).getListOfProducts().get(index)).getAvarageLife());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new temperature");
                            ((LittleAnimals) shops.get(activeBranch).getListOfProducts().get(index)).setAvarageLife(reader.usersIntInput());
                        }
                    } else if (shops.get(activeBranch).getListOfProducts().get(index) instanceof Spider) {
                        System.out.println("This is venomous " + ((Spider) shops.get(activeBranch).getListOfProducts().get(index)).isVenomous());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("is it venomous? Y/N");
                            ((Spider) shops.get(activeBranch).getListOfProducts().get(index)).setVenomous(reader.YN_UsersAnswer());
                        }

                        System.out.println("The current habitat is " + ((Spider) shops.get(activeBranch).getListOfProducts().get(index)).getHabitat());
                        System.out.println("do you want to change it? Y/N");
                        answer = reader.YN_UsersAnswer();
                        if (answer) {
                            System.out.println("Write the new habitat");
                            ((Spider) shops.get(activeBranch).getListOfProducts().get(index)).setHabitat(reader.usersStringInput());
                        }
                    }
                }

            }
        }
    }
    public void sortByPrice(){
        Collections.sort(shops.get(activeBranch).getListOfProducts(), new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
        if (o1.getPrice() > o2.getPrice()){
            return 1;
        }
        else if (o1.getPrice() < o2.getPrice()){
            return -1;
        }
        return 0;
    }
});
        print1();
    }
    public void sortByName(){
        Collections.sort(shops.get(activeBranch).getListOfProducts(), new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2){
            return o1.getNameOfProduct().compareToIgnoreCase(o2.getNameOfProduct());
            }
        });
        print1();
    }

        public void sortByType(){
        Collections.sort(shops.get(activeBranch).getListOfProducts(), new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2){
            return o1.getClass().getTypeName().compareTo(o2.getClass().getTypeName());
            }
        });
     print1();
}
    
}
