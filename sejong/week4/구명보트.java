import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int s = 0; int e = people.length - 1;
        while(s < e){
            if(people[s] + people[e] <= limit){
                people[s] = 0;
                people[e] = 0;
                answer++;
                s++;
                e--;
            }else e--;
        }
        
        for(int person : people) if(person != 0) answer++;
        
        return answer;
    }
}