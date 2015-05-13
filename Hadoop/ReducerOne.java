
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public  class ReducerOne extends MapReduceBase implements
Reducer<Text, Text, Text, Text> {
	
	private static String location;
	public void configure(JobConf job) {
	     location=job.get("location");
	}
	
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
	strbld.append("%");
}

//if(after==0){
output.collect(null,new Text(key.toString()+","+before.toString()+","+after.toString()+","+strbld));
//}
}

}// end of reducer