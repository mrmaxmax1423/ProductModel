//@author Maximiliano De Santiago Galan
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class ProductWriter {
    public static void main(String[] args) {
        String IDNum;
        String name;
        String description;
        Double cost;

        String fileName;
        Scanner fileNameInput = new Scanner(System.in);
        fileName = SafeInput.getNonZeroLenString(fileNameInput, "Input Desired File Name (Don't include .txt)");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        boolean adding;

        ArrayList<String> products = new ArrayList<>();
        adding = true;



        //Get input from user
        while(adding){
            Scanner IDNumInput = new Scanner(System.in);
            IDNum = SafeInput.getNonZeroLenString(IDNumInput, "IDNum");
            Scanner nameInput = new Scanner(System.in);
            name = SafeInput.getNonZeroLenString(nameInput, "Name");
            Scanner descriptionInput = new Scanner(System.in);
            description = SafeInput.getNonZeroLenString(descriptionInput, "Description");
            Scanner costInput = new Scanner(System.in);
            cost = SafeInput.getDouble(costInput, "Cost");
            products.add(IDNum);
            products.add(name);
            products.add(description);
            products.add(String.valueOf(cost));

            //Ask user if they wish to enter another person
            Scanner continueInput = new Scanner(System.in);
            adding = SafeInput.getYNConfirm(continueInput, "Add Another Product? (Y or N)");

            int counter = 0;

            try
            {
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                for(String product : products)
                {
                    writer.write(product, 0, product.length());
                    counter ++;
                    if(counter % 4 == 0)
                    {
                        writer.newLine();
                    }
                    else
                    {
                        writer.write(", ");
                    }
                }
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}
