import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {



    public static void main(String[] args) {
        /*a.	ID (a String as before in Person)
          b.	Name (a String)
          c.	Description (a String a short sentence)
          d.	Cost (This is currency so it will be a Java double)
*/
        String ID;
        String name;
        String description;
        double cost;

        String productRec;
        ArrayList<String> productInfo = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + File.separator+"src"+ File.separator + "ProductTestData.txt");




        boolean done;

        do {
            ID= SafeInput.getNonZeroLenString(in, "Enter the product ID[6 digits]");
            name= SafeInput.getNonZeroLenString(in, "Enter the product name");
            description= SafeInput.getNonZeroLenString(in, "Enter the product description");
            cost= SafeInput.getDouble(in,"Enter the product cost");

            productRec = ID +","+ name + ","+ description + ","+ cost;
            // stores the record and adds to arraylist folks
            productInfo.add(productRec);
            done = SafeInput.getYNConfirm(in, "Are you done");


            // loops repeat until user is not done
        }while(!done);
        //3. Displays all records
        // goes through each string in folks and prints it
        for(String p: productInfo)
            System.out.println(p);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
        {

            // Finally can write the file LOL!

            for(String rec : productInfo)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }











    }

