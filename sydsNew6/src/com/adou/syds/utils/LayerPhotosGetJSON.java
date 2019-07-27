package com.adou.syds.utils;
import java.util.ArrayList;
import com.adou.syds.domain.Image;

public class LayerPhotosGetJSON {

    public static String getJSON(ArrayList<Image> list,String contextPath)
    {
    	  StringBuilder str = new StringBuilder();//字符串连接  
          str.append("{");  
          str.append("\"status\": 1, ");  
          str.append("\"msg\": \"照片\", ");  
          str.append("\"title\": \"用户相册\", ");  
          str.append("\"id\": 0, ");  
          str.append("\"start\": 0, ");  
          str.append("\"data\": [ ");  
          //处理照片  
                  for (int i = 0; i < list.size(); i++) {  
                      str.append("{");  
                      str.append("\"name\": \"" + list.get(i).getTitle() + "\", ");  
                      str.append("\"pid\": 0, ");  
                      //注意这个中间的是左斜杠，若是写成右斜杠"\\"出不来结果  
                      str.append("\"src\": \""+ contextPath + "" + list.get(i).getImage_url() + "\", ");  
                      str.append("\"thumb\": \"\", ");//缩略图  
                      str.append("\"area\": [1000,1000] " );  
                      str.append("}");  
                      if (i < list.size()-1) {  
                          str.append(",");//不是最后一项的话，加上，号  
                      }else {  
                          break;  
                      }  
                  }  
          str.append("]");  
          str.append("}");  
          return str.toString();
        
    }
}