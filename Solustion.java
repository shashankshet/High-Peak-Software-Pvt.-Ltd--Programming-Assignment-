import java.io.*;
import java.util.*;
class Item {
  String item_name;
  int item_price;

  public Item(String item_name, int item_price) {
    this.item_name = item_name;
    this.item_price = item_price;
  }

  public String toString() { 
      return this.item_name + ": " + this.item_price;
  }
}

public class Solustion {
  public static void main(String[] args) throws Exception {
    FileInputStream fs= new FileInputStream("sample_input.txt");       
    Scanner sc=new Scanner(fs);
    int noe = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.item_price - b.item_price; 
      } 
    });

    int minimun_diff = goodies_items.get(goodies_items.size()-1).item_price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-noe+1;i++) {
      int diff = goodies_items.get(noe+i-1).item_price-goodies_items.get(i).item_price;

      if(diff<=minimun_diff) {
        minimun_diff = diff;
        min_index = i;
      }
    }
    
    

    FileWriter fw = new FileWriter("sample_output.txt");
    fw.write("The goodies selected for distribution are:\n\n\n");
    for(int i=min_index;i<min_index + noe; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + minimun_diff);
	  fw.close();
  }
}