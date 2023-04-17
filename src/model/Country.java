package model;
public class Country implements Comparable<Country>{
    private String name;
    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;

    public Country(String name) {
        this.name = name;
        this.goldMedals = 0;
        this.silverMedals = 0;
        this.bronzeMedals = 0;
    }

    public void increaseGoldMedals(int quantity){
        this.goldMedals += quantity;
    }

    public void increaseSilverMedals(int quantity){
        this.silverMedals += quantity;
    }

    public void increaseBronzeMedals(int quantity){
        this.bronzeMedals += quantity;
    }

    public int getTotalMedals(){
        return goldMedals + silverMedals + bronzeMedals;
    }

    @Override
    public int compareTo(Country otherCountry){
        int result = Integer.compare(otherCountry.goldMedals, this.goldMedals);
        if(result == 0) {
            result = Integer.compare(otherCountry.silverMedals, this.silverMedals);
            if (result == 0) {
                result = Integer.compare(otherCountry.bronzeMedals, this.bronzeMedals);
                if(result == 0){
                    result = this.name.compareTo(otherCountry.name);
                }
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public void setGoldMedals(int goldMedals) {
        this.goldMedals = goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public void setSilverMedals(int silverMedals) {
        this.silverMedals = silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }

    public void setBronzeMedals(int bronzeMedals) {
        this.bronzeMedals = bronzeMedals;
    }
}
