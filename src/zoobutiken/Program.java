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
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class Program {

    protected List<Shop> shops = new ArrayList<Shop>();
    protected List<User> users = new ArrayList<User>();
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

    public void run() throws IOException {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        int answer;

        boolean isAuthorized = false;
        do {
            if (isAuthorized == false) {
                do {
                    System.out.println("Welcome!");
                    System.out.println("[1]Loggin");
                    System.out.println("[2]Create user");
                    System.out.println("[0]Quit");
                    answer = reader.usersIntInput();
                } while (answer != 1 && answer != 2 && answer != 0);
                if (answer == 0) {
                    run = false;
                } else if (answer == 1) {
                    isAuthorized = login();
                } else if (answer == 2) {

                    User user = addNewUser();
                    users.add(user);
                    FileWriter fw = new FileWriter("users.json", true);
                    BufferedWriter bfw = new BufferedWriter(fw);
                    String userJson = gsonUser.toJson(user);
                    bfw.write(userJson);
                    bfw.newLine();
                    bfw.close();
                    isAuthorized = true;
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
                    System.out.println("[3]Change the price");
                    System.out.println("[4]Sort by product's name");
                    System.out.println("[5]Sort by product's price");
                    System.out.println("[6]Sort by product's type");
                    System.out.println("[7]Find by name");
                    System.out.println("[8]Find by price");
                    System.out.println("[9]Find by type");
                    System.out.println("[10]Delete item");
                    System.out.println("[11]Change the branch");
                    System.out.println("[0]Quit");
                    answer = reader.usersIntInput();
                } while (answer != 1 && answer != 2 && answer != 3 && answer != 4 && answer != 5
                        && answer != 6 && answer != 7 && answer != 8 && answer != 9 && answer != 10
                        && answer != 11
                        && answer != 0);
                if (answer == 1) {
                    print();
                } else if (answer == 2) {
                    shops.get(activeBranch).getListOfProducts().add(addProductFromKeyboard());
                    printToFile();
                } else if (answer == 7) {
                    searchingByName();
                } else if (answer == 8) {
                    searchingByPrice();
                } else if (answer == 9) {
                    searchingByType();
                }
                else if (answer == 10) {
                    deleteItem();
                } 
                else if (answer == 11) {
                    printToFile();
                    activeBranch = -1;
                    activeBranch = branchChoice();
                } else if (answer == 0) {
                    printToFile();
                    run = false;
                }

            }
        } while (run);
    }
    
    public void deleteItem() throws IOException{
        print1();
    System.out.println("Write the number in the list which do you want to remove");
       int index=reader.usersIntInput()-1;
       if(index>0&&index<shops.get(activeBranch).getListOfProducts().size()){
       shops.get(activeBranch).getListOfProducts().remove(index);
       printToFile();
       }
       else {
           System.out.println("Wrong number try again");
       }
    
    }

    public void searchingByName() {
        System.out.println("Write the name of the product which do you want to find");
        String name = reader.usersStringInput();
        int counter = 0;
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            if (shops.get(activeBranch).getListOfProducts().get(i).getNameOfProduct().equalsIgnoreCase(name)) {
                counter++;
                System.out.println(counter + " " + shops.get(activeBranch).getListOfProducts().get(i));
            }
        }
        if (counter == 0) {
            System.out.println("No matches found");
        }
    }

    public void searchingByPrice() {
        System.out.println("Write the price of the product which do you want to find");
        double price = reader.usersDoubleInput();
        int counter = 0;
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            if (shops.get(activeBranch).getListOfProducts().get(i).getPrice() == price) {
                counter++;
                System.out.println(counter + " " + shops.get(activeBranch).getListOfProducts().get(i));
            }
        }
        if (counter == 0) {
            System.out.println("No matches found");
        }
    }

    public void searchingByType() {
        System.out.println("Choose the type which do you want to find");
        int answer = -1;
        int counter = 0;
        do {

            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + " " + types[i]);
            }
            answer = reader.usersIntInput() - 1;
        } while (answer < 0 && answer > types.length);
        for (int i = 0; i < shops.get(activeBranch).getListOfProducts().size(); i++) {
            if (shops.get(activeBranch).getListOfProducts().get(i).getClass().toString().contains(types[answer])) {
                counter++;
                System.out.println(counter + " " + shops.get(activeBranch).getListOfProducts().get(i));

            }
        }
        if (counter == 0) {
            System.out.println("No matches found");
        }
    }

    public boolean login() {
        System.out.println("Write your username");
        String username = reader.usersStringInput();
        System.out.println("Write your password");
        String password = reader.usersStringInput();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
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
            System.out.println("File not found");
        }
    }

    public void printToFile() throws IOException {

        String filename = shops.get(activeBranch).getBranchName() + ".json";
        FileWriter fw = new FileWriter("/Users/yuliiastelmakhovska/NetBeansProjects/zoobutiken/branches/" + filename);//WRITE YOUR PATH
        BufferedWriter bfw = new BufferedWriter(fw);

        for (Product product : shops.get(activeBranch).getListOfProducts()) {
            bfw.write(gsonProduct.toJson(product, Product.class));
            bfw.newLine();
        }
        bfw.close();
    }

    public void print() {
        for (Product product : shops.get(activeBranch).listOfProducts) {
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
            File folder = new File("/Users/yuliiastelmakhovska/NetBeansProjects/zoobutiken/branches/");//WRITE YOUR PATH
            System.out.println(folder.getUsableSpace());
            File listOfFiles[] = folder.listFiles();
            if (listOfFiles != null) {
                System.out.println(folder.listFiles());
                for (File file : listOfFiles) {
                    System.out.println(file);
                }
            }

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("bra")) {
                    System.out.println(listOfFiles[i].getName());
                    Shop shop = new Shop(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length() - 5));
                    populateListFromFile(shop.getListOfProducts(), listOfFiles[i].getAbsolutePath());
                    shops.add(shop);

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void populateListFromFile(List<Product> products, String filename) throws IOException {

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

    }

    public User addNewUser() {

        System.out.println("Write your name");
        String name = reader.usersStringInput();
        System.out.println("Write the username");
        String username = reader.usersStringInput();
        System.out.println("choose a password");
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

    public Product addProductFromKeyboard() {
        int answer = -1;

        do {
            System.out.println("Choose the type of product which do you want to add. Write the number.");
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + " " + types[i]);
            }
            answer = reader.usersIntInput() - 1;
        } while (answer < 0 && answer > types.length);
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

        return addProductFromKeyboard();

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
                } while (activeBranch < 0 || activeBranch >= shops.size());
            }
        } while (activeBranch == -1);
        return activeBranch;

    }

}
