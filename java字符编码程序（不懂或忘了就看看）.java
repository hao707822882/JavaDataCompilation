package io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

public class io {
	public static void main(String[] args) {
		show1();
		System.out.println("------------------------");
		show2();
	}

	
	public static void show2(){
		
		String input = "我爱中国aaa";
		Charset charset = Charset.forName("utf-8");
		
		CharsetEncoder encoder = charset.newEncoder();
		
		encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
		
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer buffer = CharBuffer.allocate(256);
		buffer.put(input);
		buffer.flip();
		try {
			ByteBuffer byteBuffer = encoder.encode(buffer);
			CharBuffer cbuf = decoder.decode(byteBuffer);
			
			char ch[]=cbuf.array();
			for (char c : ch) {
				System.out.print("****"+Integer.toBinaryString(c)+"****");
			}
		//	System.out.println(cbuf); // 输出123
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}

	}
	
	public static void show1(){
		String input = "我爱中国aaa";
		Charset charset = Charset.forName("utf-16");
		
		CharsetEncoder encoder = charset.newEncoder();
		
		encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
		
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer buffer = CharBuffer.allocate(256);
		buffer.put(input);
		buffer.flip();
		try {
			ByteBuffer byteBuffer = encoder.encode(buffer);
			CharBuffer cbuf = decoder.decode(byteBuffer);
			
			char ch[]=cbuf.array();
			for (char c : ch) {
				System.out.print("****"+Integer.toBinaryString(c)+"****");
			}
		//	System.out.println(cbuf); // 输出123
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}

	}
}
