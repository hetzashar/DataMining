

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public  class MapperOne extends MapReduceBase implements
Mapper<LongWritable, Text, Text, Text> {
	
	private static String startdate;
	private static String enddate;
	private static String location;
	public void configure(JobConf job) {
	     startdate = job.get("startdate");
	     enddate = job.get("enddate");
	     location=job.get("location");
	}
	
private Text word = new Text("");
private Text file = new Text();

@Override
public void map(LongWritable key, Text value,
OutputCollector<Text, Text> output, Reporter reporter)
throws IOException {

String line = value.toString();
line=line.replaceAll(",]","]");
line=line.replaceAll("\""," ");
line=line.replaceAll(":"," ");
String[] tokens =line.split(",");
//FileSplit split = (FileSplit) reporter.getInputSplit();

//String name = split.getPath().getName();
//String name="filename";

//file.set(name + "@" + key);


int before=0;
int after=0;

Date d1 = null;
System.out.println(tokens[0]);
try {
d1 = (new SimpleDateFormat("dd-MMM-yy")).parse(tokens[0].trim());
} catch (ParseException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}



Date crime = null;
try {
crime = (new SimpleDateFormat("MM/dd/yyyy")).parse(startdate.trim());
//System.out.println(crime);
} catch (ParseException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}


Calendar cal = Calendar.getInstance();
cal.setTime(crime);
cal.add(Calendar.DATE, 7);
Date crimeplus7 = null;
try {
crimeplus7 = (new SimpleDateFormat("MM/dd/yyyy")).parse(enddate.trim());
//System.out.println(crimeplus7);
} catch (ParseException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
cal.setTime(crime);
cal.add(Calendar.DATE, -120);
Date crimeminus30 = cal.getTime();

if(d1.compareTo(crime)<=0 && d1.compareTo(crimeminus30)>=0) {
before=1;
//word.set(tokenizer.nextToken().toString());
}
if(d1.compareTo(crime)>=0 && d1.compareTo(crimeplus7)<=0){
after=1;
}

word.set(before+","+after+","+tokens[3].substring(0,9)+";"+tokens[6]+";"+tokens[7]+";"+tokens[10]+";"+tokens[11]+";"+tokens[12]+";"+tokens[13]+";"+tokens[17]+";"+tokens[8]+";"+tokens[14]+";"+tokens[15]+";"+tokens[23]+";"+tokens[24]+";"+tokens[0]);
//System.out.println(new Text(tokens[1]));
//System.out.println(word);
//System.out.println(startdate);
//System.out.println(enddate);
output.collect(new Text(tokens[1].substring(0,9)), word);
}

}// end of mapper