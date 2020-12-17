package ac20.day9;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
public class EncodingError {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input9.txt"));
            int[] last = new int[25];
            for(int i=0;i<last.length;i++) last[i]=scan.nextInt();
            int pos = 0;
            int weak = -1;
            ArrayList<Integer> all = new ArrayList<Integer>();
            for(int i:last) all.add(i);
            while(scan.hasNextInt()){
                int num = scan.nextInt();
                boolean okay = false;
                for(int i=0;!okay&&i<last.length-1;i++)
                    for(int j=i+1;j<last.length;j++)
                        if(last[i]+last[j]==num){
                            okay=true;
                            break;
                        }
                if(!okay){
                    System.out.println(num+" was not correct.");
                    weak=num;
                }
                last[pos++]=num;
                pos%=last.length;
                all.add(num);
            }
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i=0;i<all.size();i++){
                int sum=0;
                for(int j=i;j<all.size();j++){
                    sum+=all.get(j);
                    if(sum>weak) break;
                    if(sum==weak){
                        for(int k=i;k<=j;k++)
                            if(all.get(k)<min) min=all.get(k);
                            else if(all.get(k)>max) max=all.get(k);
                        break;
                    }
                }
                if(min!=Integer.MAX_VALUE) break;
            }
            System.out.println(min+max);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
