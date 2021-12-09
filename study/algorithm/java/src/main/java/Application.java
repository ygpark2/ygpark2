
import org.pyg.gfg.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygpark2 on 16. 2. 17.
 */
public class Application {

    public static void main(String [] args) {

        Arrays gfgArrays = new Arrays();

        // String[] nums = {"88", "57", "44", "92", "28", "66", "60", "37", "33", "52", "38", "29", "76", "8", "75"};
        // String[] nums = {"10", "2", "2", "4", "30", "35"};
        // String[] nums = {"2", "5", "7", "8"};
        String[] nums = {"10", "3", "5", "30", "35"};
        System.out.println(gfgArrays.findGreatestProduct(nums));

    }
}
