
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Mappertwo extends MapReduceBase implements
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