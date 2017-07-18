package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Panda on 18-07-2017.
 */

public class GeneralUtils {
    private GeneralUtils(){}

    public static String FormatToHumanDate(String in){
        try{
            Date date = new Date ();
            date.setTime((long)Double.parseDouble(in)*1000);
            List<String> myList = new ArrayList<String>(Arrays.asList(date.toString().split(" ")));

            return String.format("%s %s, %s",
                    myList.get(1),
                    myList.get(2),
                    myList.get(5)
            );
        }catch (Exception ex){
            return "";
        }
    }
}
