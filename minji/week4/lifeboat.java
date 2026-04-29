package PS.minji.week4;

import java.util.Arrays;
class lifeboat {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int right = people.length - 1;
        int left = 0;

        // people 오름차순 정렬
        Arrays.sort(people);

        while(left <= right) {
            if(people[left] + people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            answer++;
        }
        return answer;
    }
}
