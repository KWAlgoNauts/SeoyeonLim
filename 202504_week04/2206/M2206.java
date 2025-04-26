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

    private static int bfs(){
        queue.add(new int[]{0, 0, 0}); // x, y, broken
        visited[0][0][0] = true;
        int path = 1;

        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int broken = cur[2];

                if(x == (N -1) && y == (M - 1)){
                    return path;
                }

                for(int u = 0; u < 4; u++){
                    int nx = x + dir[u][0];
                    int ny = y + dir[u][1];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                        if(arr[nx][ny] == 1 && broken == 0 && !visited[nx][ny][1]){
                            queue.add(new int[]{nx, ny, 1});
                            visited[nx][ny][1] = true;
                        }
                        else if(arr[nx][ny] == 0 && !visited[nx][ny][broken]){
                            queue.add(new int[]{nx, ny, broken});
                            visited[nx][ny][broken] = true;
                        }
                    }
                }
            }
            path++;
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
