import java.io.*;
import java.util.*;

public class M2447 {
    static int N;
    static char theFirst = '*';
    static StringBuilder sb;

    private static void printing(int i, int j, int N){
        if((i/N)%3 == 1 && (j/N)%3 == 1){
            sb.append(' ');
        }else{
            //N/3 == 1  이상이라는건, 아직 찍어질게 남아있다는 의미
            if(N/3 == 0){
                sb.append(theFirst);
            }else{
                printing(i,j, N/3);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // 처음에 세번 반복
        // 1번과 3번 반복
        // 다시 세번 반복
        for(int i = 0; i<N; i++){
            for(int j =0; j<N; j++){
                printing(i,j, N/3);
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}