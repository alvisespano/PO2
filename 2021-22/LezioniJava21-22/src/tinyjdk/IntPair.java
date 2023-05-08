package tinyjdk;

public class IntPair implements Comparable<IntPair> {
    public final int first, second;

    public IntPair(int a, int b) {
        first = a;
        second = b;
    }

    @Override
    public int compareTo(IntPair o) {
        return first * o.first - second * o.second;
    }
}
