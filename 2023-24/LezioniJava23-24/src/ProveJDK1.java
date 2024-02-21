import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProveJDK1 {

    public static void main(String[] args) {

        List<Integer> l = new ArrayList<Integer>();
        l.add(21);
        l.add(2);
        l.add(456);

        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }

        for (int i = 0; i < l.size(); ++i) {
            int n = l.get(i);
            System.out.println(n);
        }
    }
}
