package algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumberKSumPairs1679 {

    public int maxOperations(int[] nums, int k) {
        List<Integer> numList = Arrays.stream(nums).boxed().toList();
        return getAllSubset(numList, new ArrayList<Integer>(), 0, 0, k);
    }

    public int getAllSubset(List<Integer> numList, List<Integer> ansList, int ansCnt, int idx, int k) {
        if (idx == numList.size()) {
            if (ansList.isEmpty()) {
                return 0;
            } else {
                int sum = ansList.stream().mapToInt(Integer::intValue).sum();
                return sum == k ? 1 + ansCnt : ansCnt;
            }
        }

        ansList.add(numList.get(idx));
        return getAllSubset(numList, ansList, ansCnt, idx + 1, k);
    }

}
