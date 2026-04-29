import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1]));

        int camera = routes[0][1];
        answer += 1;
        
        for(int i=1; i<routes.length; i++){
            if(routes[i][0] <= camera) continue; //카메라에 찍히므로 넘김
            else{
                camera = routes[i][1];
                answer += 1;
            }
        }
        return answer;
    }
}