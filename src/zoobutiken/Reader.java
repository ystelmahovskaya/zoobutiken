package zoobutiken;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Reader {

    private static final Logger LOG = LogManager.getLogger(Reader.class);

    

    Scanner scanner = new Scanner(System.in);

//    encapsulation of users input 
    public int usersIntInput() {

        int result;
        do {
            try {
                result = scanner.nextInt();
                if(result<0){
                System.out.println("Wrong number input, try again");
            }

            } catch (Exception exception) {
                System.out.println("Wrong data input you need to write a number");
                result = -1;
                LOG.error("Reader.usersIntInput" + exception.getMessage());
            }
            scanner.nextLine();  
        } while (result < 0);
        return result;
    }
     public int usersIntInputNegativeIncluded() {
 int result;
       
 
            try {
            result   = scanner.nextInt();

            } catch (Exception exception) {
                System.out.println("Wrong data input you need to write a number");
                LOG.error("Reader.usersIntInput" + exception.getMessage());
                return usersIntInputNegativeIncluded();
            }
            scanner.nextLine();
       return result;
        
    }


    public String usersStringInput() {
        String s = "";
        do {
            s = scanner.nextLine();
            if (s.length()==0){
                System.out.println("You need to write something");
            }

        } while (s.length()==0);
        return s;
    }

  

    public double usersDoubleInput() {

        double result;
        do {
            try {
                result = scanner.nextDouble();
                if(result<0){
                System.out.println("Wrong number input, try again");
            }

            } catch (Exception exception) {
                System.out.println("Wrong data input you need to write a number");
                LOG.error("Reader.usersDoubleInput " + exception.getMessage());
                result = -1;
            }
            
            scanner.nextLine();
        } while (result < 0);
        return result;
    }

    public boolean YN_UsersAnswer() {
        String answer;
        do {
            //System.out.println("Y/N?");
            answer = scanner.nextLine().toUpperCase();
        } while (answer.charAt(0) != 'Y' && answer.charAt(0)!= 'N' && answer.length() != 2);

        //scanner.nextLine();
        if (answer.charAt(0) == 'Y') {
            return true;
        } else {
            return false;
        }
    }

    public boolean YN_Parser(String line) {
        if (line.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public Date usersDateInput() {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        System.out.println("Enter date and time in the format yyyy-MM-dd");
        System.out.println("For example, it is now " + format.format(new Date()));
        Date date = null;
        while (date == null) {
            String line = scanner.nextLine();
            try {
                date = format.parse(line);
            } catch (ParseException e) {
                System.out.println("Sorry, that's not valid. Please try again.");
                LOG.error("Reader.usersDateInput" + e.getMessage());
            }

        }
        return date;
    }

    public Date parseDateFromString(String line) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date(1900 - 01 - 01);
        try {
            date = format.parse(line);
        } catch (ParseException e) {

            LOG.error("Reader.parseDateFromString" + e.getMessage());
        }
        return date;
    }
}
