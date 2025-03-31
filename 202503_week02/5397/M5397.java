import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class M5397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        int L = Integer.parseInt(br.readLine());

        for(int i = 0; i < L; i++){
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> cursor = list.listIterator();//시간초과 안날라면 해야
            String s = br.readLine();
            int j = 0;
            while(j< s.length()){
                char chr = s.charAt(j);
                //<
                if(chr == '<'){
                    if(cursor.hasPrevious()){
                        cursor.previous();
                    }
                }
                //>
                else if (chr == '>'){
                    if(cursor.hasNext()){
                        cursor.next();
                    }
                }
                //-
                else if (chr == '-'){
                    if(cursor.hasPrevious()){
                        cursor.previous();
                        cursor.remove();
                    }
                }
                //나머지
                else{
                    cursor.add(chr);
                }
                j++;
            }

            for(Character item : list ){
                bw.write(item);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}