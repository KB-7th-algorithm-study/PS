package PS.minji.week6;

public class twoSum2 {
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int[] index = new int[2];

            int index1 = 0;
            int index2 = numbers.length-1;

            while(index1 < index2) {
                int sum = numbers[index1] + numbers[index2];
                // target > left + right
                // left값을 올리기
                if(target > sum) {
                    index1++;
                    // target < left + right
                    // right값을 내리기
                }

                if(target < sum) {
                    index2--;
                    // target == left + right
                    // 정답에 인덱스값 넣기
                }

                if(target == sum) {
                    index[0] = index1+1;
                    index[1] = index2+1;
                    break;
                }
            }

            return index;
        }
    }
}
