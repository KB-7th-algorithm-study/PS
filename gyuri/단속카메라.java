import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);

        int camera = routes[0][1]; // 처음 설치한 카메라 위치
        int answer = 1; // 설치한 카메라 개수

        for(int i = 1; i < routes.length; i++) {
            if(camera < routes[i][0]) { // 현재 설치한 카메라의 위치를 넘으면
                answer++; // 카메라 추가 설치
                camera = routes[i][1]; // 카메라 위치 갱신
            }
        }

        return answer;
    }
}