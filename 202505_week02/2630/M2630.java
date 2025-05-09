import java.io.*;
import java.util.*;

public class M2630 {
    static int N;
    static int[][] paper;
    static int[] answer;
    private static boolean checking(int x, int y, int size){
        int n = paper[x][y];

        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(paper[i][j] != n){
                    return false;
                }
            }
        }

        if(n == 0){
            answer[0]++;
        }else{
            answer[1]++;
        }
        return true;
    }


    private static void cut(int x, int y, int size){
        //1. checking
        if(checking(x,y,size)){
            return;
        }

        //2. Not cut
        int n = size / 2;

        cut(x,y,n);
        cut(x + n, y, n);
        cut(x, y + n, n);
        cut(x + n, y + n, n);

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        paper = new int[N][N];
        answer = new int[2];
        answer[0] = 0; //white
        answer[1] = 0; //blue

        for(int i = 0 ; i < N; i++){
            String[] info = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                paper[i][j] = Integer.parseInt(info[j]);
            }
        }

        cut(0,0,N);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}
