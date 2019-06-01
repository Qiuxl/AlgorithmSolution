package DynamicProgram;

import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTicket {


    public int mincostTickets(int[] days, int[] costs) {

        int[] allCost = new int[366];
        allCost[0] = 0;
        Set<Integer> hasToTravel = new HashSet<>();
        for(int day : days){
            hasToTravel.add(day);
        }
        for(int i = 1; i < 366; i ++){
            if(!hasToTravel.contains(i)){
                allCost[i] = allCost[i-1];
            }else {
                allCost[i] = Math.min(allCost[i-1] + costs[0], allCost[Math.max(0, i - 7)] + costs[2]);
                allCost[i] = Math.min(allCost[i], allCost[Math.max(0, i - 30)] + costs[3]);
            }
        }
        return allCost[365];

    }
}
