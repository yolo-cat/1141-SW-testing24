package medal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MedalSorter {
    public static void main(String[] args) {
        List<Medal> medalsList = new ArrayList<>();

        // Add medals to the list
        medalsList.add(new Medal("United States", 3, 8, 9));
        medalsList.add(new Medal("France", 5, 8, 3));
        medalsList.add(new Medal("Japan", 6, 2, 4));
        medalsList.add(new Medal("China", 5, 5, 2));

        // Sort based on gold medals in descending order
        medalsList.sort(Comparator.comparingInt(Medal::getGold).reversed());

        // Print sorted list
        for (Medal medal : medalsList) {
            System.out.println(medal);
        }
    }
}
