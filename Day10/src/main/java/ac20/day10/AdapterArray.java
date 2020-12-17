package ac20.day10;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class AdapterArray {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input10.txt"));
            ArrayList<Integer> adapt = new ArrayList<Integer>();
            adapt.add(0);
            while(scan.hasNextInt()){
                int newNum = scan.nextInt();
                int bot = 0;
                int top = adapt.size();
                int mid = (bot+top)/2;
                while(bot<top){
                    if(newNum==adapt.get(mid)) break;
                    if(newNum<adapt.get(mid)) top=mid;
                    else bot=mid+1;
                    mid=(top+bot)/2;
                }
                adapt.add(mid,newNum);
            }
            adapt.add(adapt.get(adapt.size()-1)+3);
            int[] diff = new int[4];
            for(int i=0;i<adapt.size()-1;i++)
                diff[adapt.get(i+1)-adapt.get(i)]++;
            System.out.println(diff[1]*diff[3]);
            //Part 2
            long[] amount = new long[adapt.size()];
            amount[0]=1;
            for(int i=0;i<adapt.size();i++){
                for(int j=i+1;j<adapt.size();j++)
                    if(adapt.get(j)-adapt.get(i)<4) amount[j]+=amount[i];
                    else break;
            }
            System.out.println(amount[amount.length-1]);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
