package algo.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MaxNumberKSumPairs1679Test {

    private final MaxNumberKSumPairs1679 maxNumberKSumPairs = new MaxNumberKSumPairs1679();

    @Test
    public void testMaxOperations() {
        int ans = maxNumberKSumPairs.maxOperations(new int[]{1, 2, 3, 4}, 5);
        System.out.println("answer num -> " + ans);
        // assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }

}
