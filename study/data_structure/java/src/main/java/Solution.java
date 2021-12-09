import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

  // Complete the makeAnagram function below.
  static int makeAnagram(String a, String b) {
    List<Character> aCharsList = a.chars().mapToObj(e->(char)e).collect(Collectors.toList());
    List<Character> bCharsList = b.chars().mapToObj(e->(char)e).collect(Collectors.toList());
    List<Character> biggerList = new ArrayList<>();
    List<Character> smallerList = new ArrayList<>();
    if (aCharsList.size() > bCharsList.size()) {
      biggerList = aCharsList;
    } else {
      smallerList = bCharsList;
    }
    List<Character> clonedList = new ArrayList<>();
    biggerList.forEach(t -> clonedList.add(t));
    for(Character item : clonedList) {
      System.out.println(item);
       if (smallerList.contains(item)) {
         System.out.println("contain both => " + item);
         bCharsList.remove(item);
         aCharsList.remove(item);
       }
    }

    System.out.println(aCharsList);
    System.out.println(bCharsList);

    aCharsList.addAll(bCharsList);
    return aCharsList.size();

  }

  /*
   * Complete the runningMedian function below.
   */
  static double[] runningMedian(int[] a) {
    double[] medianArray = new double[a.length];
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(a.length);
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(a.length, Collections.reverseOrder());
    for(int idx = 0; idx < a.length; idx++) {
      int num = a[idx];
      if (minHeap.size() == 0) {
        minHeap.add(num);
      } else if (maxHeap.size() == minHeap.size()) {
        if (minHeap.peek() > num) {
          maxHeap.add(num);
        } else {
          minHeap.add(num);
        }
      } else {
        if (maxHeap.size() < minHeap.size()) {
          if (minHeap.peek() < num) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
          } else {
            maxHeap.add(num);
          }
        } else {
          if (minHeap.peek() < num) {
            minHeap.add(num);
          } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
          }
        }

      }

      if (minHeap.size() == maxHeap.size()) {
        double sum = minHeap.peek() + maxHeap.peek();
        medianArray[idx] = Math.round(sum / 2 * 10.0) / 10.0;
      } else {
          if (maxHeap.size() > minHeap.size()){
            medianArray[idx] = maxHeap.peek();
          } else {
            medianArray[idx] = minHeap.peek();
          }
      }
    }
    return medianArray;
  }

  /*
   * Complete the minimumAverage function below.
   */
  static int minimumAverage(int[][] customers) {
    List<Integer> calList = new ArrayList<>();
    HashMap<Integer, Integer> hm = new HashMap<>();

    Stream<Map.Entry<Integer, Integer>> sorted = hm.entrySet().stream()
            .sorted(Map.Entry.comparingByValue());

    long maxLen = sorted.count();
    for(int idx = 0; idx < maxLen; idx++) {

    }
    /*
    sorted.forEachOrdered(v -> {
      int time = 0;
      for (int innerIdx = 0; innerIdx < v.getKey() + 1; innerIdx++) {
        time += customers[v.getKey()][1];
      }
      calList.add(time - v.getKey());
    });
    for (int idx = 0; idx < customers.length; idx++) {
      int time = 0;
      for (int innerIdx = 0; innerIdx < idx + 1; innerIdx++) {
        time += customers[idx][1];
      }
      calList.add(time - customers[idx][0]);
    }
    */
    Supplier<Stream<Map.Entry<Integer, Integer>>> streamSupplier
            = () -> hm.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue());


    long maxCnt = streamSupplier.get().count();
    // int maxLen = customers.length;
    for (int idx = 0; idx < maxCnt; idx++) {
      int time = streamSupplier.get().limit(idx).mapToInt(v -> v.getValue()).reduce(0, (x,y) -> x+y) - customers[idx][0];
      calList.add(time);
    }
    System.out.println(calList);
    return 0;
  }

  // Complete the isBalanced function below.
  static String isBalanced(String s) {
    Map<String, String> bracketMap = new HashMap<>();
    bracketMap.put("(", ")");
    bracketMap.put("{", "}");
    bracketMap.put("[", "]");
    Stack<String> stack = new Stack<>();
    char[] chs = s.toCharArray();
    for (int fidx = 0; fidx < chs.length; fidx++) {
      String bracket = String.valueOf(chs[fidx]);
      if (!stack.empty() && bracketMap.keySet().contains(stack.peek()) && bracketMap.get(stack.peek()).equals(bracket)) {
        stack.pop();
      } else {
        stack.push(bracket);
      }
    }

    if (stack.empty())
      return "YES";
    else
      return "NO";
  }


  static List<List<Integer>> ClosestXdestinations(int numDestinations,
                                           List<List<Integer>> allLocations,
                                           int numDeliveries)
  {
    // WRITE YOUR CODE HERE
    HashMap<Integer, Double> hm = new HashMap<>();
    int index = 0;
    for(List<Integer> allocation: allLocations) {
      double val = Math.sqrt(Math.pow(allocation.get(0), 2) + Math.pow(allocation.get(1), 2));
      hm.put(index, val);
      index++;
    }
    Stream<Map.Entry<Integer, Double>> sorted = hm.entrySet().stream()
            .sorted(Map.Entry.comparingByValue()).limit(numDeliveries);

    List<List<Integer>> result = new ArrayList<>();
    sorted.forEachOrdered(v -> {
      result.add(allLocations.get(v.getKey()));
    });
    return result;
  }

  private static int min(int x, int y, int z)
  {
    if (x < y)
      return (x < z)? x : z;
    else
      return (y < z)? y : z;
  }

  private static int getCost(int cost) {
    return cost == 0 ? 1000 : cost;
  }

  private static int minCost(List<List<Integer>> cost, int row, int col)
  {
    int i, j;
    int tc[][]=new int[row+1][col+1];

    tc[0][0] = getCost(cost.get(0).get(0));

    /* Initialize first column of total cost(tc) array */
    for (i = 1; i <= row; i++)
      tc[i][0] = tc[i-1][0] + getCost(cost.get(i).get(0));

    /* Initialize first row of tc array */
    for (j = 1; j <= col; j++)
      tc[0][j] = tc[0][j-1] + getCost(cost.get(0).get(j));

    /* Construct rest of the tc array */
    for (i = 1; i <= row; i++)
      for (j = 1; j <= col; j++)
        tc[i][j] = min(tc[i-1][j-1],
                tc[i-1][j],
                tc[i][j-1]) + getCost(cost.get(i).get(j));

    return tc[row][col];
  }

  static int[][] findDestination(int numRows, int numColumns, List<List<Integer>> area) {
    int[][] dest = new int[0][0];
    for (int i = 0; i < numRows; i ++) {
      for (int j = 0; j < numColumns; j++) {
        if(area.get(i).get(j) == 9) {

        }
      }
    }
    return dest;
  }

  private static boolean isSafe(List<List<Integer>> mat, int visited[][], int x, int y) {
    return !(mat.get(x).get(y) == 0 || visited[x][y] != 0);
  }

  private static boolean isValid(int xLimit, int yLimit, int x, int y) {
    return (x < xLimit && y < yLimit && x >= 0 && y >= 0);
  }

  public static int findShortestPath(List<List<Integer>> mat, int visited[][],
                                     int startRow, int startCol, int destRow, int destCol, int min_dist, int dist)
  {
    int xLimit = mat.size();
    int yLimit = mat.get(0).size();

    // System.out.println(" xLimit => " + xLimit + " yLimit => " + yLimit);

    // if destination is found, update min_dist
    if (startRow == destRow && startCol == destCol)
    {
      return Integer.min(dist, min_dist);
    }

    // set (i, j) cell as visited
    visited[startRow][startCol] = 1;

    // go to bottom cell
    if (isValid(xLimit, yLimit, startRow + 1, startCol) && isSafe(mat, visited, startRow + 1, startCol)) {
      min_dist = findShortestPath(mat, visited, startRow + 1, startCol, destRow, destCol, min_dist, dist + 1);
    }

    // go to right cell
    if (isValid(xLimit, yLimit, startRow, startCol + 1) && isSafe(mat, visited, startRow, startCol + 1)) {
      min_dist = findShortestPath(mat, visited, startRow, startCol + 1, destRow, destCol, min_dist, dist + 1);
    }

    // go to top cell
    if (isValid(xLimit, yLimit, startRow - 1, startCol) && isSafe(mat, visited, startRow - 1, startCol)) {
      min_dist = findShortestPath(mat, visited, startRow - 1, startCol, destRow, destCol, min_dist, dist + 1);
    }

    // go to left cell
    if (isValid(xLimit, yLimit, startRow, startCol - 1) && isSafe(mat, visited, startRow, startCol - 1)) {
      min_dist = findShortestPath(mat, visited, startRow, startCol - 1, destRow, destCol, min_dist, dist + 1);
    }

    // Backtrack - Remove (i, j) from visited matrix
    visited[startRow][startCol] = 0;

    return min_dist;
  }

  static int minimumDistance(int numRows, int numColumns, List<List<Integer>> area)
  {
    // WRITE YOUR CODE HERE
    int colLimit = area.size();
    int rowLimit = area.get(0).size();
    int[][] visited = new int[colLimit][rowLimit];
    int result = findShortestPath(area, visited, 0, 0, 4, 3, Integer.MAX_VALUE, 0);

    System.out.println("result => " + result);

    int[][] sum = new int[numRows][numColumns];

    sum[0][0] = area.get(0).get(0);

    for (int i = 0; i < numRows; i ++) {
      for (int j = 0; j < numColumns; j++) {
        if (i == 0) {
          if (j == 0) {
            sum[i][j] = area.get(i).get(j);
          }
          else {
            sum[i][j] = sum[i][j - 1] + area.get(i).get(j);
          }
        }
        else if (j == 0) {
          if (i == 0) sum[i][j] = area.get(i).get(j);
          else  sum[i][j] = sum[i-1][j] + area.get(i).get(j);
        } else {
          sum[i][j] = Math.min(sum[i-1][j], sum[i][j-1]) + area.get(i).get(j);
        }
      }
    }

    /*
    1 1 1 1
    0 1 1 1
    0 1 0 1
    1 1 9 1
    0 0 1 1
     */
    int row = sum.length;
    int col = sum[0].length;
    for (int i = 0; i < row; i ++) {
      for (int j = 0; j < col; j++) {
        System.out.print(sum[i][j] + " ");
      }
      System.out.println("---------- new row -----------------");
    }
    return sum[numRows - 1][numColumns - 1] + 1;
  }

  public static void main(String[] args) throws IOException {

    List<List<Integer>> a = new ArrayList<>();
    ArrayList<Integer> list1 = new ArrayList<Integer>() {{
      add(1);
      add(1);
      add(1);
      add(1);
    }};

    ArrayList<Integer> list2 = new ArrayList<Integer>() {{
      add(0);
      add(1);
      add(1);
      add(1);
    }};

    ArrayList<Integer> list3 = new ArrayList<Integer>() {{
      add(0);
      add(1);
      add(0);
      add(1);
    }};

    ArrayList<Integer> list4 = new ArrayList<Integer>() {{
      add(1);
      add(1);
      add(9);
      add(1);
    }};

    ArrayList<Integer> list5 = new ArrayList<Integer>() {{
      add(0);
      add(0);
      add(1);
      add(1);
    }};
    a.add(list1);
    a.add(list2);
    a.add(list3);
    a.add(list4);
    a.add(list5);

    System.out.println( minCost(a, 4, 3) );
    int r = minimumDistance(4, 3, a);
    System.out.println(r);
    /*
    List<List<Integer>> a = new ArrayList<>();
    ArrayList<Integer> list1 = new ArrayList<Integer>() {{
      add(1);
      add(2);
    }};

    ArrayList<Integer> list2 = new ArrayList<Integer>() {{
      add(3);
      add(4);
    }};

    ArrayList<Integer> list3 = new ArrayList<Integer>() {{
      add(1);
      add(-1);
    }};
    a.add(list1);
    a.add(list2);
    a.add(list3);
    System.out.println( ClosestXdestinations(3, a, 2) );
    */

    System.out.println(makeAnagram("fcrxzwscanmligyxyvym","jxwtrhvujlmrpdoqbisbwhmgpmeoke"));
    System.out.println(makeAnagram("abc","cde"));

    int[] param = {37632,
        10118,
        25334,
        84618,
        87339,
        97852,
        91683,
        99232,
        31552,
        90453,
        46239,
        89445,
        23303,
        46262,
        65147,
        15646,
        49990,
        57359,
        18685};

        // {1,2,3,4,5,6,7,8,9,10};
    runningMedian(param);
    // String s = "{[()]}";
    String s = "{(([])[])[]]}";
    // String s = "{{[[(())]]}}";
    String result = isBalanced(s);
    System.out.println(result);

    int[][] avgArray = {{0,3}, {1,9}, {2,5}};
    minimumAverage(avgArray);
  }
}
