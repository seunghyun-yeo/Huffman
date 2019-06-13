import java.util.*;


public class CharStat {
	
	public LinkedList<Symbol> MakeTable(StringBuffer sb){
		LinkedList<Symbol> map=new LinkedList<Symbol>();
		int stat[]=new int[128];
		for(int i=0;i<sb.length();i++) {
			stat[(int)(sb.charAt(i))]++;
		}
		
		for(int j=0; j<stat.length;j++) {
			//System.out.println( (char)j + ":" + stat[j]);
			if(stat[j]!=0) {
				Symbol addnode = new Symbol();
				addnode.ch = (char) j;
				addnode.freq = stat[j];
				map.add(addnode);
			}
		}
		Collections.sort(map, new Comparator<Symbol>() {
			public int compare(Symbol o1, Symbol o2)
			{
				if(o1.freq < o2.freq) return -1;
				else if(o1.freq==o2.freq) return 0;
				else return 1;
			}
		});
		return map;
	}
}
