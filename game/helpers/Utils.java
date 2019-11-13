package aog2.game.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author adam
 * class Utils
 * helper class
 */
public class Utils {

    //convert a file path to a string
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }

            br.close();
        } catch (IOException e) {
        }
        return builder.toString();
    }

    //convert a string to a number
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    //rounds a number down to the nearest whole number
    // (used for proper rendering according to offsets)
    public static int round(int i, int v) {
        return (int) (Math.round(i / v) * v);
    }
}
