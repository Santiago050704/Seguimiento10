import model.Country;
import model.CountryList;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static CountryList countryList = new CountryList();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        int option;
        countryList.load();
        do{

            option = getOptionMenu();
            executeOption(option);

        }while(option != 5);
        input.close();
    }

    public static int getOptionMenu(){
        int option = 5;
        System.out.println("What do you want to do?");
        System.out.println(
                        "1. Enter a country. \n" +
                        "2. Show medallions. \n" +
                        "3. Show total medals. \n" +
                        "4. Show countries. \n" +
                        "5. Exit.");
        option = input.nextInt();
        return option;
    }

    public static void executeOption(int option) throws IOException {
        switch(option){
            case 1:
                System.out.println("Enter a country.");
                enterCountry();
                break;

            case 2:
                System.out.println("Show medallions.");
                countryList.showMedalTable();
                break;

            case 3:
                System.out.println("Show total medals.");
                countryList.sortByNumberOfMedals();
                break;

            case 4:
                System.out.println("Show countries.");
                countryList.sortByAlphabeticalOrder();
                break;

            case 5:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    public static void enterCountry() throws IOException {
        System.out.println("Type the entry with the format Country::Type_of_medal::Quantity");
        String entry = input.next();
        countryList.enterCountry(entry);
    }
}