package pers.jjn.localizationassistant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.JOptionPane;

public class FileModifier {
	
	/*
	 *  LA所有的功能都由此类的实例对象实现
	 *	@Author: JJN(GWYOG)
	 */
	
	private MainFrame parentFrame;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private File file;
	private FileOutputStream fos;
	private OutputStreamWriter osw;
	private BufferedWriter bw;
	
	private String fileInput1;
	private String fileInput2;
	private String fileInput3;
	private String fileOutput;
	private String sOrigin; 
	private String sTo; 
	private String sInterval;
	private String sAdd;
	private String []rowNumberSplit;
	private String []specialIgnoreSymbol;
	private int updateType;
    
	public FileModifier(MainFrame parentFrame,String fileInput,String fileOutput,String sOrigin,String sTo,String []rowNumberSplit,String []specialIgnoreSymbol){
		this.parentFrame = parentFrame;
		this.fileInput1 = fileInput;
		this.fileOutput = fileOutput;
		this.sOrigin = sOrigin;
		this.sTo = sTo;
		this.rowNumberSplit = rowNumberSplit;
		this.specialIgnoreSymbol = specialIgnoreSymbol;
	}
	
	public FileModifier(MainFrame parentFrame,String fileInput,String fileOutput,String sInterval,String sAdd,String []rowNumberSplit){
		this.parentFrame = parentFrame;
		this.fileInput1 = fileInput;
		this.fileOutput = fileOutput;
		this.sInterval = sInterval;
		this.sAdd = sAdd;
		this.rowNumberSplit = rowNumberSplit;
	}

	public FileModifier(MainFrame parentFrame,String fileInput1,String fileInput2,String fileInput3,String fileOutput,int updateType){
		this.parentFrame = parentFrame;
		this.fileInput1 = fileInput1;
		this.fileInput2 = fileInput2;
		this.fileInput3 = fileInput3;
		this.fileOutput = fileOutput;
		this.updateType = updateType;
	}
	
	//输入一个int型参数，判断它是否在用户给定的操作行数范围内：不在的话返回-1，在的话返回该行数，如果用户未置顶操作行数的话，默认对所有行操作，此时返回0
	public int verifyLineNumber(int lineNumber){
		if (rowNumberSplit[0].equals("Blank"))
			return 0;
		else 
			for(int index = 0; index <= rowNumberSplit.length-1 ; index++)
			{	
				try{
					int index2 = rowNumberSplit[index].indexOf("-");
					if(index2 == -1 && lineNumber == Integer.parseInt(rowNumberSplit[index]))
					{	
						return lineNumber;
					}
					else if(index2 != -1 && lineNumber >= Integer.parseInt(rowNumberSplit[index].substring(0,index2)) && lineNumber <= Integer.parseInt(rowNumberSplit[index].substring(index2+1)))
					{
						return lineNumber;
						
					}

				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "行数格式输入错误！", "错误", JOptionPane.ERROR_MESSAGE); 
				}
			}
		return -1;
	}
	
	//判断String类型的字符串能否转化为整数，如果能则返回该整数，不然则返回-1
	public int verifyNumber(String number){
		try{
			return Integer.parseInt(number);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "数字格式输入错误！", "错误", JOptionPane.ERROR_MESSAGE); 
		}
		return -1;
	}
	
	//LA的第一个功能，条件替换
	public void functionReplace(){
		int lineNumber = 1;
		 MessageWindow mw = new MessageWindow(parentFrame,"处理中","",0);
		try{
			String str = "";
			String str1 = "";
		    fis = new FileInputStream(fileInput1);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
		    file = new File(fileOutput);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    fos = new FileOutputStream(file);
		    osw = new OutputStreamWriter(fos,"UTF-8");
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && verifyLineNumber(lineNumber) >= 0 && str.indexOf('=')!=-1)
		    	{
		    		str1 = str.substring(str.indexOf('=')+1);	
		    		String str2,str3="";
		    		int index,index2;
		    		//由于目前只支持使用一对替换忽略符，因此该数组的长度非2即1(即输入为空情形)
		    		if(specialIgnoreSymbol.length == 2){
		    			char specialBegin = specialIgnoreSymbol[0].charAt(0);
		    			char specialEnd = specialIgnoreSymbol[1].charAt(0);
		    			while((index = str1.indexOf(specialBegin)) != -1){
			    			str2 = str1.substring(0, index);
			    			str3 = str3 + str2.replaceAll(sOrigin, sTo);
			    			str1 = index == str1.length() ? "" : str1.substring(index + 1);
			    			if((index2 = str1.indexOf(specialEnd)) != -1)
			    			{
			    				str3 = str3 + specialBegin + str1.substring(0,index2) + specialEnd;
			    				str1 = index2 == str1.length() ? "" : str1.substring(index2 + 1);
			    			}
			    			else{
			    				str3 = str3 + specialBegin + str1.replaceAll(sOrigin, sTo);
			    				str1 = "";
			    			}
			    		}
			    		str3 = str3 + str1.replaceAll(sOrigin,sTo);
		    		}
		    		else
		    			str3 = str1.replaceAll(sOrigin, sTo);		
		    		osw.write(str.substring(0, str.indexOf('=') + 1) + str3);
		    		osw.write("\n");
		    	}
	  	    	else{
	  	    		osw.write(str);
	  	    		osw.write("\n");
	  	    	}
	  	    	lineNumber++;
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return;
		    }finally{	 
		    	try{
		    		if(bw != null)
			    		bw.flush();
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    		osw.close();
		    		fos.close();
		    		mw.close();
		    	}catch(IOException e){ 
					new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
					return;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"错误！","错误，未知错误！",-1);
			return;
		}
	    new MessageWindow(parentFrame,"完成","Done!",1);
	}
	
	//LA的第二个功能，等距添加字符串
	public void functionAdd(){
		int lineNumber = 1;
		int iInterval;
		if((iInterval = verifyNumber(sInterval)) == -1)
			return;
		MessageWindow mw = new MessageWindow(parentFrame,"处理中","",0);
		try{
			String str = "";
			String str1 = "";
		    fis = new FileInputStream(fileInput1);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
		    file = new File(fileOutput);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    fos = new FileOutputStream(file);
		    osw = new OutputStreamWriter(fos,"UTF-8");
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && verifyLineNumber(lineNumber) >= 0 && str.indexOf('=')!=-1)
		    	{
		    		str1 = str.substring(str.indexOf('=')+1);	
		    		String str2="";
		    		while(!str1.equals("")){	
		    			if (str1.length()>iInterval)
		    			{
		    				str2 = str2 + str1.substring(0, iInterval) + sAdd;
		    				str1 = str1.substring(iInterval);
		    			}
		    			else if(str1.length()<=iInterval)
		    			{
		    				str2 = str2 + str1;
		    				str1 = "";
		    			}
		    		}
	  	    		osw.write(str.substring(0, str.indexOf('=')+1) + str2);
	  	    		osw.write("\n");
		    	}
	  	    	else{
	  	    		osw.write(str);
	  	    		osw.write("\n");
	  	    	}
	  	    	lineNumber++;
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return;
		    }finally{	 
		    	try{
		    		if(bw != null)
			    		bw.flush();
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    		osw.close();
		    		fos.close();
		    		mw.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
		    		return;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"错误！","错误，未知错误！",-1);
			return;
		}
		new MessageWindow(parentFrame,"完成","Done!",1);
	}
	
	public void fileDelete(String location){
		File file = new File(location);
		if(file.exists())
			file.delete();
	}
	
	//LA的第三个功能，自动更新本地化文本
	public void functionUpdateLocalization(){
		String tempOutput = fileOutput.substring(0,fileOutput.lastIndexOf('.'))+"TempLocalizationAssistantUpdateLocalization.txt";
		int status1=0,status2=0;
		switch(updateType){
		case 1:status1=functionUpdateLocalization(fileInput2,fileInput3,fileOutput);break;
		case 2:status2=functionUpdateLocalization(fileInput1,fileInput2,fileInput3,fileOutput);break;
		case 3:status1=functionUpdateLocalization(fileInput2,fileInput3,tempOutput);status2=functionUpdateLocalization(fileInput1,fileInput2,tempOutput,fileOutput);fileDelete(tempOutput);break;
		case 4:status2=functionUpdateLocalization(fileInput1,fileInput2,fileInput3,tempOutput);status1=functionUpdateLocalization(fileInput2,tempOutput,fileOutput);fileDelete(tempOutput);break;
		}
		if(status1==0 && status2==0)
			new MessageWindow(parentFrame,"完成","Done!",1);
	}
	
	//方法的重载
	public int functionUpdateLocalization(String fileInput2,String fileInput3,String fileOutput){
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		String str = "";
		String str1 = "";
		String str2 = "";
		MessageWindow mw = new MessageWindow(parentFrame,"处理中","",0);
		try{
		    fis = new FileInputStream(fileInput2);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1)
		    	{
		    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
		    		str2 = str.substring(str.indexOf('=')+1);
		    		hashtable.put(str1,str2);
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
		    		return -1;
		    	}
		    }
		try{
		    fis = new FileInputStream(fileInput3);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
		    file = new File(fileOutput);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    fos = new FileOutputStream(file);
		    osw = new OutputStreamWriter(fos,"UTF-8");
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1)
		    	{
		    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
		    		if(hashtable.containsKey(str1))
		    		{
		    			osw.write(str1 + "=" + hashtable.get(str1));
		    			osw.write("\n");}
		    		else
		    		{
		    			osw.write(str);
		    			osw.write("\n");
		    		}
		    	}
	  	    	else
	  	    	{
	  	    		osw.write(str);
	  	    		osw.write("\n");
	  	    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return -1;
		    }finally{	 
				mw.close();
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    		osw.close();
		    		fos.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
		    		return -1;
		    	}
		    }
		return 0;
	}
	
	//方法的重载
	public int functionUpdateLocalization(String fileInput1,String fileInput2,String fileInput3,String fileOutput){
		Hashtable<String, String> hashtable1 = new Hashtable<String, String>();
		Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
		Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
		String str = "";
		String str1 = "";
		String str2 = "";
		MessageWindow mw = new MessageWindow(parentFrame,"处理中","",0);
		try{
		    fis = new FileInputStream(fileInput1);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1 && str.indexOf('=')!=str.length()-1)
		    	{
		    		str1 = str.substring(0,str.indexOf('='));	
		    		str2 = str.substring(str.indexOf('=')+1);
		    		hashtable1.put(str1,str2.toLowerCase());
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
		    		return -1;
		    	}
		    }
		try{
		    fis = new FileInputStream(fileInput2);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1 && str.indexOf('=')!=str.length()-1)
		    	{
		    		str1 = str.substring(0,str.indexOf('='));	
		    		str2 = str.substring(str.indexOf('=')+1);
		    		hashtable2.put(str1,str2);
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
		    		return -1;
		    	}
		    }
        Set<String> keys1 = hashtable1.keySet();  
        for(String key: keys1)
            if(hashtable2.containsKey(key))
            	hashtable3.put(hashtable1.get(key),hashtable2.get(key));
		try{
		    fis = new FileInputStream(fileInput3);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
		    file = new File(fileOutput);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    fos = new FileOutputStream(file);
		    osw = new OutputStreamWriter(fos,"UTF-8");
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1 && str.indexOf('=')!=str.length()-1)
		    	{
		    		str1 = str.substring(str.indexOf('=')+1);	
		    		if(hashtable3.containsKey(str1.toLowerCase()))
		    		{
		    			osw.write(str.substring(0,str.indexOf('=')+1) + hashtable3.get(str1.toLowerCase()));
		    			osw.write("\n");}
		    		else
		    		{
		    			osw.write(str);
		    			osw.write("\n");
		    		}
		    	}
	  	    	else
	  	    	{
	  	    		osw.write(str);
	  	    		osw.write("\n");
	  	    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"错误！","错误，找不到指定文件！",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"错误！","错误，读取文件失败",-1);
				 return -1;
		    }finally{	 
				mw.close();
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    		osw.close();
		    		fos.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"错误！","错误，关闭数据流失败！",-1);
		    		return -1;
		    	}
		    }
		return 0;
	}
}

