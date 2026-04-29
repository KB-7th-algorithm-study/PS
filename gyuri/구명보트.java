import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] people, int limit) {
        // 1. 몸무게 오름차순 정렬
        Arrays.sort(people);

        int idx = 0; // 가장 가벼운 사람을 가리키는 변수
        int answer = 0; // 필요한 보트 개수

        // 2. 가장 무거운 사람부터 처리
        for(int i = people.length-1; i >= idx ; i--) {
            // 3. 가장 가벼운 사람과 같이 탈 수 있는 경우
            if(people[idx] + people[i] <= limit) {
                idx++; // 가벼운 사람도 함께 태웠으므로 다음 사람으로 이동
            }
            // 4. 무거운 사람은 무조건 한 번은 보트를 타야 하므로 매 반복마다 보트 개수 증가
            answer++;
        }

        return answer;
    }
}