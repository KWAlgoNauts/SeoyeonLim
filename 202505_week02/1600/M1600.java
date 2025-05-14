import java.io.*;
import java.util.*;

public class M1600 {
    static int W;
    static int H;
    static int[][] board;
    static Queue<int[]> queue;
    static boolean[][][] visited;
    static int dis;
    static int k;
    static int[][] dir = {
            {-1, 0},
            {1, 0},
            {0,-1},
            {1, 0}
    };
    static int[][] horse = {
        {-2,-1}, {-2,1},
        {-1,-2}, {-1, 2},
        {1, -2}, {1, 2},
        {2,-1}, {2, 1}
    };
    private static void horse(int row, int col, int dis){
        for(int i = 0; i < 8; i++){
            int dx = horse[i][0];
            int dy = horse[i][1];
            if(row+dx > 0 && row + dx < H && col+dy > 0 && col + dy < W
            &&!visited[row+dx][col+dy][0]){
                queue.add(new int[]{row+dx, col+dy,dis++});
                visited[row+dx][col+dy][1] = true;
            }
        }
    }
    private static void monkey(int row, int col, int dis, boolean using){
        for(int i = 0; i < 4; i++){
            int dx = horse[i][0];
            int dy = horse[i][1];

            if(row+dx > 0 && row + dx < H && col+dy > 0 && col + dy < W){
                //사용 안했다면
                if(!using){
                    //사용을 안했다면 사용 안한 상태로 찾기
                    if(!visited[row+dx][col+dy][0]){
                        queue.add(new int[]{row+dx, col+dy,dis++});
                        visited[row+dx][col+dy][0] = true;
                    }
                }else{
                    if(!visited[row+dx][col+dy][1]){
                        queue.add(new int[]{row+dx, col+dy,dis++});
                        visited[row+dx][col+dy][1] = true;
                    }
                }

            }
        }
    }

    private static void bfs(){
        while(!queue.isEmpty()){
            int[] arr  = queue.poll();
            int row = arr[0];
            int col = arr[1];
            int dis = arr[2];

            if(row==(H -1) && col==(W - 1)){
                System.out.println(dis);
                return;
            }

            //만약 안 썼다면
            if(visited[row][col][0]){
                //한 번은 말
                horse(row, col, dis);
                //한 번은 원숭이
                monkey(row, col, dis,  false);
            }
            //만약 썼다면
            {
                monkey(row, col, dis,  true);
            }
        }

        System.out.println(-1);
        return;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        String[] length = br.readLine().split(" ");
        W = Integer.parseInt(length[0]);
        H = Integer.parseInt(length[1]);

        board = new int[H][W];

        //0 : 평지 1 : 장애물 -> 장애물 있는 쪽 움직이지도 못함
        for(int i = 0; i < H; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < W; j++){
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        //그럼 일단 일반으로 가는 경우 하나랑
        //가지 않는 경우가 있어야할 것 같음
        //전처럼 bfs로 하는
        queue = new LinkedList<>();
        visited = new boolean[H][W][k]; //0,0 에서 0은 아직 k를 안 쓴 것

        queue.add(new int[]{0,0,0});//dis 얘가 거리라고 치자.
        visited[0][0][0] = true;
        bfs();
    }
}
