package DynamicProgram;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
 *  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2,
 *  word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 */
public class LongestStringChain {


    public static int longestStrChain(String[] words) {


        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        Map<String,Integer> strLengthMap = new HashMap<>();

        int maxCount = 0;

        for(String work : words){
            if(strLengthMap.isEmpty()){
                strLengthMap.put(work,1);
                maxCount = 1;
                continue;
            }
            int tempMax = 1;
            for(int i = 0; i < work.length(); i ++){
                String temp = work.substring(0,i) + work.substring(i+1);
                if(strLengthMap.containsKey(temp)){
                    tempMax = Math.max(tempMax, strLengthMap.get(temp) + 1);
                }
            }
            strLengthMap.put(work,tempMax);
            if(tempMax > maxCount){
                maxCount = tempMax;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(arr));
    }
}
