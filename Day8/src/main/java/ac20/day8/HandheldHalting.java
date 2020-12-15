package ac20.day8;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class HandheldHalting {
    public static void main(String[] args) {
        ArrayList<Pair> code = new ArrayList<Pair>();
        try{
            Scanner scan = new Scanner(new File("Input8.txt"));
            while(scan.hasNext())
                code.add(new Pair(scan.next(),scan.nextInt()));
        }catch(Exception e){
            System.out.println(e);
        }
        int[] sequence = new int[code.size()];
        int acc = 0;
        int line = 0;
        for(int i=1;sequence[line]==0;i++){
            sequence[line]=i;
            Pair cmd = code.get(line);
            //Part 2
            if(!cmd.command.equals("acc")){
                int[] s = new int[code.size()];
                int a = acc;
                int l = line;
                if(cmd.command.equals("nop")) l+= cmd.amount;
                else l++;
                for(int j=i+1;s[l]==0;j++){
                    boolean quit = l+1==code.size();
                    s[l]=j;
                    Pair c=code.get(l);
                    if(c.command.equals("jmp")) l+=c.amount;
                    else{
                        if(c.command.equals("acc")) a+=c.amount;
                        l++;
                    }
                    if(quit){
                        System.out.println("Changed line "+i);
                        System.out.println("Good Total: "+a);
                        break;
                    }
                    if(l<0||l>=code.size()) break;
                }
            }
            //End Part 2
            if(cmd.command.equals("jmp")) line+=cmd.amount;
            else{
                if(cmd.command.equals("acc")) acc+=cmd.amount;
                line++;
            }
        }
        System.out.println("Loop total: "+acc);
    }
}
class Pair{
    public String command;
    public int amount;
    public Pair(String c,int a){
        command = c;
        amount = a;
    }
    public Pair copy(){
        return new Pair(command,amount);
    }
}