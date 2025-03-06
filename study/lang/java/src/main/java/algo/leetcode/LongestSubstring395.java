package algo.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongestSubstring395 {

    public int longestSubstring(String s, int k) {
        int [] a = {};
        a[0] = 3;
        a[1] = 3;
        List<Integer> d = Arrays.stream(a).boxed().toList();;
        List<Character> charList = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        return longestSubStr(charList, k, 0);
    }

    public int longestSubStr(List<Character> charList, int t, int point) {
        if (charList.isEmpty()) {
            return 0;
        }
        Character firstChar = charList.remove(0);
        long num = charList.stream().filter(item -> item == firstChar).count();
        charList.removeIf( item -> item == firstChar);
        if ( num >= t) {
            point += Long.valueOf(num).intValue();
        }
        longestSubStr(charList, t, point);
        return point;
    }
}
