package model;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CountryList {

    static String folder = "data";
    static String path = "data/2024OlympicGamesMedals.txt";
    ArrayList<Country> countries;
    public CountryList(){
        countries = new ArrayList<>();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        String data = "";
        for(int i = 0; i < countries.size(); i++){
            data += countries.get(i).getName() + "::Gold::" + countries.get(i).getGoldMedals() + "::Silver::" +
                    countries.get(i).getSilverMedals() + "::Bronze::" + countries.get(i).getBronzeMedals() + "\n";
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }

    public void load() throws IOException{
        File file = new File(path);
        if(file.exists()){
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while((line = reader.readLine()) != null){
                System.out.println(line);
                String[] arr = line.split("::");
                System.out.println(Arrays.toString(arr));
                countries.add(new Country(arr[0]));
                content += line +"\n";
            }
            System.out.println(countries.size());
        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void enterCountry(String entry) throws IOException {
        String[] data = entry.split("::");
        if(data.length != 3){
            System.out.println("Invalid format. Try again.");
            return;
        }

        String name = data[0];
        String medal = data[1].toLowerCase();
        int quantity = Integer.parseInt(data[2]);
        
        Country existingCountry = null;
        for(Country country : countries){
            if(country.getName().equalsIgnoreCase(name)){
                existingCountry = country;
                break;
            }
        }

        if(existingCountry != null){
            switch (medal){
                case "gold":
                    existingCountry.increaseGoldMedals(quantity);
                    break;

                case "silver":
                    existingCountry.increaseSilverMedals(quantity);
                    break;

                case "bronze":
                    existingCountry.increaseBronzeMedals(quantity);
                    break;

                default:
                    System.out.println("Invalid medal. Try again.");
                    break;
            }
        }else{
            Country newCountry = new Country(name);
            switch (medal){
                case "gold":
                    newCountry.increaseGoldMedals(quantity);
                    break;

                case "silver":
                    newCountry.increaseSilverMedals(quantity);
                    break;

                case "bronze":
                    newCountry.increaseBronzeMedals(quantity);
                    break;

                default:
                    System.out.println("Invalid medal. Try again.");
                    break;
            }
            countries.add(newCountry);
        }
        save();
    }

    public void showMedalTable(){
        System.out.println("Conventional medal table");
        System.out.println("****************************");
        System.out.println("Country     Gold   Silver   Bronze");
        System.out.println("-------     ----   ------   ------");
        Collections.sort(countries);

        for(Country country : countries){
            System.out.printf("%-10s %4d %7d %7d\n", country.getName(), country.getGoldMedals(), country.getSilverMedals(),
                    country.getBronzeMedals());
        }
    }

    public void sortByNumberOfMedals(){
        CountryComparator countryComparator = new CountryComparator();
        Collections.sort(countries, countryComparator);
        System.out.println("Ranking table by total medals");
        System.out.println("****************************");
        System.out.printf("%-15s %15s%n", "Country", "Total medals");
        System.out.printf("%-15s %15s%n", "-------", "------------");

        for(Country country : countries){
            System.out.printf("%-15s %15d%n", country.getName(), country.getTotalMedals());
        }
    }

    public void sortByAlphabeticalOrder(){
        for(int i = 0; i < countries.size() - 1; i++){
            for(int j = 1; j < countries.size() - i; j++){
                if(countries.get(j-1).getName().compareTo(countries.get(j).getName()) > 0){
                    Country previous = countries.get(j-1);
                    Country current = countries.get(j);
                    countries.set(j-1, current);
                    countries.set(j, previous);
                }
            }
        }
        System.out.println("Ranking table by alphabetical order");
        System.out.println("****************************");
        System.out.printf("%-15s %15s%n", "Country", "Total medals");
        System.out.printf("%-15s %15s%n", "-------", "------------");

        for(Country country : countries){
            System.out.printf("%-15s %15d%n", country.getName(), country.getTotalMedals());
        }
    }


}
