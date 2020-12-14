package ac20.day5;
import java.util.Scanner;
import java.io.File;
public class PlaneSeat {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input5.txt"));
            boolean[] found = new boolean[128*8];
            int maxID = Integer.MIN_VALUE;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                int max=127,min=0,pos=0,row=-1,col=-1;
                while(pos<7){
                    int mid=(max+min)/2;
                    if(line.charAt(pos++)=='F')
                        max=mid;
                    else min=mid+1;
                    if(pos==7) row=(max+min)/2;
                }
                min=0;
                max=7;
                while(pos<10){
                    int mid = (min+max)/2;
                    if(line.charAt(pos++)=='L')
                        max=mid;
                    else min=mid+1;
                    if(pos==10) col=(max+min)/2;
                }
                found[row*8+col]=true;
                if(row*8+col>maxID) maxID=row*8+col;
            }
            int id=-1;
            for(int i=1;i<found.length;i++)
                if(found[i-1]&&!found[i]&&found[i+1]){
                    id=i;
                    break;
                }
            System.out.println("Max ID: "+maxID);
            System.out.println("Your ID: "+id);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
