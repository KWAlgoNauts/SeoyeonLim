import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int max = 1;

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            while(max <= num){
                stack.push(max);
                max ++;
                sb.append("+").append("\n");
            }

            if(stack.peek() == num){
                stack.pop();
                if (i == n - 1) {
                    sb.append("-");// 마지막일 때는 \n 없이 출력
                } else {
                    sb.append("-").append("\n");;
                }
            }
            else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb.toString());

    }
}