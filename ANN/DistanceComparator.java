import java.util.Comparator;

public class DistanceComparator implements Comparator<LabelledPoint> {
    public int compare(LabelledPoint p1, LabelledPoint p2) {
        if (p1.getKey() < p2.getKey()) {
            return -1;
        } else if (p1.getKey() > p2.getKey()) {
            return 1;
        } else {
            return 0;
        }
    }
}
