import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);

        int camera = routes[0][1];
        int i=0;
        while(i<routes.length){
            if(camera>=routes[i][0]){
                i+=1;
            }
            else{
                camera = routes[i][1];
                answer++;
            }
        }

        return answer;
    }
}