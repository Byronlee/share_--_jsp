package com.noon.simlegate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
	
	  // get current time of system
        public String getCurrentTime(){
        	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        	  Date currentDate  = new Date(System.currentTimeMillis());
        	  String time = formatter.format(currentDate);
        	  return time;
        }
        public String getAccessById(String id){
        	if(id!=null&&id.equals("1")){
        		return "private";
        	}
        	else if(id.equals("2")){
        		return "public";
        	}
        	else{
        	    return "both";
        	}
        	
        }
        public String formatSize(String size){
        	  DecimalFormat formatter = new DecimalFormat("#0.0");
        	  double temp=0;
        	  String result=null;
        	   try{
        		 temp = Double.parseDouble(size);
        	   }
        	   catch(Exception e){
        		   e.printStackTrace();
        	   }
        	   if(temp<1024*1024){
        		     temp = temp/1024;
        		     result= formatter.format(temp)+"k";
        	   }
        	   else{
        	         temp = temp/1024/1024;
        	         result = formatter.format(temp)+"M";
        	   }
        	   return result;
        	
        }
}
