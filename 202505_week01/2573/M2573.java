import java.io.*;
import java.util.*;

public class M2573 {
    static int R; //행
    static int L; //열
    static int[][] glacier;
    static Stack<int[]> areaStack;
    static Queue<int[]> calQueue;
    static Queue<int[]> zeroQueue;
    static boolean[][] visited;
    static int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private static void after1Years(){
        int years = 0; //지난 1년
        zeroQueue = new LinkedList<>();
        areaStack = new Stack<>();

        //qeuue 조건을 멈추는게 bfs가 2덩어리가 나왔을 때임 -> boolean 타입으로 해야겠음
        while(true){
            if(calQueue.isEmpty()){
                System.out.println(0);
                return;
            }
            int size = calQueue.size();

            for(int i = 0; i < size; i++){
                int[] cur = calQueue.poll();
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
                int h = glacier[x][y]; //현재 위치의 숫자 구하기
                //뺐는데 0이하면 0으로 표시하기
                if((h - n) <= 0){
                    zeroQueue.add(new int[] {x, y});
                }else{//아니면 update 해주기
                    glacier[x][y] = (h - n);
                    areaStack.add(new int[]{x, y}); //영역 확인 용
                    calQueue.add(new int[]{x, y}); //다음 빙하 계산을 위함
                }
            }

            while(!zeroQueue.isEmpty()){
                int[] cur = zeroQueue.poll();
                int x = cur[0];
                int y = cur[1];

                glacier[x][y] = 0;
            }


            years++;
            visited = new boolean[R][L];
            int areas = 0;

            while(!areaStack.isEmpty()){
                int[] cur = areaStack.pop();
                int x = cur[0];
                int y = cur[1];

                if(!visited[x][y]){
                    dfs(x, y);
                    areas ++;
                }
            }

            if(areas >= 2){
                System.out.println(years);
                return;
            }
        }
    }

    //빙하가 두 군데로 나누는지를 bfs를 통해 찾아보는 걸로 해야할 듯 함
    private static void dfs(int x, int y){
        visited[x][y] = true; //처음 좌표 방문 처맇하기

        for(int i = 0; i < dir.length; i++){
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx>=0 && nx<R && ny >=0 && ny <L &&
            glacier[nx][ny] != 0 && !visited[nx][ny]){
                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] size = br.readLine().split(" ");
        R = Integer.parseInt(size[0]);
        L = Integer.parseInt(size[1]);
        glacier = new int[R][L];
        calQueue = new LinkedList<>();
        for(int i = 0; i < R; i++){
            String[] Ls = br.readLine().split(" ");
            for(int j = 0; j < L; j++){
                glacier[i][j] = Integer.parseInt(Ls[j]);
                if(glacier[i][j] != 0){
                    calQueue.add(new int[]{i, j});
                }
            }
        }

        after1Years(); // 1년씩 다 까먹을 거임
    }
}
