import java.io.*;
import java.util.*;

public class M2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < N; i++){
            queue.add(i+1);
        }

        while(queue.size() != 1){
            int sub;
            queue.remove();
            sub = queue.peek();
            queue.remove();
            queue.add(sub);
        }
        System.out.print(queue.peek());
    }
}