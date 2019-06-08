package DynamicProgram;


import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given a series of video clips from a sporting event that lasted T seconds.
 * These video clips can be overlapping with each other and have varied lengths.
 *
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].
 * We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 *
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).
 *
 * If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation:
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 */
public class VideoStitching {


    public static int videoStitching(int[][] clips, int T) {

        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int[] result = new int[T+1];

        for(int[] pair : clips){
            int minNum = -1;
            if(pair[0] == 0){
                // 从0开始
                minNum = 0;
            }else {
                // 开始这个点片段了
                if( pair[0] <= T && result[pair[0]] != 0){
                    minNum = result[pair[0]];
                }
            }
            if(minNum != -1){
                for(int index = pair[0]; index <= pair[1] && index <= T; index ++){
                    if(result[index] == 0){
                        result[index] = minNum + 1;
                    }else {
                        result[index] = Math.min(minNum + 1 , result[index]);
                    }
                }
            }
        }

        return result[T] == 0 ? -1 : result[T];
    }


    public static int videoStitching_ver2(int[][] clips, int T) {

        Arrays.sort(clips, (o1, o2) -> o1[0] <= o2[0] ? -1 : 1);

        int maxRemote = 0;
        int count = 0;

        int index = 0;
        while ( index < clips.length){
            int nextMax = -1;
            while (index < clips.length && clips[index][0] <= maxRemote){
                nextMax = Math.max(nextMax, clips[index][1]);
                index ++;
            }
            if( nextMax > maxRemote){
                maxRemote = nextMax;
                count ++;
            }else {
                // 出现断层，退出
                break;
            }
            if( maxRemote >= T){
                return count;
            }
        }
        if(maxRemote >= T){
            return count;
        }
        return -1;
    }
    public static void main(String[] args) {
      //  int[][] arr = new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        long begin = System.currentTimeMillis();
        for(int i = 0; i < 10000000; i ++){
            int[][] arr = new int[][]{{8,10},{17,39},{18,19},{8,16},{13,35},{33,39},{11,19},{18,35}, {1,2}, {1 ,3}};
            Arrays.sort(arr, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                     return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
                }
            });
        }
       System.out.println("非lambda, 一共耗时: "+ (System.currentTimeMillis() - begin) + " ms");

        long begin1 = System.currentTimeMillis();
        for(int i = 0; i < 10000000; i ++){
            int[][] arr = new int[][]{{8,10},{17,39},{18,19},{8,16},{13,35},{33,39},{11,19},{18,35}, {1,2}, {1 ,3}};
            Arrays.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        }
        System.out.println("lambda, 一共耗时: "+ (System.currentTimeMillis() - begin1) + " ms");

    }
}
