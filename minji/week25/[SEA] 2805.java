import java.io.*;

// [SEA] 2805 농작물 수확하기
class Solution {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(nextLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(nextLine());
            int mid = n / 2;
            int sum = 0;

            for (int i = 0; i < n; i++) {
                String row = nextLine().replace(" ", "");
                // 가운데 행에서 멀어질수록 양쪽에서 한 칸씩 줄어드는 마름모
                int gap = Math.abs(i - mid);
                for (int j = gap; j < n - gap; j++) {
                    sum += row.charAt(j) - '0';
                }
            }

            sb.append('#').append(tc).append(' ').append(sum).append('\n');
        }
        System.out.print(sb);
    }

    // 빈 줄은 건너뛰고 다음 줄 읽기
    static String nextLine() throws IOException {
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        return line.trim();
    }
}
