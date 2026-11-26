// https://acmicpc.net/problem/2805
// [백준] 나무 자르기 : Java
import java.io.*;
import java.util.*;

public class Main {
    private static int[] woods;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        woods = new int[N];
        int maxH = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int curH = Integer.parseInt(st.nextToken());
            woods[i] = curH;
            maxH = Math.max(maxH, curH);
        }

        int lowH = 0;
        int answerH = 0;
        while (lowH <= maxH) {
            int midH = (lowH + maxH) / 2;

            if (getSum(midH) < M) {
                maxH = midH - 1;
            }
            else {
                answerH = midH;
                lowH = midH + 1;
            }
        }

        System.out.println(answerH);
    }

    private static long getSum(int cutH) {
        long sum = 0;

        for (int wood: woods) {
            sum += Math.max(wood - cutH, 0);
        }

        return sum;
    }
}