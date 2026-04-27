import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int camera = Integer.MAX_VALUE;
        for(int[] route : routes){
            int s = route[0];
            int e = route[1];
            if(camera <= e && camera >= s) continue;
            else {
                camera = e;
                answer++;
            }
        }
        
        return answer;
    }
}