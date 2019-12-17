package services;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author Hai
 */
public class StringService {
    
    public static String covertToString(String value) {
        try {
              String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
              Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
              return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            System.out.println("services.StringService.covertToString()");
            System.out.println(ex.getMessage());
        }
          return null;
    }

}
