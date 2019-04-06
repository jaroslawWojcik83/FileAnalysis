import java.util.Scanner;

/**
 * Created by wojcik.jaroslaw1 on 03.04.2019.
 */

public class Main {

    public static void main(String[] args) {

        TextFileInterface textFile = new TextFileDAO();
        Scanner scr = new Scanner(System.in);
        
        String FILENAME = null;
        int decision;
        int whetherToAnalyzeTheFile = -1;

        do {
            System.out.print("What do you want will todo: 0 - exit" +
                    "\n                            1 - select a file" +
                    "\n                            2 - analysis a file" +
                    "\nDecision: ");

            decision = scr.nextInt();

            switch (decision) {
                case 0 : return;
                case 1 :
                    FILENAME = textFile.getFile();
                    System.out.println("Loaded file: " + FILENAME + "\n");
                    break;
                case 2 :
                   if (FILENAME != null) {
                        whetherToAnalyzeTheFile = 1;
                        break;
                    }
                    System.out.println("No file selected! ");
            }
        } while (whetherToAnalyzeTheFile != 1);

        System.out.println("File analysis...");

        textFile.readFile();
        textFile.getTop10WordsAndCount();
        textFile.getTop10WordsLength();


    }
}
