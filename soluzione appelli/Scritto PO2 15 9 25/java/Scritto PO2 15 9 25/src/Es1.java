import javax.script.Bindings;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;

public class Es1 {
    public enum Priority { HIGH, MEDIUM, LOW };

    private Map<Priority, Consumer<Instant>> map = new EnumMap<>(Priority.class);

    public void register(Priority priority, Consumer<Instant> consumer) {
        map.put(priority, consumer);
    }

    public void dispatcher() {
        SortedSet<Map.Entry<Priority, Consumer<Instant>>> set = new TreeSet<>(Comparator.comparing(Map.Entry::getKey));
        set.addAll(map.entrySet());
        for (Map.Entry<Priority, Consumer<Instant>> p : set) {
            p.getValue().accept(Instant.now());
        }
    }

}
