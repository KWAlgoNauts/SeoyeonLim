import java.util.*;
import java.io.*;

public class M1780{
    static int[] answer;

    private static boolean equalNum(int N, int[][] paper){
        int num = paper[0][0];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(paper[i][j] != num){
                    return false;
                }
            }
        }

        if(num == -1){
            answer[0]++;
        }else if(num == 0){
            answer[1]++;
        }else {
            answer[2]++;
        }
        return true;
    }

    private static void Paper(int N, int[][] paper){
        int[][] area; //새로운

        //새로운 종이일 때,
        if(equalNum(N, paper)){
            return;
        }

        else{
            //3으로 쪼개기
            int num = N/3;
            for(int i = 0; i < 3; i++){
                int row = num * i;
                for(int j = 0; j < 3; j++){
                    int col = num * j;
                    area = new int[num][num];
                    for(int k = 0; k < num; k++){
                        for(int l = 0; l < num; l++){
                            area[k][l] = paper[row + k][col + l];
                        }
                    }
                    Paper(num, area);
                }
            }
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[N][N];
        answer = new int[3];
        answer[0] = 0;
        answer[0] = 0;
        answer[0] = 0;

        for(int i = 0; i < N; i++){
            String[] element  = br.readLine().split(" ");
            int x = 0;
            for(int j = 0; j < element.length; j++){
                paper[i][j] = Integer.parseInt(element[j]);
            }
        }

        //현재 하나의 수로 같은지 확인하기
        //안 같으면 9개로 자르기
        Paper(N, paper);

        for(int i = 0; i < 3; i++){
            System.out.println(answer[i]);
        }
    }
}
