package PS.sanghak.week4;

import java.util.*;
class 단속카메라 {
    public int solution(int[][] routes) {

        //진출기준 -15 -13 -5 -3
        Arrays.sort(routes, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        int camera = routes[0][1];
        int answer = 1;

        for(int i=0; i<routes.length; i++){
            if(camera<routes[i][0]){
                camera = routes[i][1];
                answer++;
            }
        }
        return answer;
    }
}
