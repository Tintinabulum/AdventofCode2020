package ac20.day7;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class HandyHaversacks {
    private static ArrayList<Bag> bags = new ArrayList<Bag>();
    public static void main(String[] args) {
        try{
            Scanner scan=new Scanner(new File("Input7.txt"));
            while(scan.hasNextLine()){
                //Adjective Color bags contain <num Adjective Color bags,>
                Scanner word = new Scanner(scan.nextLine());
                String color = word.next()+" "+word.next();
                Bag temp;
                int index = posBag(color);
                if(index==-1){
                    temp = new Bag(color);
                    bags.add(temp);
                }else temp = bags.get(index);
                word.next();word.next();//bags contain
                while(word.hasNext()){
                    if(!word.hasNextInt()) break;//no other bags.
                    int num=word.nextInt();
                    color = word.next()+" "+word.next();
                    index = posBag(color);
                    if(index==-1){
                        Bag newBag = new Bag(color);
                        temp.addBag(num, newBag);
                        bags.add(newBag);
                    }else temp.addBag(num, bags.get(index));
                    word.next();//bag
                }
            }
            int shinyGoldCount=0;
            for(Bag b:bags){
                if(b.canHoldShinyGold())
                    shinyGoldCount++;
                if(b.color.equals("shiny gold"))
                    System.out.println("Inside: "+b.bagsInside());
            }
            System.out.println("Shiny Gold: "+shinyGoldCount);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private static int posBag(String color){
        for(int i=0;i<bags.size();i++)
            if(color.equals(bags.get(i).color))
                return i;
        return -1;
    }
}
class Bag{
    public String color;
    private ArrayList<Bag> inside;
    private ArrayList<Integer> number;
    public Bag(String c){
        color=c;
        inside = new ArrayList<Bag>();
        number = new ArrayList<Integer>();
    }
    public void addBag(int num,Bag b){
        inside.add(b);
        number.add(num);
        
    }
    public boolean contains(String c){
        for(Bag b:inside)
            if(b.color.equals(c)) return true;
            else if(b.contains(c)) return true;
        return false;
    }
    public boolean canHoldShinyGold(){
        for(Bag b:inside)
            if(b.color.equals("shiny gold"))
                return true;
            else if(b.canHoldShinyGold())
                return true;
        return false;
    }
    public int bagsInside(){
        int bags = 0;
        for(int i=0;i<inside.size();i++)
            bags+=(inside.get(i).bagsInside()*number.get(i)+number.get(i));
        return bags;
    }
}