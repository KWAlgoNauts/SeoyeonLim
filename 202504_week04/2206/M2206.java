import java.io.*;
import java.util.*;

public class M2206 {
    static int N, M;
    static int[][] arr;
    static boolean[][][] visited;
    static Queue<int[]> queue;

    static int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private static int bfs() {
        queue.add(new int[]{0, 0, 0, 1}); // (x, y, broken, distance)
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int broken = cur[2];
            int dist = cur[3];

            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            for (int u = 0; u < 4; u++) {
                int nx = x + dir[u][0];
                int ny = y + dir[u][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (arr[nx][ny] == 0 && !visited[nx][ny][broken]) {
                        visited[nx][ny][broken] = true;
                        queue.add(new int[]{nx, ny, broken, dist + 1});
                    } else if (arr[nx][ny] == 1 && broken == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        queue.add(new int[]{nx, ny, 1, dist + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mapInfo = br.readLine().split(" ");
        N = Integer.parseInt(mapInfo[0]);
        M = Integer.parseInt(mapInfo[1]);

        arr = new int[N][M];
        visited = new boolean[N][M][2];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] map = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(map[j]);
            }
        }

        System.out.println(bfs());
    }
}
