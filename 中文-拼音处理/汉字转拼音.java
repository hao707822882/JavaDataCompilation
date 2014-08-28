package com.itjh.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;


public class SpellHelper {
     //������ת��ΪӢ��
     public static String getEname(String name) {
           HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
           pyFormat.setCaseType(HanyuPinyinCaseType. LOWERCASE);
          pyFormat.setToneType(HanyuPinyinToneType. WITHOUT_TONE);
           pyFormat.setVCharType(HanyuPinyinVCharType. WITH_V);

            return PinyinHelper. toHanyuPinyinString(name, pyFormat, "");
     }

     //�ա����ĵ�һ����ĸ��ҪΪ��д
     public static String getUpEname(String name) {
            char[] strs = name.toCharArray();
           String newname = null;
               
        //���ֵĳ���
     if (strs.length == 2) {   
                newname = toUpCase(getEname ("" + strs[0])) + " "
                           + toUpCase(getEname ("" + strs[1]));
           } else if (strs. length == 3) {
                newname = toUpCase(getEname ("" + strs[0])) + " "
                           + toUpCase(getEname ("" + strs[1] + strs[2]));
           } else if (strs. length == 4) {
                newname = toUpCase(getEname ("" + strs[0] + strs[1])) + " "
                           + toUpCase(getEname ("" + strs[2] + strs[3]));
           } else {
                newname = toUpCase(getEname (name));
           }

            return newname;
     }

     //����ĸ��д
     private static String toUpCase(String str) {
           StringBuffer newstr = new StringBuffer();
           newstr.append((str.substring(0, 1)).toUpperCase()).append(
                     str.substring(1, str.length()));

            return newstr.toString();
     }

     public static void main(String[] args) {
           System. out.println( getEname("���"));

     }

}