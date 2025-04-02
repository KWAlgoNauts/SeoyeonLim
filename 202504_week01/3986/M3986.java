import java.io.*;
import java.util.*;

public class M3986{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result = 0;
        while (N-- > 0){
            String input = br.readLine();
            int length = input.length();
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i<length; i++){
                if(!(stack.isEmpty()) && stack.peek() == input.charAt(i)){
                    stack.pop();
                }else{
                    stack.push(input.charAt(i));
                }
            }
            if(stack.isEmpty()){
                result++;
            }
        }

        System.out.println(result);
    }
}