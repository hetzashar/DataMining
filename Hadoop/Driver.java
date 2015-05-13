

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Driver {
/*	
public static class Subscriber{
		
		int number;
		int count;
		String str; 
		
		public Subscriber(int a,int b,String c){
			number=a;
			count=b;
			str=c;
		}
		
	}



	public static class MapperOne extends MapReduceBase implements
	Mapper<LongWritable, Text, Text, Text> {
		
		private static String startdate;
		private static String enddate;
		public void configure(JobConf job) {
		     startdate = job.get("startdate");
		     enddate = job.get("enddate");
		}
		
private Text word = new Text("");
private Text file = new Text();

@Override
public void map(LongWritable key, Text value,
	OutputCollector<Text, Text> output, Reporter reporter)
	throws IOException {

String line = value.toString();
String[] tokens =line.split(",");
tokens[0]=tokens[0].replaceAll("\""," ");
tokens[1]=tokens[1].replaceAll("\""," ");
tokens[2]=tokens[2].replaceAll(","," ");
tokens[3]=tokens[3].replaceAll(","," ");
tokens[4]=tokens[4].replaceAll(","," ");
tokens[5]=tokens[5].replaceAll(","," ");
tokens[6]=tokens[6].replaceAll(","," ");
tokens[7]=tokens[7].replaceAll(","," ");
tokens[8]=tokens[8].replaceAll(","," ");
tokens[9]=tokens[9].replaceAll(","," ");
tokens[10]=tokens[10].replaceAll(","," ");
tokens[11]=tokens[11].replaceAll(","," ");
tokens[12]=tokens[12].replaceAll(","," ");
tokens[13]=tokens[13].replaceAll(","," ");
tokens[14]=tokens[14].replaceAll(","," ");
tokens[2]=tokens[2].replaceAll(":"," ");
tokens[3]=tokens[3].replaceAll(":"," ");
tokens[4]=tokens[4].replaceAll(":"," ");
tokens[5]=tokens[5].replaceAll(":"," ");
tokens[6]=tokens[6].replaceAll(":"," ");
tokens[7]=tokens[7].replaceAll(":"," ");
tokens[8]=tokens[8].replaceAll(":"," ");
tokens[9]=tokens[9].replaceAll(":"," ");
tokens[10]=tokens[10].replaceAll(":"," ");
tokens[11]=tokens[11].replaceAll(":"," ");
tokens[12]=tokens[12].replaceAll(":"," ");
tokens[13]=tokens[13].replaceAll(":"," ");
tokens[14]=tokens[14].replaceAll(":"," ");

//FileSplit split = (FileSplit) reporter.getInputSplit();

//String name = split.getPath().getName();
//String name="filename";

//file.set(name + "@" + key);


int before=0;
int after=0;

Date d1 = null;
try {
	d1 = (new SimpleDateFormat("dd-MMM-yy")).parse(tokens[0]);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}



Date crime = null;
try {
	crime = (new SimpleDateFormat("dd-MM-yy")).parse(startdate);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


Calendar cal = Calendar.getInstance();
cal.setTime(crime);
cal.add(Calendar.DATE, 7);
Date crimeplus7 = null;
try {
	crimeplus7 = (new SimpleDateFormat("dd-MM-yy")).parse(enddate);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
cal.setTime(crime);
cal.add(Calendar.DATE, -30);
Date crimeminus30 = cal.getTime();

if(d1.compareTo(crime)<=0 && d1.compareTo(crimeminus30)>=0) {
	before=1;
	//word.set(tokenizer.nextToken().toString());
}
if(d1.compareTo(crime)>=0 && d1.compareTo(crimeplus7)<=0){
	after=1;
}
word.set(before+","+after+","+tokens[3].substring(0,9)+";"+tokens[6]+";"+tokens[7]+";"+tokens[10]+";"+tokens[11]+";"+tokens[12]+";"+tokens[13]+";"+tokens[17]);
//System.out.println(new Text(tokens[1]));
//System.out.println(word);
//System.out.println(startdate);
//System.out.println(enddate);
output.collect(new Text(tokens[1].substring(0,9)), word);
}

}// end of mapper

public static class ReducerOne extends MapReduceBase implements
	Reducer<Text, Text, Text, Text> {
@Override
public void reduce(Text key, Iterator<Text> values,
		OutputCollector<Text, Text> output, Reporter reporter)
		throws IOException {

	StringBuilder str=new StringBuilder(key.toString());
	Integer before=0;
	Integer after=0;

	String[] tokens=null;
	StringBuilder strbld= new StringBuilder("");
	while (values.hasNext()) {
		tokens=values.next().toString().split(",");
		before+=Integer.parseInt(tokens[0]);
		after+=Integer.parseInt(tokens[1]);
		strbld.append(tokens[2]);
		strbld.append("|");
	}

	//if(after==0){
	output.collect(null,new Text(key.toString()+","+before.toString()+","+after.toString()+","+strbld));
	//}
	}

}// end of reducer

	public static class Mappertwo extends MapReduceBase implements
	Mapper<LongWritable, Text, IntWritable, Text> {
@Override
public void map(LongWritable key, Text value, OutputCollector<IntWritable,Text> output, Reporter repoter)
		throws IOException {

			String[] str=value.toString().split(",");
	
			if(str[2].equals("0")&& !str[1].equals("0")){
			output.collect(new IntWritable(0), new Text(str[0]+":"+str[1]+":"+str[3]));
			}
}

}

public static class Reducertwo extends MapReduceBase implements 
	Reducer<IntWritable, Text, Text, IntWritable> {
@Override
public void reduce(IntWritable key, Iterator<Text> values,
		OutputCollector<Text,IntWritable> output,Reporter reporter) throws IOException {
	int sum = 0;
	class MyNameComp implements Comparator<Subscriber>{
		 
	    @Override
	    public int compare(Subscriber e1, Subscriber e2) {
	    	 if(e1.count < e2.count){
	             return 1;
	         } else {
	             return -1;
	         }
	    }
	}  
	
	TreeSet<Subscriber> salComp = new TreeSet<Subscriber>(new MyNameComp()); 
	while (values.hasNext()) {
		sum++;
		String[] str=values.next().toString().split(":");
		System.out.println(str[0]+str[1]);
		salComp.add(new Subscriber(Integer.parseInt(str[0].trim()),Integer.parseInt(str[1].trim()),str[2]));
	}
	Iterator it=salComp.iterator();
	while(it.hasNext()){
		Subscriber sub=(Subscriber) it.next();
		output.collect(new Text(sub.number+":"+sub.count+":"+sub.str), null);
	}
	
}
}
*/
	public static void main(String[] args) throws Exception {
		// Path output = new Path("HadoopLab1");
		// File temp = new File(output.toString());
		Path output = new Path("Hadoopprjt1");
		File temp = new File(output.toString());

		if (temp.isDirectory() && temp.exists()) {
			FileUtils.deleteDirectory(temp);
			System.out.println("Deleting Folder");
		}

		Date start = new Date();
		System.out
				.println("Start........................... Program starting time: "
						+ start);

		//Configuration conf = new Configuration();
		System.out.println("Started Job");
		// JobConf job = new JobConf(prjt2.class);
		// JobClient job = new JobClient(conf, "MapReduce 1");

		
		JobConf job = new JobConf(Driver.class);
		// job.setJobName("Driver");

		job.set("startdate",args[3].trim());
		job.set("enddate",args[4].trim());
		job.set("location",args[5].trim());
		job.setJarByClass(Driver.class);
		job.setMapperClass(MapperOne.class);
		job.setReducerClass(ReducerOne.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		JobClient.runJob(job);
		
		
		JobConf job1 = new JobConf(Driver.class);
		// job.setJobName("Driver");

		job1.setJarByClass(Driver.class);
		job1.setMapperClass(Mappertwo.class);
		job1.setReducerClass(ReducerTwo.class);

		job1.setMapOutputKeyClass(IntWritable.class);
		job1.setMapOutputValueClass(Text.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job1, new Path(args[1]));
		FileOutputFormat.setOutputPath(job1, new Path(args[2]));
		JobClient.runJob(job1);
		
		Date finish = new Date();

		System.out.println("Program ending time  " + finish);
		Long diff = (finish.getTime() - start.getTime());
		System.out.println("Driver Programs start time is: " + start
				+ " , and end time is: " + finish);
		System.out.println("Total time of execution: " + diff
				+ " milliseconds.");
		
		
	}

}
