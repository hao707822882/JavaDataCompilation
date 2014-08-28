import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * 
 * @see 关于字符转码
 * 
 * ***/
public class s {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		File	file  =  new  File("C:/data.txt"); 
		ObjectOutputStream oos  =  new  ObjectOutputStream(new  FileOutputStream(file)); 
		 c a=new c();
		oos.writeObject(a);
		oos.flush();
		oos.close();
		//new String(null, "GBk");
		System.out.println(Charset.availableCharsets());
		Charset cs=Charset.forName("utf-8");
		CharsetEncoder end=cs.newEncoder();
		end.encode(null);
		CharBuffer charb=CharBuffer.allocate(100);
		charb.put("");
		//URLEncoder.encode(s)也是更具nio的charset做的
		Charset cs2=StandardCharsets.ISO_8859_1;
		//String是用""  char是用''
		Locale local=new Locale.Builder().setLanguage("").setRegion("").setExtension('x', "").build();
		ResourceBundle bundle=ResourceBundle.getBundle("");
		int [][]a1={{10,11},{20,21}};
		NumberFormat numb=NumberFormat.getInstance();
		int i=1-111;
		
	}

	
}
