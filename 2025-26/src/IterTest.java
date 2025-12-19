import java.util.*;

public class IterTest {

    public static void main(String[] args) {
        Collection<Integer> a = new ArrayList<Integer>();
        a.add(10);
        a.add(245);
        a.add(387);

        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            Integer n = it.next();
            System.out.println(n);
        }

    }
}
