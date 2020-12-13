package ac20.day4;
import java.util.Scanner;
import java.io.File;
public class Passport {
    private static final String[] fie = {"byr","iyr","eyr","hgt","hcl","ecl","pid","cid"};
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input4.txt"));
            int valid = 0;
            boolean[] field = new boolean[8];
            while(scan.hasNextLine()){
               String line = scan.nextLine();
               if(line.length()==0){
                   boolean good = true;
                   for(int i=0;i<field.length;i++){
                       if(i!=field.length-1) good = good && field[i];
                       field[i]=false;
                   }
                   if(good) valid++;
               }else for(String s:line.split(" "))
                   for(int i=0;i<fie.length;i++)
                       if(s.contains(fie[i]))
                           if(validate(s))
                               field[i]=true;
            }
            boolean good = true;
            for(int i=0;i<field.length-1;i++)
                good = good && field[i];          
            if(good) valid++;
            System.out.println(valid);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private static boolean validate(String field){
        try{
            if(field.contains("byr")){
                int year = Integer.parseInt(field.substring(field.indexOf(":")+1));
                if(year<1920) return false;
                if(year>2002) return false;
            }else if(field.contains("iyr")){
                int year = Integer.parseInt(field.substring(field.indexOf(":")+1));
                if(year<2010) return false;
                if(year>2020) return false;
            }else if(field.contains("eyr")){
                int year = Integer.parseInt(field.substring(field.indexOf(":")+1));
                if(year<2020) return false;
                if(year>2030) return false;
            }else if(field.contains("hgt")){
                if(field.substring(field.length()-2).equals("cm")){
                    int len = Integer.parseInt(field.substring(field.indexOf(":")+1,field.length()-2));
                    if(len<150) return false;
                    if(len>193) return false;
                }else if(field.substring(field.length()-2).equals("in")){
                    int len = Integer.parseInt(field.substring(field.indexOf(":")+1,field.length()-2));
                    if(len<59) return false;
                    if(len>76) return false;
                }else return false;
            }else if(field.contains("hcl")){
                field = field.substring(field.indexOf(":")+1);
                if(field.length()!=7) return false;
                if(field.charAt(0)!='#') return false;
                for(int i=1;i<7;i++)
                    if(!isHex(field.charAt(i)))
                        return false;
            }else if(field.contains("ecl")){
                field = field.substring(field.indexOf(":")+1);
                String[] colors = {"amb","blu","brn","gry","grn","hzl","oth"};
                for(String c:colors)
                    if(c.equals(field)) return true;
                return false;
            }else if(field.contains("pid")){
                field = field.substring(field.indexOf(":")+1);
                if(field.length()!=9) return false;
                for(int i=0;i<9;i++)
                    if('0'>field.charAt(i)||'9'<field.charAt(i))
                        return false;
            }else if(field.contains("cid")){
                return true;
            }else return false;
        }catch(Exception e){
            System.out.println(field);
            return false;
        }
        return true;
    }
    private static boolean isHex(char c){
        if('a'<=c&&c<='f') return true;
        if('0'<=c&&c<='9') return true;
        return false;
    }
}