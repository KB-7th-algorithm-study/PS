package PS.minji;

import java.io.*;
import java.util.*;

public class virus_2606 {
    // 감염된 컴퓨터 수 (전역변수로 선언)
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 컴퓨터 수 입력받기
        int computerNum = Integer.parseInt(br.readLine());
        // 쌍의 개수
        int pairNum = Integer.parseInt(br.readLine());

        // 그래프 세팅
        ArrayList<Integer>[] graph = new ArrayList[computerNum +1];

        // 그래프 초기화
        for(int i=1; i<=computerNum; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<pairNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 연결된 컴퓨터 번호 입력받기
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 간선 추가 (양방향)
            graph[a].add(b);
            graph[b].add(a);
        }

        // 방문 처리
        boolean[] visited = new boolean[computerNum+1];

        DFS(1, visited, graph);

        System.out.println(count);
    }

    public static void DFS(int node, boolean[] visited, ArrayList<Integer>[] graph) {
        // 방문한 노드 체크
        visited[node] = true;

        for(int neighbor : graph[node]) {
            if(!visited[neighbor]) {
                // 연결된 컴퓨터이고 방문한 적이 없다면 감염된 컴퓨터 수++
                count++;
                // 재귀 호출
                DFS(neighbor, visited, graph);
            }
        }
    }
}
