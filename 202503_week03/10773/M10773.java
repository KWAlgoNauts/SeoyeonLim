import java.io.*;
import java.util.*;

public class M10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for(int i = 0; i < K; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0 && !(stack.isEmpty())){
                stack.pop();
            }
            else{
                stack.push(num);
            }
        }

        if(stack.size() == 0){
            result = 0;
        }

        else{
            for(int i = 0; i < stack.size(); i++){
                result += stack.get(i);
            }
        }
        System.out.print(result);
    }
}