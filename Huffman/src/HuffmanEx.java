import java.io.*;
import java.util.*;

public class HuffmanEx {
	static FileRead fileread;
	static CharStat charstat;
	static LinkedList<Symbol> map;
	static StringBuffer sb;
	static HuffTree hufftree;
	static File f = new File(".");
	static String path;
	
	public static void main(String[] args) throws IOException {
		path=f.getCanonicalPath()+"\\bin//";
		fileread= new FileRead();
		charstat = new CharStat();
		sb = fileread.GetBuffer(path+"test.txt");
		map = charstat.MakeTable(sb);
		hufftree = new HuffTree(map);
		hufftree.BuildupTree();
		hufftree.FillCodeWord("", hufftree.root);
		compress();
		decompress();	
		}
	
	
	public static void compress() throws IOException {
		FileOutputStream file = new FileOutputStream(path + "compressedtest.txt");
		StringBuffer write = new StringBuffer();
		ArrayList<Byte> contByte = new ArrayList<>();

		for(int i=0; i<sb.length();i++) {
			write.append(hufftree.hashmap.get(sb.charAt(i)));
		}
		
		int len;
		
		for(len=write.length();len<write.length()+8;len++)
			if(len%8==0) break;
		
		byte b=0;
		
		for(int i=0;i<len;i++)
		{
			if(i>=write.length())
				b=(byte)(b<<1);
			else {
				b = (byte)((b<<1)|Character.getNumericValue(write.charAt(i)));
			}
			
            if(((i+1)%8==0&&i!=0)||i==len-1)
            {
                contByte.add((byte)b);
                b=0;
            }
           
		}
		
		for(byte k : contByte)
		{
			file.write(k);
		}

		file.close();
	}
	
	public static <K,V> K getKey(HashMap<K, V> m, V value) 
	{ 
		for(K o: m.keySet()) 
		{ 
			if(m.get(o).equals(value)) 
			{ 
				return o; 
			}
		}
		return null; 
	}
	
	public static void decompress() throws IOException{
		StringBuffer towrite = new StringBuffer();
		ArrayList <Byte> contByte = fileread.GetByteArray(path+"compressedtest.txt");
		FileOutputStream file = new FileOutputStream(path+"decompressedtest.txt");
		StringBuilder fin = new StringBuilder();
		for(byte b : contByte)
		{
			towrite.append(String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(' ', '0'));
		}

		int j=0;
		int i=0;
		
		while(true)
		{
			String str = towrite.substring(i,j);
			Character ch = getKey(hufftree.hashmap,str);
			if(ch==null) j++;
			else {
				i=j;
				file.write(ch.charValue());
				fin.append(ch.charValue());
			}
			if(j>=towrite.length()) break;
		}
		System.out.println(fin.toString());
	}
}
