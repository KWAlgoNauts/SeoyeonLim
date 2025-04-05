import java.io.*;
import java.util.*;

public class M2504{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String cmd = br.readLine();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;
        boolean error = false;
        for(int i = 0; i < cmd.length(); i++){
            if(cmd.charAt(i) == '('){
                stack.push(cmd.charAt(i));
                temp *= 2;
            } else if (cmd.charAt(i) == '[') {
                stack.push(cmd.charAt(i));
                temp *= 3;
            }
            else{
                // 빼는 것
                if(cmd.charAt(i) == ')'){
                    if(stack.isEmpty() || stack.peek() != '('){
                        error = true;
                        break;
                    }
                    if(cmd.charAt(i - 1) == '('){
                        answer += temp;
                    }
                    stack.pop();
                    temp /= 2;
                }else if(cmd.charAt(i) == ']'){
                    if(stack.isEmpty() || stack.peek() != '['){
                        error = true;
                        break;
                    }
                    if(cmd.charAt(i - 1) == '['){
                        answer += temp;
                    }
                    stack.pop();
                    temp /= 3;
                }
                else{
                    error = true;
                    break;
                }
            }
        }
        if(error || !stack.isEmpty()){
            System.out.println(0);
        }else{
            System.out.println(answer);
        }
    }
}