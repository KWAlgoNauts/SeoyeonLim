import java.io.*;
import java.util.*;

public class M10026{
    private static void BFS(Vector<Vector<Character>> colorlist,
                           boolean[][] visited, Queue<int[]> queue,
                           int [][] dir, char color, boolean isBlind) {

        int index = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int u = 0; u < 4; u++) {
                int nx = x + dir[u][0];
                int ny = y + dir[u][1];

                if (nx >= 0 && nx < colorlist.size() && ny >= 0 && ny < colorlist.get(0).size()
                        && !(visited[nx][ny])) {
                    if(!(isBlind)) {
                        if(colorlist.get(nx).get(ny) == color){
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                    else {
                        if((color == 'R' && colorlist.get(nx).get(ny) == 'G') ||
                                (color == 'G' && colorlist.get(nx).get(ny) == 'R')||
                                (color == colorlist.get(nx).get(ny))) {
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                        if(color == colorlist.get(nx).get(ny)){
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Vector<Vector<Character>> colorList = new Vector<>();
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int colorBlined = 0;
        int color = 0;

        int w = 0;

        for(int i = 0; i < N; i++){
            colorList.add(new Vector<>());
            String temp = br.readLine();
            w = temp.length();
            for(int j = 0; j < w; j++){
                colorList.get(i).add(temp.charAt(j));
            }
        }

        boolean[][] visited = new boolean[N][w];
        boolean[][] visitedforBlined = new boolean[N][w];
        Queue<int[]> queue = new LinkedList<>();

        //BFS를 위한 전체 반복문
        //색맹이 아닐 때
        for(int i = 0; i < N; i++){
            for(int j = 0; j < w; j++){
                if(!(visited[i][j])){
                    color++;
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    if(colorList.get(i).get(j) == 'R'){
                        BFS(colorList, visited, queue, dir, 'R', false);
                    }

                    if(colorList.get(i).get(j) == 'G'){
                        BFS(colorList, visited, queue, dir, 'G', false);
                    }

                    if(colorList.get(i).get(j) == 'B'){
                        BFS(colorList, visited, queue, dir, 'B', false);
                    }
                }
            }
        }

        //색맹일 때
        //색맹이 아닐 때
        for(int i = 0; i < N; i++){
            for(int j = 0; j < w; j++){
                if(!(visitedforBlined[i][j])){
                    colorBlined++;
                    queue.add(new int[]{i, j});
                    visitedforBlined[i][j] = true;

                    if(colorList.get(i).get(j) == 'R'){
                        BFS(colorList, visitedforBlined, queue, dir, 'R', true);
                    }

                    if(colorList.get(i).get(j) == 'G'){
                        BFS(colorList, visitedforBlined, queue, dir, 'G', true);
                    }

                    if(colorList.get(i).get(j) == 'B'){
                        BFS(colorList, visitedforBlined, queue, dir, 'B', true);
                    }
                }
            }
        }

        System.out.print(color + " " + colorBlined);
    }
}