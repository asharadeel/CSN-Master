//ashar adeel
//29 december 2024
//version 1
//csn master control




import java.util.Scanner;
import java.io.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to CSN Master.");
        System.out.println("ECS404u Assistant written by ashar");
        System.out.println("...");
        System.out.println("...");
        String use = input("Would you like to use CSN Master? (y/n)");
        while(!use.equalsIgnoreCase("y") && !use.equalsIgnoreCase("n")){
            use = input("Invalid input, choose between y/n.");
        }
        while(use.equalsIgnoreCase("y")) {
            if (use.equalsIgnoreCase("y")) {
                String input = input("UTF-8 FORMATTER [1] | BASE N CONVERTER [2] | BINARY FORMATTER [3]");
                while(!input.equalsIgnoreCase("1") && !input.equalsIgnoreCase("2") && !input.equalsIgnoreCase("3")){
                    input = input("Invalid input, choose between 1, 2, or 3.");
                }

                if (input.equalsIgnoreCase("1")) {
                    utf8formatter.main(args);
                } else if (input.equalsIgnoreCase("2")) {
                    baseNConverter.main(args);
                } else if (input.equalsIgnoreCase("3")) {
                    bdhformatter.main(args);
                }
            }
            System.out.println("...");
            use = input("Would you like to continue using CSN Master? (y/n)");

            while(!use.equalsIgnoreCase("y") && !use.equalsIgnoreCase("n")){
                use = input("Invalid input, choose between y/n.");
            }
            System.out.println("...");
        }
        if(use.equalsIgnoreCase("n")){
            System.out.println("Thank you for using CSN Master.");
        }
    }

    public static String input (String text){
        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer;
    }
}