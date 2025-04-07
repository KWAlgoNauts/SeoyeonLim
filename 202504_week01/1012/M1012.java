import java.awt.*;
import java.io.*;
import java.util.*;

public class M1012{
    public static void main(String[] args) throws IOException{
        //BFS
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int w, h, k, ans;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(T --> 0){
            String[] cmd = br.readLine().split(" ");
            w = Integer.parseInt(cmd[0]);
            h = Integer.parseInt(cmd[1]);
            k = Integer.parseInt(cmd[2]);
            ans = 0;

            boolean[][] napa = new boolean[w][h];
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[w][h];

            for(int i = 0; i < k; i++){
                String[] pos = br.readLine().split(" ");
                int a = Integer.parseInt(pos[0]);
                int b = Integer.parseInt(pos[1]);
                napa[a][b] = true;
            }

            for(int i = 0; i < w; i++){
                for(int j = 0; j < h; j++){
                    if(napa[i][j] && !(visited[i][j])){
                        //새로운 구역을 찾기
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;
                        ans++;

                        while(!queue.isEmpty()){
                            //point 로 된 좌표 꺼내기
                            int[] cur = queue.poll();
                            int x = cur[0];
                            int y = cur[1];

                            //4방향 탐색
                            for(int u = 0; u < 4; u++) {
                                int nx = x + dir[u][0];
                                int ny = y + dir[u][1];

                                //범위에 있는지 확인
                                if(nx >= 0 && nx < w && ny >= 0 && ny < h && napa[nx][ny] && !(visited[nx][ny])){
                                    queue.add(new int[]{nx,ny});
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}