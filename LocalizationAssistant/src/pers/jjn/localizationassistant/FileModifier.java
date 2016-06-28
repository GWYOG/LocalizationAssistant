package pers.gwyog.localizationassistant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class FileModifier {
	
	/*
	 *  LA���еĹ��ܶ��ɴ����ʵ������ʵ��
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
	private String sRemoveFlag;
	private String []rowNumberSplit;
	private String []specialIgnoreSymbol;
	private String []filter;
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
	
	public FileModifier(MainFrame parentFrame,String fileInput1,String fileOutput,String sRemoveFlag,int flag){
		this.parentFrame = parentFrame;
		this.fileInput1 = fileInput1;
		this.fileOutput = fileOutput;
		this.sRemoveFlag = sRemoveFlag;
		this.updateType = flag;
	}
	
	public FileModifier(MainFrame parentFrame,String fileInput1,String fileOutput,String []filter){
		this.parentFrame = parentFrame;
		this.fileInput1 = fileInput1;
		this.fileOutput = fileOutput;
		this.filter = filter;
	}
	
	//����һ��int�Ͳ������ж����Ƿ����û������Ĳ���������Χ�ڣ����ڵĻ�����-1���ڵĻ����ظ�����������û�δ�ö����������Ļ���Ĭ�϶������в�������ʱ����0
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
					JOptionPane.showMessageDialog(null, "������ʽ�������", "����", JOptionPane.ERROR_MESSAGE); 
				}
			}
		return -1;
	}
	
	//�ж�String���͵��ַ����ܷ�ת��Ϊ������������򷵻ظ���������Ȼ�򷵻�-1
	public int verifyNumber(String number){
		try{
			return Integer.parseInt(number);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "���ָ�ʽ�������", "����", JOptionPane.ERROR_MESSAGE); 
		}
		return -1;
	}
	
	//�ж�һ���ַ������Ƿ��������
	public boolean isContainChineseChar(String str){
		Pattern pattern = Pattern.compile("[\u4e00-\u9fbf]");
		return pattern.matcher(str).find();
	}
	
	//���ַ�������ĸ��Ϊ��д
	public String toUpperInitial(String str){
		if(str == null || str == "")
			return str;
		else
			return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	
	//���ַ�������ĸ��ΪСд
	public String toLowerInitial(String str){
		if(str == null || str == "")
			return str;
		else
			return str.substring(0,1).toLowerCase()+str.substring(1);
	}
	
	//�ж��ַ����Ƿ����ַ�������filter��
	public Boolean isInFilter(String str){
		if(filter==null)
			return false;
		else
			for(int i=0;i<filter.length;i++)
				if(filter[i].toLowerCase().equals(str))
					return true;
		return false;
	}
	
	//LA�ĵ�һ�����ܣ������滻
	public void functionConditionalReplace(){
		int lineNumber = 1;
		 MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
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
		    		//����Ŀǰֻ֧��ʹ��һ���滻���Է�����˸�����ĳ��ȷ�2��1(������Ϊ������)
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
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
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
					new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
					return;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"����","����δ֪����",-1);
			return;
		}
	    new MessageWindow(parentFrame,"���","Done!",1);
	}
	
	//LA�ĵڶ������ܣ��Ⱦ�����ַ���
	public void functionAdd(){
		int lineNumber = 1;
		int iInterval;
		if((iInterval = verifyNumber(sInterval)) == -1)
			return;
		MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
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
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
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
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"����","����δ֪����",-1);
			return;
		}
		new MessageWindow(parentFrame,"���","Done!",1);
	}
	
	public void fileDelete(String location){
		File file = new File(location);
		if(file.exists())
			file.delete();
	}
	
	//LA�ĵ��������ܣ��Զ����±��ػ��ı�
	public void functionUpdateLocalization(){
		String tempOutput = fileOutput.substring(0,fileOutput.lastIndexOf('.'))+"TempLocalizationAssistantUpdateLocalization.txt";
		int status1=0,status2=0;
		int informationModeStatus = updateType % 10;
		int checkModeStatus = (updateType / 10) % 10;
		updateType = updateType / 100;
		switch(updateType){
		case 1:status1=functionUpdateLocalization(fileInput2,fileInput3,fileOutput);break;
		case 2:status2=functionUpdateLocalization(fileInput1,fileInput2,fileInput3,fileOutput,0,checkModeStatus,informationModeStatus);break;
		case 3:status1=functionUpdateLocalization(fileInput2,fileInput3,tempOutput);status2=functionUpdateLocalization(fileInput1,fileInput2,tempOutput,fileOutput,0,checkModeStatus,informationModeStatus);fileDelete(tempOutput);break;
		case 4:status2=functionUpdateLocalization(fileInput1,fileInput2,fileInput3,tempOutput,0,checkModeStatus,informationModeStatus);status1=functionUpdateLocalization(fileInput2,tempOutput,fileOutput);fileDelete(tempOutput);break;
		case 5:status2=functionUpdateLocalization(fileInput1,fileInput2,fileInput3,fileOutput,1,checkModeStatus,informationModeStatus);break;
		}
		if(status1==0 && status2==0)
			new MessageWindow(parentFrame,"���","Done!",1);
	}
	
	//���������ط���1
	public int functionUpdateLocalization(String fileInput2,String fileInput3,String fileOutput){
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		String str = "";
		String str1 = "";
		String str2 = "";
		MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
		try{
		    fis = new FileInputStream(fileInput2);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1)
		    	{   
	  	    		//hashtable��žɰ�zh_CN.lang�ľɰ��-�ɰ�����ֵ
		    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
		    		str2 = str.substring(str.indexOf('=')+1);
		    		hashtable.put(str1,str2);
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
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
	  	    		//�ж��°�en_US.lang���°���Ƿ��hashtable�еľɰ��һ��. ���һ�������þɰ������ֵ�滻�°��Ӣ��ֵ
		    		str1 = str.substring(0,str.indexOf('='));	
		    		if(hashtable.containsKey(str1.toLowerCase()))
		    			str1 = str1 + "=" + hashtable.get(str1.toLowerCase());
		    		else
		    			str1 = str;
	    			osw.write(str1);
	    			osw.write("\n");
		    	}
	  	    	else
	  	    	{
	  	    		osw.write(str);
	  	    		osw.write("\n");
	  	    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
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
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		return 0;
	}
	
	//���������ط���2
	public int functionUpdateLocalization(String fileInput1,String fileInput2,String fileInput3,String fileOutput,int doubleCheckFlag,int checkModeStatus,int informationModeStatus){
		Hashtable<String, String> hashtable1 = new Hashtable<String, String>();
		Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
		Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
		String str = "";
		String str1 = "";	
		String str2 = "";	
		String str3 = "";	//output String
		MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
		try{
		    fis = new FileInputStream(fileInput1);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1)
		    	{	
	  	    		//hashtable1:Ӣ���ı��ɰ�Сд��-�ɰ�СдӢ��ֵ
		    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
		    		str2 = str.substring(str.indexOf('=')+1);
		    		hashtable1.put(str1,str2.toLowerCase());
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
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
	  	    		//hashtable2:�ɰ������ı�Сд��-ֵ
		    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
		    		str2 = str.substring(str.indexOf('=')+1);
		    		hashtable2.put(str1,str2);
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		//hashtable:�ɰ�Ӣ��ֵ-�ɰ�����ֵ��������Ϊ�ɰ�Ӣ�ļ��ڲ������ļ��ڲ�������ִ�Сд��ͬ�ļ�
        Set<String> keys1 = hashtable1.keySet();  
        for(String key: keys1)
            if(hashtable2.containsKey(key))
            	if(checkModeStatus==1 && hashtable3.containsKey(hashtable1.get(key)) && !hashtable2.get(key).equals(hashtable3.get(hashtable1.get(key))))
            		hashtable3.put(hashtable1.get(key),"##Warning:" + hashtable2.get(key));
            	else	
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
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1)
		    	{	
	  	    		if(doubleCheckFlag == 0)
	  	    		{
	  	    			str1 = str.substring(0,str.indexOf('='));		//key
	  	    			str2 = str.substring(str.indexOf('=')+1);		//value
			    		//��δ�����°�ĳ����ɰ�����ͬ��Ӣ��ֵ����str3��δ���У���������ɰ�����ͬ��ֵ����=���滻Ϊ�ɰ�����ֵ���ر�أ�������У��ģʽ���򵱸�Ӣ��ֵ��Ӧ�����ͬ�ɰ�����ֵʱ����Ҫ��str3ǰ�����##Warning:
	  	    			if(hashtable3.containsKey(str2.toLowerCase()))
			    			if(hashtable3.get(str2.toLowerCase()).indexOf("##Warning:") == -1)
			    				str3 = str.substring(0,str.indexOf('=')+1) + hashtable3.get(str2.toLowerCase());
			    			else
			    				str3 = "##Warning:" + str.substring(0,str.indexOf('=')+1) + hashtable3.get(str2.toLowerCase()).replaceAll("##Warning:", "");
			    		else
			    			str3 = str;
	  	    			//����ѡ��ʾ�����Ϣ�󣬻�Ҫ��str3��һ���������������ļ���
	  	    			//str3��str1���Դ�Сд�����ζ�Ÿ���Ӣ��ֵ�ھɰ��в�����.��ʱ��Ҫ��ʾ�����Ϣ
		    			if(informationModeStatus == 1 && str3.toLowerCase().equals(str.toLowerCase()))
	  	    				//֮�����жϸ���Ӣ�ļ��Ƿ��ھɰ��д��ڣ������ڣ���˵�������ı�������
	    					if(hashtable2.containsKey(str1.toLowerCase()) && !hashtable2.get(str1.toLowerCase()).toLowerCase().equals(str2.toLowerCase()))
	    					{
			    				osw.write("##-:" + str1 + "=" + hashtable1.get(str1.toLowerCase()));
					    		osw.write("\n");
					    		osw.write("##+:" + str3);
					    		osw.write("\n");
					    		osw.write("##R:" + str1 + "=" + hashtable2.get(str1.toLowerCase()));
					    		osw.write("\n");
					    	}
			    			else 
			    			{
				    			osw.write("##N:" + str);
				    			osw.write("\n");
			    			}
		    			else
		    			{
		    				osw.write(str3);
			    			osw.write("\n");
		    			}
	  	    		}
	  	    		else
	  	    		{
	  	    			if(informationModeStatus == 0)
	  	    			{
				    		str1 = str.substring(str.indexOf('=')+1);
				    		str2 = str.substring(0,str.indexOf('='));
				    		if(hashtable3.containsKey(str1.toLowerCase()) && hashtable1.containsKey(str2.toLowerCase()) && hashtable1.get(str2.toLowerCase()).toLowerCase().equals(str1.toLowerCase()))
				    			str3 = str2 + "=" + hashtable3.get(str1.toLowerCase());
				    		else
				    			str3 = str;
			    			osw.write(str3);
			    			osw.write("\n");
	  	    			}
	  	    			else
	  	    			{
	  	    				str1 = str.substring(str.indexOf('=')+1);
				    		str2 = str.substring(0,str.indexOf('='));
				    		if(hashtable3.containsKey(str1.toLowerCase()) && hashtable1.containsKey(str2.toLowerCase()) && hashtable1.get(str2.toLowerCase()).toLowerCase().equals(str1.toLowerCase()))
				    		{
				    			str1 = str2 + "=" + hashtable3.get(str1.toLowerCase());
				    			osw.write(str1);
				    			osw.write("\n");
				    		}
				    		else if(hashtable3.containsKey(str1.toLowerCase()) && hashtable1.containsKey(str2.toLowerCase()) && !hashtable1.get(str2.toLowerCase()).toLowerCase().equals(str1.toLowerCase()))
				    		{
				    			osw.write("##-:" + str2 + "=" + hashtable1.get(str2.toLowerCase()));
				    			osw.write("\n");
				    			osw.write("##+:" + str);
				    			osw.write("\n");
				    			osw.write("##R:" + str2 + "=" + hashtable3.get(str1.toLowerCase()));
				    			osw.write("\n");
				    		}
				    		else
				    		{
				    			osw.write("##N:" + str);
				    			osw.write("\n");
				    		}
	  	    			}
	  	    		}
		    	}
	  	    	else
	  	    	{
	  	    		osw.write(str);
	  	    		osw.write("\n");
	  	    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
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
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		return 0;
	}
	
	//LA�ĵ��ĸ����ܣ�������������
	public int functionConditionalRemove(){
		MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
		int updateType1 = updateType / 10;
		int updateType2 = updateType % 10;
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
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1){
		  	    	if(updateType2 == 1)
		  	    	{
		  	    		Pattern pattern = Pattern.compile("##\\S+:");
		  	    		Matcher matcher = pattern.matcher(str);
		  	    		if(matcher.find())
		  	    			str = str.substring(matcher.end());
		  	    	}
		  	    	if(updateType1 == 0)
			  	    	if(str.length()!=0 && str.indexOf(sRemoveFlag)!=-1)
				    		str1 = str.substring(0,str.indexOf(sRemoveFlag));	
			  	    	else
			  	    		str1 = str;
		  	    	else
			  	    	if(str.length()!=0 && str.indexOf(sRemoveFlag)!=-1 && (str.indexOf(sRemoveFlag)+sRemoveFlag.length()<str.length()))
					    	str1 = str.substring(0,str.indexOf(sRemoveFlag)+sRemoveFlag.length());	
			  	    	else
			  	    		str1 = str;

	  	    	}
	  	    	else
	  	    		str1 = str;
  	    		osw.write(str1);
  	    		osw.write("\n");
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
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
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"����","����δ֪����",-1);
			return -1;
		}
		new MessageWindow(parentFrame,"���","Done!",1);
		return 0;
	}
		
	//LA�ĵ�5�����ܣ������ı��滻
	public int functionAutoReplaceEnglishTextWithChineseTranslation(){
		Hashtable<String, String> hashtable1 = new Hashtable<String, String>();
		Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
		Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
		int updateType = this.updateType / 10;
		int checkModeStatus = this.updateType % 10;
		String str = "";
		String str1 = "";	
		String str2 = "";	
		String str3 = "";	//output String
		MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
		//�����һ���ļ�
		try{
		    fis = new FileInputStream(fileInput1);
		    isr = new InputStreamReader(fis,"UTF-8");
	  	    br = new BufferedReader(isr);
	  	    while((str = br.readLine()) != null) {
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1)
		    	{	
	  	    		if(updateType == 0){
		  	    		//hashtable1:Ӣ���ı�Сд��-СдӢ��ֵ
			    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
			    		str2 = str.substring(str.indexOf('=')+1);
			    		hashtable1.put(str1,str2.toLowerCase());
	  	    		}
	  	    		else if(updateType == 1){
		  	    		//hashtable3:Ӣ��ֵ-����ֵ
			    		str1 = str.substring(0,str.indexOf('='));	
			    		str2 = str.substring(str.indexOf('=')+1);
			    		if(hashtable3.containsKey(str1) && !hashtable3.get(str1).equals(str2))
			    			hashtable3.put(str1,"##Warnging:"+str2);
			    		else
			    			hashtable3.put(str1,str2);
	  	    		}
		    	}
		    }
		    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
		    }finally{	 
		    	try{
		    		br.close();   
		    		isr.close();      
		    		fis.close();
		    	}catch(IOException e){ 
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		//����ִ��Ĭ��ģʽʱ������ڶ����ļ�������hashtable3
		if(updateType == 0){
			try{
			    fis = new FileInputStream(fileInput2);
			    isr = new InputStreamReader(fis,"UTF-8");
		  	    br = new BufferedReader(isr);
		  	    while((str = br.readLine()) != null) {
		  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1 && str.indexOf('=')!=str.length()-1)
			    	{
		  	    		//hashtable2:�����ı�Сд��-ֵ(�����Ҫ�ж��������Ƿ��������,���������ֵ�ֵ�Թ�)
			    		str1 = str.substring(0,str.indexOf('=')).toLowerCase();	
			    		str2 = str.substring(str.indexOf('=')+1);
			    		if(isContainChineseChar(str2))
			    			hashtable2.put(str1,str2);
			    	}
			    }
			    }catch(FileNotFoundException e){
					 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
					 return -1;
			    }catch(IOException e){
					 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
					 return -1;
			    }finally{	 
			    	try{
			    		br.close();   
			    		isr.close();      
			    		fis.close();
			    	}catch(IOException e){ 
			    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
			    		return -1;
			    	}
			    }
			//hashtable3:����Ӣ��ֵ-����ֵ��������ΪӢ�ļ��ڲ������ļ��ڲ�������ִ�Сд��ͬ�ļ�
	        Set<String> keys1 = hashtable1.keySet();  
	        for(String key: keys1)
	            if(hashtable2.containsKey(key))
	            	if(checkModeStatus==1 && hashtable3.containsKey(hashtable1.get(key)) && !hashtable2.get(key).equals(hashtable3.get(hashtable1.get(key))))
	            		hashtable3.put(hashtable1.get(key),"##Warning:" + hashtable2.get(key));
	            	else	
	            		hashtable3.put(hashtable1.get(key),hashtable2.get(key));
		}
		//��ȡ�ļ�3�����в��������뵽�ļ�4
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
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1){
	  	    		str1 = str.substring(0,str.indexOf('='));
	  	    		str2 = str.substring(str.indexOf('=')+1);
  	    			int warningFlag = 0; 
  	    			String strTemp;
	  	    		for(String key:hashtable3.keySet()){
	  	    			str3 = toLowerInitial(key);
	  	    			if(str2.indexOf(str3)!=-1){
	  	    				strTemp = hashtable3.get(key);
	  	    				if(strTemp.indexOf("##Warning:")==-1)
	  	    					str2 = str2.replaceAll(str3, strTemp);
	  	    				else{
	  	    					warningFlag = 1;
	  	    					str2 = str2.replaceAll(str3, strTemp.replace("##Warning:", ""));
	  	    				}
	  	    			}
		  	    		str3 = toUpperInitial(key);
	  	    			if(str2.indexOf(str3)!=-1){
	  	    				strTemp = hashtable3.get(key);
	  	    				if(strTemp.indexOf("##Warning:")==-1)
	  	    					str2 = str2.replaceAll(str3, strTemp);
	  	    				else{
	  	    					warningFlag = 1;
	  	    					str2 = str2.replaceAll(str3, strTemp.replace("##Warning:", ""));
	  	    				}
	  	    			}
	  	    		}
	  	    		str = str1 + "=" + str2;
	  	    		str = warningFlag==0?str:"##Warnging:"+str;
	  	    	}
	  	    	osw.write(str);
	  	    	osw.write("\n");
	  	    }
	    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
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
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"����","����δ֪����",-1);
			return -1;
		}
		new MessageWindow(parentFrame,"���","Done!",1);
		return 0;
	}
	
	//LA�ĵ�6�����ܣ�ͳ�Ƹ�Ƶ�ʻ�
	public int functionWordCount(){
		//��ȡ�ļ������в��������뵽�ļ�
		Map<String, Integer> map = new HashMap<String, Integer>();
		String str = "";
		String str1 = "";
		MessageWindow mw = new MessageWindow(parentFrame,"������","",0);
		try{
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
	  	    	if(str.length()!=0 && str.charAt(0) != '#' && str.indexOf('=')!=-1){
	  	    		String[] sArrayTemp;
	  	    		str1 = str.substring(str.indexOf('=')+1);
	  	    		sArrayTemp = str1.split(" ");
	  	    		if(sArrayTemp==null) continue;
	  	    		for(int i=0;i<sArrayTemp.length;i++){
	  	    			String sTemp = sArrayTemp[i].toLowerCase();
	  	    			sTemp = sTemp.replaceAll("\\.","");
	  	    			sTemp = sTemp.replaceAll(",","");
	  	    			sTemp = sTemp.replaceAll("\"","");
	  	    			if(isInFilter(sTemp)) continue;
	  	    			if(map.containsKey(sTemp))
	  	    				map.put(sTemp,map.get(sTemp)+1);
	  	    			else
	  	    				map.put(sTemp,1);
	  	    		}
	  	    	}
	  	    }
			List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {   
			    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
			       return (o2.getValue() - o1.getValue()); 
			    }
			}); 
			for(int i=0;i<list.size();i++){
	  	    	osw.write(list.get(i).toString());
	  	    	osw.write("\n");
			}
	    }catch(FileNotFoundException e){
				 new MessageWindow(parentFrame,"����","�����Ҳ���ָ���ļ���",-1);
				 return -1;
		    }catch(IOException e){
				 new MessageWindow(parentFrame,"����","���󣬶�ȡ�ļ�ʧ��",-1);
				 return -1;
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
		    		new MessageWindow(parentFrame,"����","���󣬹ر�������ʧ�ܣ�",-1);
		    		return -1;
		    	}
		    }
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e){
			e.printStackTrace();
			new MessageWindow(parentFrame,"����","����δ֪����",-1);
			return -1;
		}
		new MessageWindow(parentFrame,"���","Done!",1);
		return 0;
	}
	
}

