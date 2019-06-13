import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;




public class FileRead {
	
	public StringBuffer GetBuffer(String path) throws IOException {

		List<String> lines;
		StringBuffer sb;

		lines = Files.readAllLines(Paths.get(path));
        sb = new StringBuffer();  // 테스트용 변수
        for(String line: lines) {
            sb.append(line+"\n");
        }
        return sb;
	}
	
	public ArrayList<Byte> GetByteArray(String path) throws IOException {

		ArrayList<Byte> contByte = new ArrayList<>();
		FileInputStream file = null;
		file = new FileInputStream(path);
		
		byte[] b=new byte[1];
		while(file.read(b,0,1)!=-1)
		{
				contByte.add(b[0]);
				b[0]=0;
		}
		file.close();
		return contByte;
	}
}


