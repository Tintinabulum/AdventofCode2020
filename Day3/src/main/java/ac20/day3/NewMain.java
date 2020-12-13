package ac20.day3;
import java.util.Scanner;
import java.io.File;
public class NewMain {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input3.txt"));
            int[] count = {0,0,0,0,0};//11,31,51,71,12
            int[] pos = {-1,-3,-5,-7,-1};  //11,31,51,71,12
            boolean skip = false;
            while(scan.hasNextLine()){
                pos[0]++;
                pos[1]+=3;
                pos[2]+=5;
                pos[3]+=7;
                String line = scan.nextLine();
                for(int i=0;i<pos.length;i++){
                    if(i==4)
                        if(skip){
                            skip=false;
                            continue;
                        }else{
                            pos[4]++;
                            skip=true;
                        }
                    if(pos[i]>=line.length()) pos[i]-=line.length();
                    if(line.charAt(pos[i])=='#') count[i]++;
                }
                
            }
            long mult=1;
            for(int i:count){
                mult*=i;
                System.out.println(i);
            }
            System.out.println(mult);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
