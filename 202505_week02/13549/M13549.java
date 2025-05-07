import java.io.*;
import java.util.*;

public class M13549 {
    static Deque<int[]> queue;
    static int min;
    static boolean[] visited;

    private static int bfs(int su, int sister) {
        visited[su] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pos = cur[0];
            int dis = cur[1];

            if(dis > min){
                continue;
            }

            if (pos == sister) {
                if (dis < min) {
                    min = dis;
                }
                continue;
            }

            //곱하려고 할 때 그게 이득인지를 보는 거임.
            if (Math.abs(sister - pos) > Math.abs(2*pos - sister)
                    && pos *2 <=100000) {
                queue.offerFirst(new int[]{pos * 2, dis});
                visited[pos * 2] = true;
            }

            if ((pos + 1 < 100001) && !visited[pos + 1]) {
                queue.offerLast(new int[]{pos + 1, dis + 1});
                visited[pos + 1] = true;
            }

            if((pos -1 > -1) && !visited[pos - 1]) {
                queue.offerLast(new int[]{pos - 1, dis + 1});
                visited[pos - 1] = true;
            }

        }

        return min;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] pos = br.readLine().split(" ");
        int su = Integer.parseInt(pos[0]);
        int sister = Integer.parseInt(pos[1]);

        queue = new LinkedList<>();
        visited = new boolean[100001];
        queue.offer(new int[] {su, 0});
        min = Integer.MAX_VALUE;

        bfs(su, sister);

        System.out.println(min);
    }
}
