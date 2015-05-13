package numbersimilarity;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

	
public class similarity
{
	
	public static void main(String[] args)
	{
	try{
		BufferedReader br=new BufferedReader(new FileReader("Datamining.csv"));
		String line=null;
		BufferedWriter br1=new BufferedWriter(new FileWriter("output1.csv"));
		boolean first=true;
		while((line=br.readLine())!=null)
		{
			if(first){first=false;continue;}
			line=line.replaceAll(",]","]");
			line=line.replaceAll("\""," ");
			line=line.replaceAll(":"," ");
			String[] tokens =line.split(",");
//FileSplit split = (FileSplit) reporter.getInputSplit();

//String name = split.getPath().getName();
//String name="filename";

//file.set(name + "@" + key);
			br1.write(tokens[1].trim()+","+tokens[3].trim()+","+1+"\n");
			
		}
		
		br1.close();
		br.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
}
