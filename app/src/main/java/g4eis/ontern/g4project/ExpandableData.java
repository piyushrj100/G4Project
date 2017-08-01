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
        Mumbai.add("Campus1");
        Mumbai.add("Campus2");
        Mumbai.add("Campus3");
        Mumbai.add("Campus4");


        List<String> Kolkata = new ArrayList<String>();
        Kolkata.add("Campus1");
        Kolkata.add("Campus2");
        Kolkata.add("Campus3");
        Kolkata.add("Campus4");


        List<String> Chennai = new ArrayList<String>();
        Chennai.add("Campus1");
        Chennai.add("Campus2");
        Chennai.add("Campus3");
        Chennai.add("Campus4");


        List<String> Delhi = new ArrayList<String>();
       Delhi.add("Campus1");
        Delhi.add("Campus2");
        Delhi.add("Campus3");
        Delhi.add("Campus4");


        List<String> Hyderabad = new ArrayList<String>();
        Hyderabad.add("Campus1");
        Hyderabad.add("Campus2");
        Hyderabad.add("Campus3");
        Hyderabad.add("Campus4");


        List<String> Bengaluru = new ArrayList<String>();
        Bengaluru.add("Campus1");
        Bengaluru.add("Campus2");
        Bengaluru.add("Campus3");
        Bengaluru.add("Campus4");

        List<String> Pune = new ArrayList<String>();
       Pune.add("Campus1");
        Pune.add("Campus2");
        Pune.add("Campus3");
        Pune.add("Campus4");





        expandableListDetail.put("Mumbai",Mumbai);
        expandableListDetail.put("Kolkata", Kolkata);
        expandableListDetail.put("Chennai", Chennai);
        expandableListDetail.put("Delhi",Delhi);
        expandableListDetail.put("Bengaluru", Bengaluru);
        expandableListDetail.put("Hyderabad", Hyderabad);
        expandableListDetail.put("Pune", Pune);

        return expandableListDetail;
    }
}