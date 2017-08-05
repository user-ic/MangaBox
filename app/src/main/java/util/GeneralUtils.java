package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public static String FormatTitle(String in){
        try{
            Double n = Double.parseDouble(in);
            return "";
        }catch (Exception ex){
        }
        if(in.length() > 0 && !in.equals(null) && !in.equals(false) && !in.equals("null"))
            return  in;
        return "";
    }
    public static String GetDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
