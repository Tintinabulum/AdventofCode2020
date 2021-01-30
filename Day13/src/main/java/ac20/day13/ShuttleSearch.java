package ac20.day13;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
public class ShuttleSearch {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input13.txt"));
            int time = scan.nextInt();
            String[] str = scan.next().split(",");
            ArrayList<Integer> bus = new ArrayList<Integer>(str.length);
            for(String s:str)
                try{
                    bus.add(Integer.parseInt(s));
                }catch(NumberFormatException e){
                    bus.add(null);
                }
            int index = 0;
            int wait = bus.get(0)-time%bus.get(0);
            for(int i=1;i<bus.size();i++){
                if(bus.get(i)==null) continue;
                int temp = bus.get(i)-time%bus.get(i);
                if(temp<wait){
                    wait=temp;
                    index=i;
                }
            }
            System.out.println(bus.get(index)*wait);
            //Part 2
            ArrayList<Pair> b = new ArrayList<Pair>(bus.size());
            long max=1;
            for(int i=0;i<bus.size();i++)
                if(bus.get(i)!=null){
                    b.add(new Pair(bus.get(i),i));
                    max*=bus.get(i);
                }
            long t=-1;
            long start = 100000000000000l;
            while(start%bus.get(0)!=0) start++;
            for(long i=start;t==-1&&i<max;i+=bus.get(0)){
                t=i;
                for(Pair p:b)
                    if((i+p.index)%p.num!=0){
                        t=-1;
                        break;
                    }
            }
            System.out.println(t);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
class Pair{
    public int num;
    public int index;
    public Pair(int n,int i){
        num=n;
        index=i;
    }
}