import java.io.*;
import java.util.*;

public class M7569{
    private static int bfs(Vector<Vector<Vector<Integer>>> tomatos, boolean[][][] visited,
                           Queue<int[]> queue, int [][] dir){
        int day = -1;
        while(!queue.isEmpty()){
            int size = queue.size();
            day++;

            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0]; // h
                int y = cur[1]; // n
                int z = cur[2]; // m

                for(int u = 0; u < 6; u ++){
                    int nx = x + dir[u][0];
                    int ny = y + dir[u][1];
                    int nz = z + dir[u][2];

                    if(nx >= 0 && nx < tomatos.size()
                            && ny >=0 && ny < tomatos.get(0).size()
                            && nz >=0 && nz < tomatos.get(0).get(0).size()
                            && !(visited[nx][ny][nz])
                            && (tomatos.get(nx).get(ny).get(nz) == 0)){
                        queue.add(new int[] {nx, ny, nz});
                        visited[nx][ny][nz] = true;
                        //1로 값을 변경하는 방법
                        tomatos.get(nx).get(ny).set(nz, 1);
                    }
                }
            }
        }

        for(int i = 0; i < tomatos.size(); i ++){
            for(int j = 0; j < tomatos.get(0).size(); j++){
                for(int z = 0; z < tomatos.get(0).get(0).size(); z++){
                    if (tomatos.get(i).get(j).get(z) == 0) {
                        return -1;
                    }
                }
            }
        }

        return day;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] cmd = br.readLine().split(" ");
        int M = Integer.parseInt(cmd[0]);
        int N = Integer.parseInt(cmd[1]);
        int H = Integer.parseInt(cmd[2]);
        boolean isRipe = false;

        Vector<Vector<Vector<Integer>>> tomatos = new Vector<>(); //3차원
        boolean[][][] visited = new boolean[H][N][M];
        Queue<int[]> queue = new LinkedList<>();
        int size = 0;

        //이거 이해하는 거 중요함
        int[][] dir = {
                {0,-1, 0},
                {0, 1, 0},
                {0, 0,-1},
                {0, 0, 1},
                {-1, 0, 0},
                {1, 0, 0}
        };

        for(int i = 0; i < H; i++){
            tomatos.add(new Vector<>());
            for(int j = 0; j < N; j++){
                tomatos.get(i).add(new Vector<>());

                String [] tocmd = br.readLine().split(" ");
                for(int k = 0; k < M; k++){
                    tomatos.get(i).get(j).add(Integer.parseInt(tocmd[k]));
                    if(tocmd[k].equals("1")){
                        queue.add(new int[]{i, j, k});
                    }

                    if(tocmd[k].equals("-1")){
                        size ++;
                    }
                }
            }
        }

        //토마토 다 익었을 때
        if(queue.size() == (H*M*N) - size){
            isRipe = true;
            System.out.println(0);
            return;
        }

        //bfs
        int day = 0;

        if(!isRipe){
            day = bfs(tomatos, visited, queue, dir);
        }


        System.out.println(day);
    }
}