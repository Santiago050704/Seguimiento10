package model;

import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {
    public int compare(Country c1, Country c2){
        return Integer.compare(c2.getTotalMedals(), c1.getTotalMedals());
    }
}
