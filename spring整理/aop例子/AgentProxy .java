

import javax.sound.midi.SysexMessage;

import org.aspectj.lang.ProceedingJoinPoint;

public class AgentProxy {
	
	public void before(){
		System.out.println("before()........");
	}
	
	public void after(){
		System.out.println("after()......");
	}
	
	public void afterThrow(){
		
		System.out.println("after()......");
	}
	
	
	public void around(ProceedingJoinPoint p){
		//�õ�����
		Object[] o= p.getArgs();
		for (int i = 0; i < o.length; i++) {
			Object object = o[i];
			System.out.println(object);
		}
		
		try {
			//����ӣ����ӾͲ�ִ��ԭ����
			p.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("around()....");
	}
	
	public void afterReturn(){
		
		System.out.println("afterReturn()......");
	}
}