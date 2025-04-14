import java.io.*;
import java.util.*;

public class M5427{
    private static int BFS(char[][] matrix, boolean [][] visited, Queue<int[]> fire,
                           Queue<int[]> move, int[][] dir){
        int time = 0;

        //불로 먼저 @ 한 다음 사용자 이동시키기
        while(!fire.isEmpty() || !move.isEmpty()){
            //불 먼저
            int firesize = fire.size();
            for(int i =0; i<firesize; i++){
                int[] fcur = fire.poll();
                int fx = fcur[0];
                int fy = fcur[1];

                for(int u = 0; u < 4; u++){
                    int nfx = fx + dir[u][0];
                    int nfy = fy + dir[u][1];
                    //불 먼저
                    if(nfx>=0 && nfx<matrix.length && nfy>=0 && nfy<matrix[0].length
                            && !visited[nfx][nfy] && matrix[nfx][nfy] == '.'){
                        matrix[nfx][nfy] = '*';
                        fire.add(new int[]{nfx, nfy});
                    }
                }
            }

            int movesize = move.size();
            for(int i=0; i<movesize; i++){
                int[] mcur = move.poll();
                int mx = mcur[0];
                int my = mcur[1];
                for(int u = 0; u < 4; u++){
                    int nmx = mx + dir[u][0];
                    int nmy = my + dir[u][1];

                    //사람 다음
                    if(nmx < 0 || nmx >= matrix.length
                            || nmy < 0 || nmy >= matrix[0].length){
                        time++;
                        return time;
                    }

                    if(nmx >=0 && nmx < matrix.length && nmy >=0 && nmy <matrix[0].length
                            && !visited[nmx][nmy] && matrix[nmx][nmy] == '.'){
                        move.add(new int[]{nmx, nmy});
                        visited[nmx][nmy] = true;
                    }
                }
            }
            time++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        //불이 먼저 붙고
        //상근이의 BFS가 이후에 실행되어야할 것 같음

        int dir [][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(T-- > 0){
            String[] wh = br.readLine().split(" ");
            int w = Integer.parseInt(wh[0]);
            int h = Integer.parseInt(wh[1]);

            char [][] matrix = new char[h][w];
            boolean[][] visited = new boolean[h][w];
            Queue<int[]> fire = new LinkedList<>();
            Queue<int[]> move = new LinkedList<>();

            for(int i = 0; i< h; i++){
                String line = br.readLine();
                for(int j = 0; j < w; j++){
                    char a = line.charAt(j);
                    matrix[i][j] = a;
                    if(matrix[i][j] == '@'){
                        move.add(new int[]{i, j});
                        matrix[i][j]='.';
                    }

                    if(matrix[i][j] == '*'){
                        fire.add(new int[]{i, j});
                    }

                }
            }

            //BFS
            int time = BFS(matrix, visited, fire, move, dir);

            if(time == -1){
                sb.append("IMPOSSIBLE");
            }
            else{
                sb.append(time);
            }

            sb.append("\n");

        }

        System.out.print(sb);
    }
}