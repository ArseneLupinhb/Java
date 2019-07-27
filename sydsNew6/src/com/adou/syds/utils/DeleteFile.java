package com.adou.syds.utils;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class DeleteFile {


	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 * @throws IOException 
	 */  
	public static boolean deleteFile(String filePath) throws IOException {  
	    boolean flag = false;  
	    File file = new File(filePath);
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
}
