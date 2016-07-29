package cn.sunxyz.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class FileUtil {
	
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	/**
	 * 
	* 创建文件
	* @param filePath
	* @param clazzName
	* @param str    设定文件
	 */
	public static void out(String filePath,String clazzName,String str){
		File file1 = new File(filePath); // 这种路径名格式是在Unix下面的路径格式，在Windows下面的路径格式类似" C:\\test\\  "这样，注意区别  
        if (file1.exists()) {  
        	log.debug("存在文件夹"+filePath);  
        } else {  
            file1.mkdirs(); // 文件夹的创建 创建文件夹/home/a123/a  
        }  
        File file2 = new File(filePath+"\\"+clazzName+".java");  
        if (file2.exists()) {  
        	log.debug("存在文件"+clazzName+".java");  
        } else {  
            try {  
                file2.createNewFile(); // 文件的创建，注意与文件夹创建的区别  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
  
        /** 
         * 最简单的文件读写方法是使用类FileWriter 
         * (它的父类依次是java.io.OutputStreamWriter——>java.io.Writer——>java.lang.Object ); 
         */  
  
        // 下面是向文件file2里面写数据  
        try {  
            FileWriter fileWriter = new FileWriter(file2);  
            fileWriter.write(str);  
            fileWriter.close(); // 关闭数据流  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
	}
	
	/**
	 * 
	* 读入文件
	* @param pathname
	* @return    设定文件
	 */
	public static InputStreamReader in(String pathname){ 
        File filename = new File(pathname); // 要读取以上路径的input。txt文件  
        try {
			InputStreamReader reader = new InputStreamReader(  
			        new FileInputStream(filename));
			return reader;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}

}
