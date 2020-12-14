package ac20.day6;
import java.util.Scanner;
import java.io.File;
public class Questions {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input6.txt"));
            int total=0,tot=0;
            boolean[] or = new boolean[26];
            boolean[] and= new boolean[26];
            for(int i=0;i<and.length;i++) and[i]=true;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                if(line.length()==0)
                    for(int i=0;i<or.length;i++){
                        if(or[i]){
                            total++;
                            or[i]=false;
                        }
                        if(and[i])
                            tot++;
                        else and[i]=true;
                    }
                else{
                    int[] ans = new int[line.length()];
                    for(int i=0;i<line.length();i++){
                        ans[i]=line.charAt(i)-'a';
                        or[ans[i]]=true;
                    }
                    for(int i=0;i<and.length;i++){
                        boolean found = false;
                        for(int n:ans)
                            if(n==i){
                                found=true;
                                break;
                            }
                        if(!found) and[i]=false;
                    }
                }
            }
            for(int i=0;i<or.length;i++){
                if(or[i]) total++;
                if(and[i]) tot++;
            }
            System.out.println(total);
            System.out.println(tot);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
