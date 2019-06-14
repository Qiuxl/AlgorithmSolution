package datastructdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 实现插入和更新的O（1）操作，并且返回时候数据随机是一样的
 */
public class RandomizedSet {

    Map<Integer,Integer> locationMap;
    List<Integer> nums ;
    Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.locationMap = new HashMap<>();
        this.nums = new ArrayList<>();
        this.random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(locationMap.containsKey(val)){
            return false;
        }
        locationMap.put(val,nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!locationMap.containsKey(val)){
            return false;
        }
        int location = locationMap.get(val);
        if(location < nums.size() - 1){
            int end = nums.get(nums.size() - 1);
            nums.set(location, end);
            locationMap.put(end,location);
        }
        nums.remove(nums.size() - 1);
        locationMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if(nums.size() == 0){
            return 0;
        }
        return nums.get(random.nextInt(nums.size()));
    }
}
