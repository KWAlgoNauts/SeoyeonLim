import java.io.*;
import java.util.*;

public class M9466 {
    static int[] arr;
    static boolean[] visited; //학생 방문 확인 용
    static boolean[] finished; // 사이클에 판단 여부가 끝난 아이들
    static int nonTeamone = 0;

    private static void bfs(int start) {
        //이어져 있는 애들을 탐색해야하니깐
        Queue<Integer> queue = new LinkedList<>(); //탐색할 학생 번호 담는 큐
        //이번 탐색에서 사이클이 형성 됐을 수도 있으니깐
        List<Integer> path = new ArrayList<>(); //방문한 학생들 기록하는 리스트

        queue.add(start); // 큐에 탐색할 애 넣기
        path.add(start); // 사이클에 포함하기

        while (!queue.isEmpty()) {
            int cur = queue.poll(); //
            int next = arr[cur]; //

            //finish
            if(!finished[next]) {
                return;
            } else {
                // next를 이미 방문했을 때
                if (!finished[next]) {
                    // next가 path 안에 있으면 사이클 발견
                    int idx = path.indexOf(next);
                    for (int i = idx; i < path.size(); i++) {
                        finished[path.get(i)] = true;
                    }
                }
                break;
            }
        }

        // 사이클에 포함되지 않은 학생 처리
        for (int node : path) {
            if (!finished[node]) {
                finished[node] = true;
                nonTeamone++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1]; // 1번부터 n번까지
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            nonTeamone = 0;

            String[] cmd = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(cmd[i - 1]);
            }

            for (int i = 1; i <= n; i++) {
                //판단되지 않은 숫자다 하면 bfs
                if (!finished[i]) {
                    bfs(i);
                }
            }

            sb.append(nonTeamone).append("\n");
        }

        System.out.println(sb);
    }
}
