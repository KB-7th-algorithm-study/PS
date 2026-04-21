package PS.minji.week3;

import java.io.*;
import java.util.StringTokenizer;

public class intTriangle_1932 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // 삼각형 크기 입력받기
            int n = Integer.parseInt(br.readLine());

            // 삼각형 배열 만들기
            int[][] triangle = new int[n+1][n+1];
            // 메모장 만들기
            int[][] dpMemo = new int[n+1][n+1];

            // 삼각형 입력 받아서 배열에 넣기
            for(int i=1; i<=n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=1; j<=i; j++) {
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 시작점 세팅


            for(int i=1; i<=n; i++) {
                for(int j=1; j<=i; j++) {
                    // i가 1이라면 1가지뿐
                    if(i==1) {
                        dpMemo[i][j] = triangle[i][j];
                    }

                    // i가 2라면 경우는 2가지뿐
                    if(i==2) {
                        dpMemo[2][1] = triangle[1][1] + triangle[2][1];
                        dpMemo[2][2] = triangle[1][1] + triangle[2][2];
                    }

                    // i가 3이상이라면
                    if(i>=3) {
                        // 양끝은 그대로 내려와 더하기
                        if(j==1) {
                            dpMemo[i][j] = dpMemo[i-1][j] + triangle[i][j];
                        } else if (j==i) {
                            dpMemo[i][j] = dpMemo[i-1][j-1] + triangle[i][j];
                        } else {
                            // 가운데는 양쪽에서 내려와서 더 큰 값을 저장
                            dpMemo[i][j] = Math.max(dpMemo[i-1][j-1] + triangle[i][j], dpMemo[i-1][j] + triangle[i][j]);
                        }
                    }


                }
            }

            int max = dpMemo[n][1];
            // dp 마지막 줄에서 가장 큰 값 출력
            for(int i=1; i<=n; i++) {
                if(dpMemo[n][i] >= max) {
                    max = dpMemo[n][i];
                }
            }
            System.out.println(max);

        }

}
