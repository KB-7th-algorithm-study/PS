import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int front = 0;
        int behind = people.length-1;

        Arrays.sort(people);

        while(people[front]!=-1){
            if(people[behind]+people[front]>limit){
                people[behind]=-1;
                behind--;
                answer++;
            }
            else{
                people[front]=-1;
                people[behind]=-1;
                behind--;
                front++;
                answer++;
            }
        }

        return answer;
    }
}