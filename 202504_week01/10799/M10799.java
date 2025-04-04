import java.io.*;
import java.rmi.AccessException;
import java.util.*;

public class M10799{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String cmd = br.readLine();
        Stack<Character> par = new Stack<>();

        boolean isClose = false;
        int count = 0;

        for(int i = 0; i<cmd.length(); i++){
            if(cmd.charAt(i) == '('){
                par.push(cmd.charAt(i));
                if(isClose) {
                    isClose = false; //열린 괄호 이므로
                }
            }
            else{
                par.pop();
                //이미 이전에 닫힌 적이 있다면
                if(isClose){
                    count ++;
                }
                else{
                    isClose = true;
                    count +=par.size();
                }
            }
        }

        System.out.println(count);
    }
}