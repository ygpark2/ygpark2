package org.pyg.cp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;


public class NthMultiple {


    public int numOfNth(int n, int k) {

        String numStr = generate(() -> "1").limit(n).collect(joining());
        int num = Integer.parseInt(numStr, 2);

        List<String> resultList = IntStream.range(1, num)
                .filter(i -> i % 3 == 0)
                .mapToObj(x -> Integer.toBinaryString(x))
                .filter(bs -> bs.chars().filter(ch -> ch == '1').count() == k)
                .collect(Collectors.toList());

        System.out.println("resultList => " + resultList);
        return resultList.size();
    }
}
