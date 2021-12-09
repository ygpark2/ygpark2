package ds.sort;

import java.util.Arrays;

public class QuickSort {


    public void quickSort(int[] arr, int l, int h) {
        if (l < h) {
            int partition = partition(arr, l, h);

            System.out.println(Arrays.toString(arr));
            quickSort(arr, l, partition - 1);
            quickSort(arr, partition, h);
        }

    }

    private int partition(int[] arr, int l, int h) {

        int pivot = arr[h];
        int i = l;

        for (int j = l; j < h; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = arr[h];
        arr[h] = temp;

        return i;
    }
}
