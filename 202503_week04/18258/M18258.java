import java.io.*;
import java.util.*;

public class M18258 {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new LinkedList<Integer>();
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int last = 0;

        for(int i = 0; i < N; i++){
            String command = br.readLine();
            if(command.startsWith("push")){
                int push = Integer.parseInt(command.split(" ")[1]);
                queue.add(push);
                last = push;
            } else if(command.startsWith("pop")){
                if(queue.isEmpty()){
                    sb.append(-1).append("\n");
                }else{
                    sb.append(queue.peek()).append("\n");
                    queue.remove();
                }
            } else if(command.startsWith("size")){
                sb.append(queue.size()).append("\n");
            } else if(command.startsWith("empty")){
                if(queue.isEmpty()){
                    sb.append(1).append("\n");
                }else{
                    sb.append(0).append("\n");
                }
            } else if(command.startsWith("front")){
                if(queue.isEmpty()){
                    sb.append(-1).append("\n");
                }else{
                    sb.append(queue.peek()).append("\n");
                }
            }else{
                if(queue.isEmpty()){
                    sb.append(-1).append("\n");
                }else{
                    sb.append(last).append("\n");
                }
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);  // 마지막 개행 문자 제거
        }
        System.out.println(sb);
    }
}