
package zoobutiken;

import java.io.IOException;


public class Zoobutiken {

    
    public static void main(String[] args) throws IOException {
      Program program = new Program();
      program.populateUsersList();
      program.populateBranchList();
      program.run();
    }
    
}
