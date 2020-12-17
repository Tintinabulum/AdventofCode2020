package ac20.day12;
import java.util.Scanner;
import java.io.File;
public class RainRisk {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("Input12.txt"));
            int dir = 1;//N=0,E=1,S=2,W=3
            Position pos = new Position();
            while(scan.hasNext()){
                String command = scan.next();
                int amount = Integer.parseInt(command.substring(1));
                if(command.charAt(0)=='F')
                    if(dir==0) command="N";
                    else if(dir==1) command="E";
                    else if(dir==2) command="S";
                    else if(dir==3) command="W";
                    else System.out.println("Unknown Direction: "+dir);
                switch(command.charAt(0)){
                    case 'N':
                        pos.y+=amount;
                        break;
                    case 'E':
                        pos.x+=amount;
                        break;
                    case 'S':
                        pos.y-=amount;
                        break;
                    case 'W':
                        pos.x-=amount;
                        break;
                    case 'L':
                        amount*=-1;
                    case 'R':
                        amount=amount/90;
                        dir=dir+amount;
                        while(dir<0) dir+=4;
                        while(dir>=4) dir-=4;
                        break;
                    default:
                        System.out.println("Unknown command: "+command);
                }
            }
            System.out.println(Math.abs(pos.x)+Math.abs(pos.y));
            //Part 2
            scan = new Scanner(new File("Input12.txt"));
            pos.x=0;
            pos.y=0;
            Position way = new Position(10,1);
            while(scan.hasNext()){
                String command = scan.next();
                int amount = Integer.parseInt(command.substring(1));
                switch(command.charAt(0)){
                    case 'S':
                        amount=-amount;
                    case 'N':
                        way.y+=amount;
                        break;
                    case 'W':
                        amount=-amount;
                    case 'E':
                        way.x+=amount;
                        break;
                    case 'F':
                        pos.x+=way.x*amount;
                        pos.y+=way.y*amount;
                        break;
                    case 'L':
                        for(int i=0;i<amount/90;i++){
                            int temp=way.x;
                            way.x=-way.y;
                            way.y=temp;
                        }
                        break;
                    case 'R':
                        for(int i=0;i<amount/90;i++){
                            int temp=way.x;
                            way.x=way.y;
                            way.y=-temp;
                        }
                        break;
                    default:
                        System.out.println("Unknown command: "+command);
                }
            }
            System.out.println(Math.abs(pos.x)+Math.abs(pos.y));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
class Position{
    public int x;
    public int y;
    public Position(){
        this(0,0);
    }
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }
}