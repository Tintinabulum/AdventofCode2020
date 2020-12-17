package ac20.day11;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class SeatingSystem {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input11.txt"));
            ArrayList<String> input = new ArrayList<String>();
            while(scan.hasNextLine()) input.add(scan.nextLine());
            char[][] seat = new char[input.size()][input.get(0).length()];
            for(int r=0;r<input.size();r++)
                for(int c=0;c<input.get(r).length();c++)
                    seat[r][c]=input.get(r).charAt(c);
            boolean hasChanged;
            char[][] part2seat=seat;
            do{
                hasChanged=false;
                char[][] nextSeat = new char[seat.length][seat[0].length];
                char[][] newSeat = new char[part2seat.length][part2seat[0].length];
                for(int r=0;r<seat.length;r++){
                    for(int c=0;c<seat[r].length;c++){
                        char s = seat[r][c];
                        int amount = adj(seat,r,c);
                        if(s=='L')
                            if(amount==0){
                                nextSeat[r][c]='#';
                                hasChanged=true;
                            }else nextSeat[r][c]='L';
                        else if(s=='#')
                            if(amount>=4){
                                nextSeat[r][c]='L';
                                hasChanged=true;
                            }else nextSeat[r][c]='#';
                        else nextSeat[r][c]=s;
                        //Part of part 2
                        s=part2seat[r][c];
                        amount=see(part2seat,r,c);
                        if(s=='L')
                            if(amount==0){
                                newSeat[r][c]='#';
                                hasChanged=true;
                            }else newSeat[r][c]='L';
                        else if(s=='#')
                            if(amount>=5){
                                newSeat[r][c]='L';
                                hasChanged=true;
                            }else newSeat[r][c]='#';
                        else newSeat[r][c]=s;
                    }
                }
                seat=nextSeat;
                part2seat=newSeat;
            }while(hasChanged);
            int occCount=0;
            for(char[] sea:seat)
                for(char se:sea)
                    if(se=='#')
                        occCount++;
            System.out.println(occCount);
            occCount=0;
            for(char[] sea:part2seat)
                for(char se:sea)
                    if(se=='#')
                        occCount++;
            System.out.println(occCount);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private static int adj(char[][] seat,int row,int col){
        int count = 0;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++)
                if(i!=0||i!=j)//i!=0,j!=0
                    try{
                        if(seat[row+i][col+j]=='#') count++;
                    }catch(IndexOutOfBoundsException e){
                        
                    }
        return count;
    }
    private static int see(char[][] seat,int row,int col){
        int count=0;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++)
                if(i!=0||i!=j){
                    int mult=1;
                    try{
                        while(seat[row+i*mult][col+j*mult]=='.') mult++;
                        if(seat[row+i*mult][col+j*mult]=='#') count++;
                    }catch(IndexOutOfBoundsException e){
                        
                    }
                }
        return count;
    }
}
