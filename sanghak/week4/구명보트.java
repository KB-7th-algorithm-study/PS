package PS.sanghak.week4;

import java.util.Arrays;
class 구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int answer = 0;

        while(left<=right){
            if(people[left] + people[right] <=limit){
                left++;
                right--;
            }else{
                right--;
            }
            answer++;
        }

        return answer;
    }
}
