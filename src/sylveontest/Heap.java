package sylveontest;
import battlecode.common.*;

import java.util.*;
public class Heap {

    public int[] arr;
    public int idx = 0;

    public Heap() {
        arr = new int[65];
        arr[0] = 1_000_000;
    }


    public void insert(int item) {
        arr[++idx] = item;
        int index = idx;
        while (arr[index >> 1] < item) {
            arr[index] = arr[index >> 1];
            index >>= 1;
        }
        arr[index] = item;
    }


}
