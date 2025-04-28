import java.io.*;
import java.util.*;

public class M9466 {
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;
    static int nonTeamone = 0;

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();

        queue.add(start);
        path.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int next = arr[cur];

            if (!visited[next]) {
                visited[next] = true;
                queue.add(next);
                path.add(next);
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
                if (!visited[i]) {
                    bfs(i);
                }
            }

            sb.append(nonTeamone).append("\n");
        }

        System.out.println(sb);
    }
}
