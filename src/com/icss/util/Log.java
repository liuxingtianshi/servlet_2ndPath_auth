package com.icss.util;

import org.apache.log4j.Logger;

public class Log {	
	
	static{
		String xx = Log.class.getName();
		System.out.println(xx);
		logger =  Logger.getLogger(Log.class.getName());    //��̬��õ�ǰ���������λ�ã���������־���ı��ļ�
		System.out.println(Log.class.getResource("/").getPath());    //ͨ��������������Ի�ó������е�λ��
	}
    	
	public static Logger logger;       //һ�θ�ֵ������ʹ��
 

}
