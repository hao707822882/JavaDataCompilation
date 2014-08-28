import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	public static void main(String[] args) {
		ApplicationContext app =
			new ClassPathXmlApplicationContext("proxy/spring/agent/application.xml");
		 
		MySinger singer =(MySinger)app.getBean("singer");
		
		singer.sing();
		
		System.out.println("main"+"...."+ singer.ss("ÖÜ½ÜÂ×"));
	}
		
}