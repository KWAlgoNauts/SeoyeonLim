import java.io.*;
import java.util.*;

public class M2146 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 1. 각 섬 라벨링
        int label = 2;  // 섬 번호는 2부터 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    labelIsland(i, j, label++);
                }
            }
        }

        // 2. 각 섬의 경계에서 BFS → 가장 가까운 다른 섬까지 거리 측정
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    result = Math.min(result, bfs(i, j, map[i][j]));
                }
            }
        }

        System.out.println(result);
    }

    // DFS로 라벨링
    private static void labelIsland(int x, int y, int label) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = label;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dir) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N
                        && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = label;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    // BFS로 다리 최단 거리 측정
    private static int bfs(int x, int y, int label) {
        boolean[][] bVisited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        bVisited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1], dist = cur[2];

            for (int[] d : dir) {
                int nx = cx + d[0];
                int ny = cy + d[1];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !bVisited[nx][ny]) {
                    // 다른 섬에 도달하면 거리 리턴
                    if (map[nx][ny] != 0 && map[nx][ny] != label) {
                        return dist;
                    }

                    if (map[nx][ny] == 0) {
                        bVisited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
