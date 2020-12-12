package ac20.day2;
import java.util.Scanner;
import java.io.File;
public class PasswordValidater {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input2.txt"));
            int validCount1 = 0;
            int validCount2 = 0;
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                int min = Integer.parseInt(line[0].substring(0,line[0].indexOf("-")));
                int max = Integer.parseInt(line[0].substring(line[0].indexOf("-")+1));
                char letter = line[1].charAt(0);
                int count = 0;
                for(int i=0;i<line[2].length();i++)
                    if(letter==line[2].charAt(i))
                        count++;
                if(count>=min&&count<=max)
                    validCount1++;
                int i=0;
                //Part 2
                min--;
                max--;
                if(min<0){
                    if(max<line[2].length())
                        if(letter==line[2].charAt(max))
                            validCount2++;
                }else if(max>=line[2].length()){
                    if(letter==line[2].charAt(min))
                        validCount2++;
                }else if(line[2].charAt(min)==letter){
                    if(line[2].charAt(max)!=letter)
                        validCount2++;
                }else if(line[2].charAt(max)==letter)
                    validCount2++;
            }
            System.out.println(validCount1);
            System.out.println(validCount2);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
