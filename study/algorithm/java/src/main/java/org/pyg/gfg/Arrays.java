package org.pyg.gfg;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Arrays {

    // https://practice.geeksforgeeks.org/problems/pair-with-greatest-product-in-array/0
    public int findGreatestProduct(String[] args) {
        Stream<String> valStream = Stream.of(args);
        IntStream intStream = valStream.mapToInt(num -> Integer.parseInt(num)).sorted();
        List<Integer> intList = intStream.boxed().collect(Collectors.toList());

        int max = intList.stream().max(Integer::compareTo).orElse(0);

        int result = -1;
        for (int idx = 0; idx < intList.size() - 1; idx++) {
            int x = intList.get(idx);
            int y = intList.get(idx + 1);
            System.out.println("x val =>· " + x + " y val =>· " + y);
            System.out.println("max val =>· " + max);
            System.out.println("product val =>· " + x*y);
            if (x*y > max) {
                break;
            } else {
                if (intList.contains(x * y)) {
                    result = x * y;
                    break;
                }
            }
        }

        return result;
    }
}
