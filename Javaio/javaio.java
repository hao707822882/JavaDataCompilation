package d;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class javaio {

	public static void main(String[] args) throws FileNotFoundException {
		@SuppressWarnings("unused")
		int a = 00_00_00;
		// Path path=Paths.get(URI.create("c://in.txt"));
		// FileSystem system=path.getFileSystem();
		File file = new File("C://s.txt");
		DataInputStream stream = new DataInputStream(new FileInputStream(file));
		// System.setOut(new PrintStream(file));
		try {
			// byte b[] = new byte[255];

		//	System.setOut(new PrintStream(new File("c://out.txt")));
		
			byte by[] = new byte[2];
			by="÷Ï".getBytes();
			for (byte b : by) {
				System.out.print("**********"+Integer.toBinaryString(b)+"*******");
			}
			while (true) {
				
				byte byt[] = new byte[2];
				byte bb;
				for (int i = 0; i < 2; i++) {
					bb=byt[i] = stream.readByte();
				}
				System.out.println(new String(byt)+"****");
			//	System.out.println("**********8"+"÷Ï".getBytes()+"*******");
				//System.out.println((char) stream.read());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
