package com.whu.job;

import com.whu.hdfs.HdfsDAO;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by hulichao on 2018/1/18
 */
public class StockAnalysis {
    public static class StockAnalysisMapper extends MapReduceBase implements Mapper<Object, Text, Text, DoubleWritable> {
//        private final static IntWritable one = new IntWritable(1);
//        private Text word = new Text();

        public void map(Object key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
            // 打印样本: Before Mapper: 1238865, 000001.SZ,平安银行,2016-05-18,10.3,10.29,10.32,10.17,10.3,53415062,547076639.4,0,0,10.242,0.4525,121581762163.7,N/A,147379364231.7,11804054579,0,14308676139,6.7404,0.9126,1.5326,1.8923
//            System.out.print("Before Mapper: " + key + ", " + value);
//              StringTokenizer itr = new StringTokenizer(value.toString());
            if(value.toString().contains("收盘价(元)")) return;
            String key1 =value.toString().split(",")[0]+ value.toString().split(",")[1];
            Double value1 = Math.abs(Double.valueOf(value.toString().split(",")[7]) - Double.valueOf(value.toString().split(",")[4]));
            System.out.println(key1+":"+value1);
            output.collect(new Text(key1), new DoubleWritable(value1));
        }
    }

    public static class StockAnalysisReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
            Double sum = 0.0;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            result.set(sum);
            output.collect(key, result);
        }
    }
    public static void main(String[] args) throws Exception {
        String input = "hdfs://192.168.1.10:9000//inputFileSZ/深圳A股UTF8/*";
        String output = "hdfs://192.168.1.10:9000/outputFileSZ";

        JobConf conf = new JobConf(WordCount.class);
        conf.setJobName("WordCount");
        conf.addResource("classpath:/hadoop/core-site.xml");
        conf.addResource("classpath:/hadoop/hdfs-site.xml");
        conf.addResource("classpath:/hadoop/mapred-site.xml");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(DoubleWritable.class);

        conf.setMapperClass(StockAnalysisMapper.class);
        conf.setCombinerClass(StockAnalysisReducer.class);
        conf.setReducerClass(StockAnalysisReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(input));
        FileOutputFormat.setOutputPath(conf, new Path(output));

        JobClient.runJob(conf);
        HdfsDAO hdfs = new HdfsDAO(conf);
        hdfs.cat("/outputFile/part-00000");

        System.exit(0);
    }
}
