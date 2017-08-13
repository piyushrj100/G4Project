package g4eis.ontern.g4project;

/**
 * Created by piyush on 1/8/17.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableData {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> Mumbai = new ArrayList<String>();
        Mumbai.add("Gateway Park");
        Mumbai.add("Nortel Park");
        Mumbai.add("Vidyasagar Building");
        Mumbai.add("Akruti Trade Center");
        Mumbai.add("Search all campuses!");

        List<String> Kolkata = new ArrayList<String>();
        Kolkata.add("Gitanjali Park");
        Kolkata.add("SDF Building");
        Kolkata.add("Unitech");
        Kolkata.add("Delta Park");
        Kolkata.add("Search all campuses!");

        List<String> Chennai = new ArrayList<String>();
        Chennai.add("Mangal Tirth Estate");
        Chennai.add("Tower Victorie");
        Chennai.add("DLF IT Park");
        Chennai.add("Sonex Towers");
        Chennai.add("Search all campuses!");

        List<String> Delhi = new ArrayList<String>();
       Delhi.add("PTI Building");
        Delhi.add("Noida");
        Delhi.add("Udyog Vihar");
        Delhi.add("TCS Towers");
        Delhi.add("Search all campuses!");

        List<String> Hyderabad = new ArrayList<String>();
        Hyderabad.add("Deccan Park");
        Hyderabad.add("Autoplaza Building");
        Hyderabad.add("Synergy Park");
        Hyderabad.add("Kohinoor Park");
        Hyderabad.add("Search all campuses!");

        List<String> Bangalore = new ArrayList<String>();
        Bangalore.add("Gandhi Nagar");
        Bangalore.add("Abhilash Building");
        Bangalore.add("International Tech Park");
        Bangalore.add("SJM Towers");
        Bangalore.add("Search all campuses!");

        List<String> Pune = new ArrayList<String>();
       Pune.add("Hadapsar Industrial Estate");
        Pune.add("Godrej Millenium Condominium");
        Pune.add("Sahyadri Park");
        Pune.add("Electronic Sadan");
        Pune.add("Search all campuses!");





        expandableListDetail.put("Mumbai",Mumbai);
        expandableListDetail.put("Kolkata", Kolkata);
        expandableListDetail.put("Chennai", Chennai);
        expandableListDetail.put("Delhi",Delhi);
        expandableListDetail.put("Bangalore", Bangalore);
        expandableListDetail.put("Hyderabad", Hyderabad);
        expandableListDetail.put("Pune", Pune);

        return expandableListDetail;
    }
}