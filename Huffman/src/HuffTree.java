import java.io.IOException;
import java.util.*;

class Symbol{
	Symbol Left=null;
	Symbol Right=null;
	char ch;
	int freq;
	String codeword;
}
public class HuffTree {
	LinkedList<Symbol> map;
	Symbol root;
	Symbol HuffTreeRoot;
	HashMap<Character,String> hashmap;
	
	///need to add hash map list
	
	HuffTree(LinkedList<Symbol> input){
		map= input;
		hashmap = new HashMap<Character, String>();
	}

	void BuildupTree() {
		
		Symbol addagain;
		while(map.size()!=1)
		{
			addagain = new Symbol();
			addagain.Left = map.removeFirst();
			addagain.Right = map.removeFirst();
			addagain.freq = addagain.Left.freq + addagain.Right.freq;
			map.add(addagain);
			Collections.sort(map, new Comparator<Symbol>() {
				public int compare(Symbol o1, Symbol o2)
				{
					if(o1.freq < o2.freq) return -1;
					else if(o1.freq==o2.freq) return 0;
					else return 1;
				}
			});
		}
		root=map.removeFirst();			
	}
	
	void FillCodeWord(String Prefix,Symbol s) throws IOException {
		if(s.Left!=null) {
			this.FillCodeWord(Prefix + "0", s.Left);
		}
		if(s.Right!=null) {
			this.FillCodeWord(Prefix + "1", s.Right);
		}
		if(s.Left==null && s.Right ==null) {
			s.codeword = Prefix;
			hashmap.put((Character)s.ch, s.codeword);
		}
	}
}
