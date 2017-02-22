package com.gradle.android.utils;



public class LogUtis {
	
   public static void main(String[] args) {
		String msg="";
		for (int i = 0; i < 1778; i++) {
			msg=msg+"*"+i;
		}
		System.out.println("字符串长度："+msg.length());
		prinLnLongMsg("tag",msg);
	}
	
	/**
	  * @desc:分段打印log日志(打印长日志)
	  * @author：Arison on 2017/2/9
	  */
	public static void prinLnLongMsg(String TAG,String responseInfo){
		int lenth=7700;
		if (responseInfo != null){
			if (responseInfo.length() >=lenth) {
				int chunkCount = responseInfo.length() / lenth;     // integer division
				int y=responseInfo.length()%lenth;
				System.out.println(y);
				if(y>0)chunkCount++;
				System.out.println(chunkCount);
				for (int i = 0; i <chunkCount; i++) {
					int max = lenth * (i + 1);
					if (max >= responseInfo.length()) {
						System.out.println("【chunk " + i + " of " + chunkCount + "】:" + responseInfo.substring(lenth * i));
					} else {
						System.out.println("【chunk " + i + " of " + chunkCount + "】:" + responseInfo.substring(lenth * i, max));
					}
				}
			} else {
				System.out.println(responseInfo);
			}
		}
	}

}
