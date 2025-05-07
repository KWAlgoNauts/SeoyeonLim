import java.io.*;
import java.util.*;

public class M2146 {
    static int N;
    static int[][] continent;
    static Stack<int[]> dstack;
    static Queue<int[]> bqueue;
    static boolean[][] dvisited;
    static boolean[][] bvisited;
    static int min;
    static int[][] dir ={
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private static void dfs(int x, int y, int areas){
        dvisited[x][y] = true;
        continent[x][y] = areas;

        for(int i = 0; i < dir.length; i++){
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N
                    && !dvisited[nx][ny] && continent[nx][ny] == 1){
                continent[nx][ny] = areas; //labeling
                for(int j = 0; j < dir.length; j++){
                    int nnx = nx + dir[j][0];
                    int nny = ny + dir[j][1];

                    if(nnx >= 0 && nnx < N && nny >= 0 && nny < N
                            && continent[nnx][nny] == 0){
                        if(!dvisited[nx][ny]){
                            bqueue.add(new int[]{nx, ny, 0});
                            dvisited[nx][ny] = true;
                        }
                        break;
                    }
                }

                dfs(nx, ny, areas);
            }
        }
    }

    private static int bfs(){

         //queue 에 저장된 걸 하나씩 빼면서 다른 라벨링 나올때마다 최단 거리를 구해야함
        //이때 하나라도 좌우에 0이 없다면 bfs 패스하도록 조건을 거는게 좋을 것 가틈
        while(!bqueue.isEmpty()){
            int[] cur = bqueue.poll();
            int x = cur[0];
            int y = cur[1];
            int dis = cur[2];

            for(int i = 0; i <dir.length; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                //방문한 적 없는지 확인할 것
                if(nx >= 0 && nx < N && ny >= 0 && ny < N &&!bvisited[nx][ny]){
                    if(continent[nx][ny] != 0 && continent[nx][ny] !=continent[x][y]){
                        return dis;
                    }
                    if (continent[nx][ny] == 0){
                        bqueue.add(new int[]{nx, ny, dis + 1});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //가장 짧은 다리
        N = Integer.parseInt(br.readLine());
        continent = new int[N][N]; // 대륙
        dvisited = new boolean[N][N]; //방문기록_dfs
        dstack = new Stack<>(); //dfs_stack

        for(int i = 0; i<N; i++){
            String[] map = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                continent[i][j] = Integer.parseInt(map[j]);

                if(continent[i][j] == 1){
                    dstack.push(new int[]{i, j});
                }
            }
        }

        //한 영역을 찾으면서 0과 마주한 영역을 queue에 넣기
        //이때 영역 찾은거를 labeling 해야함
        //이때 dfs로 찾는게 나을 듯 함
        bqueue = new LinkedList<>();
        int areas = 1;
        while(!dstack.isEmpty()){
            int[] cur = dstack.pop();
            int x = cur[0];
            int y = cur[1];

            if(!dvisited[x][y]){
                dfs(x, y, areas);
                areas ++;
            }
        }

        int answer = Integer.MAX_VALUE;

        // queue에 넣은 애들끼리 bfs로 라벨링이 다를 때마다  최단 거리를 찾고 min 갱신하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (continent[i][j] != 0) {
                    answer = Math.min(answer, bfs());
                }
            }
        }

        System.out.println(answer);
    }
}
