import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
	public static void main(String[] args) throws IOException {
		//byte zifu = (byte) 'C';
		String zifuchuan="zhumingming";
		byte zifu[]=zifuchuan.getBytes();
		//System.out.println(Integer.toBinaryString((int) zifu));
		Path proverbLengthsFile = Paths.get(System.getProperty("user.home"))
				.resolve("Beginning Java Stuff").resolve("Proverb Lengths.txt");
		ByteBuffer buf = ByteBuffer.allocate(zifu.length);
		buf.put(zifu);
		BufferedOutputStream lengthsOut = new BufferedOutputStream(
				Files.newOutputStream(proverbLengthsFile));
		lengthsOut.write(buf.array());
		lengthsOut.flush();
		
	}
}
