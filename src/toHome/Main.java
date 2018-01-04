package toHome;

import java.io.File;

public class Main {
	
	private static String START1 = "C:\\Users\\michaelcb\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu";
	private static String START2 = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";
	
	 public static void main(String[] args) {
		 System.out.println("toHome");		 		 
		 folder(START1,1);
		 folder(START2,2);
		 System.out.println("Execution Terminated");
	 }

	 private static int folder(String path, int start) {
		 File folder = new File(path);
		 //File dest;
		 System.out.println("Openning Directory: " + path);
		 File[] listOfFiles = folder.listFiles();
		 if(listOfFiles == null) {
			return 1; 
		 }		 
		 
		     for (int i = 0; i < listOfFiles.length; i++) {
		       if (listOfFiles[i].isFile()) {
		         //System.out.println("File: " + listOfFiles[i].getAbsolutePath());
		         //Se primeiro start Menu
		         if(start==1) {
		        	 if(!listOfFiles[i].getName().equals(START1+"\\"+listOfFiles[i].getName())) {
		        		 System.out.println(listOfFiles[i].getAbsolutePath()+" MOVE to: "+START1+"\\"+listOfFiles[i].getName());
		        		 listOfFiles[i].renameTo(new File(START1+"\\"+listOfFiles[i].getName()));
		        	 }		        		 
		         }else {
		        	 if(!listOfFiles[i].getName().equals(START2+"\\"+listOfFiles[i].getName())) {
		        		 System.out.println(listOfFiles[i].getAbsolutePath()+" MOVE to: "+START2+"\\"+listOfFiles[i].getName());
		        		 listOfFiles[i].renameTo(new File(START2+"\\"+listOfFiles[i].getName()));	 
		        	 }
		         }
		       } else if (listOfFiles[i].isDirectory()) {
		         System.out.println("Directory: " + listOfFiles[i].getAbsolutePath());
		         if(folder(listOfFiles[i].getAbsolutePath(), start)==1) {
		        	 listOfFiles[i].delete();
		         }
		       }
		     }
		     
		     listOfFiles = folder.listFiles();
		     if(listOfFiles.length==0) {
		    	 folder.delete();
		     }
		     
			return 0;
	 }	 
}
