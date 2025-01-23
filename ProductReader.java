import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ProductReader {

    public static void main(String[] args){

        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        // set the chooser to the project src directory
        chooser.setCurrentDirectory(target.toFile());

        // Use SafeInput to get the filename from the user
        Scanner userInput = new Scanner(System.in);
        String prompt = "Please choose a file";
        String fileName = SafeInput.getNonZeroLenString(userInput, prompt);


        try
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);
                // Display header
                System.out.println("ID#       Name      Description     Cost");
                System.out.println("============================================");

                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    String[] parts = line.split("\\s*,\\s*"); // Assuming CSV format (comma-separated)

                    if (parts.length == 4) { // Ensure valid record with 4 parts
                        String formattedLine = String.format("%-12s %-12s %-12s %-8s",
                                parts[0], parts[1], parts[2], parts[3]);
                        System.out.println(formattedLine);  // Output the formatted data
                    } else {
                        System.out.println("Invalid record: " + line);  // Handle invalid record format
                    }
                }

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

}



























