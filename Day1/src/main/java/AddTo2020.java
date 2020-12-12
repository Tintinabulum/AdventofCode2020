import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class AddTo2020 {
    public static void main(String[] args) {
        try{
            boolean part1Solved=false;
            boolean part2Solved=false;
            Scanner scan = new Scanner(new File("Input.txt"));
            ArrayList<Integer> list = new ArrayList<Integer>();
            while(scan.hasNextInt()){
                int toAdd = scan.nextInt();
                if(!part1Solved)
                    for(int i:list)
                        if(i+toAdd==2020){
                            System.out.println(i+"+"+toAdd+"=2020\n"+i+"*"+toAdd+"="+i*toAdd);
                            part1Solved=true;
                        }
                if(!part2Solved){
                    for(int i=0;i<list.size()&&!part2Solved;i++){
                        int n=list.get(i);
                        for(int j=i+1;j<list.size();j++){
                            int t=list.get(j);
                            if(n+t+toAdd==2020){
                                System.out.println(n+"+"+t+"+"+toAdd+"=2020\n"+n+"*"+t+"*"+toAdd+"="+n*t*toAdd);
                                part2Solved=true;
                                break;
                            }
                        }
                    }
                }
                list.add(toAdd);
            }
        }catch(Exception e){
            System.out.println("Cannot find file.");
        }
    }
}
