package PS.minji.week6;

public class 연속된부분수열의합 {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int[] answer = new int[2];
            int left = 0;
            int sum = 0;
            // int tempLength = sequence.length-left;
            int tempLength = sequence.length + 1;;


            for(int right=0; right<sequence.length; right++) {
                sum += sequence[right];

                while(sum > k) {
                    sum = sum - sequence[left];
                    left++;
                }

                if(sum == k) {
                    int len = right - left + 1;
                    if(tempLength > len) {
                        answer[0] = left;
                        answer[1] = right;
                        tempLength = len;
                    }

                    // if(tempLength == len && answer[0] > left) {
                    //     answer[0] = left;
                    //     answer[1] = right;
                    // }
                }

            }

            return answer;
        }
    }
}
