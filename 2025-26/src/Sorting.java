import tinyjdk.dais.unive.it.ArrayList;

import java.util.Collections;
import java.util.List;

public class Sorting {

    public static void main(String[] args) {

        List<Integer> l = List.of(45, 78, 234, -456, 2);

        Collections.sort(l, (a, b) -> a - b);
    }

}
