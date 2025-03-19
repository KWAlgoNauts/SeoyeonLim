import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();
        int cursor = 0;

        for(int i = 0; i < N; i++){
            list.add(i + 1);
        }

        while(!list.isEmpty()){
            cursor = (cursor + K -1) % list.size();

            sb.append(list.get(cursor));
            if(list.size() == 1){
                break;
            }
            else {
                sb.append(", ");
            }
            list.remove(cursor);
        }
        System.out.println("<" + sb + ">");
    }
}