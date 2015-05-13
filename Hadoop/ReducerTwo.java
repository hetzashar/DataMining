
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


public class ReducerTwo extends MapReduceBase implements 
Reducer<IntWritable, Text, Text, IntWritable> {
	
	
@Override
public void reduce(IntWritable key, Iterator<Text> values,
	OutputCollector<Text,IntWritable> output,Reporter reporter) throws IOException {
int sum = 0;  

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
