import java.io.*;
import java.util.*;

public class M2573 {
    static int R; //행
    static int L; //열
    static int[][] glacier;
    static Queue<int[]> queue;
    static Queue<int[]> isQueue;
    static boolean[][] visited;
    static int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private static void after1Years(){
        int years = 0; //지난 1년
        int size = queue.size();
        isQueue = new LinkedList<>();

        //qeuue 조건을 멈추는게 bfs가 2덩어리가 나왔을 때임 -> boolean 타입으로 해야겠음
        while(!bfs(isQueue)){
            years++;

            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int n = 0;

                for(int u = 0; u < 4; u++){
                    int nx = x + dir[u][0];
                    int ny = y + dir[u][1];

                    //4방향으로 보기 -> 0인 부분 searching
                    if(nx >= 0 && nx < R && ny >=0 && ny <L
                    &&glacier[nx][ny] == 0){
                        n++;
                    }
                }
                int h = glacier[x][y];
                if((h - n) < 0){
                    glacier[x][y] = 0;
                }else{
                    glacier[x][y] = (h - n);
                    isQueue.add(new int[]{x, y});
                }
                queue.add(new int[]{x, y});
            }
        }

        System.out.println(years);
    }

    //빙하가 두 군데로 나누는지를 bfs를 통해 찾아보는 걸로 해야할 듯 함
    private static boolean bfs(Queue<int[]> isQueue){
        visited = new boolean[R][L];
        int areas = 1;

        while(!queue.isEmpty()){
            int[] cur = isQueue.poll();
            int x = cur[0];
            int y = cur[1];
            boolean isSameArea = false;

            //방문하지 않았다면
            if(!visited[x][y]){
                isSameArea = true;
                for(int u =0; u<4;u++){
                    int nx = x + dir[u][0];
                    int ny = y + dir[u][1];

                    //인접해있는 곳에 있는지 확인할 것
                    if(queue.contains(new int[] {nx,ny})){
                        visited[nx][ny] =true;
                    }
                }
            }
            else{
                isSameArea = false;
            }

            if(!isSameArea){
                areas++;
            }
        }

        if(areas >= 2){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] size = br.readLine().split(" ");
        R = Integer.parseInt(size[0]);
        L = Integer.parseInt(size[1]);
        glacier = new int[R][L];
        queue = new LinkedList<>();
        for(int i = 0; i < R; i++){
            String[] Ls = br.readLine().split(" ");
            for(int j = 0; j < L; j++){
                glacier[i][j] = Integer.parseInt(Ls[j]);
                queue.add(new int[]{i, j});
            }
        }

        after1Years(); // 1년씩 다 까먹을 거임
    }
}
