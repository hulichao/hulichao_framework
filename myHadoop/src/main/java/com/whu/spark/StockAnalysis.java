package com.whu.spark;

import com.whu.job.Stock;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.shirdrn.spark.job.maxmind.stock;
import org.shirdrn.spark.job.maxmind.LookupService;
import scala.Tuple2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import static org.eclipse.jetty.io.nio.SelectorManager.LOG;

/**
 * Created by hulichao on 2018/1/19
 */
public class StockAnalysis implements scala.Serializable {
    private static final long serialVersionUID = 8533489548835413763L;
    private static final Pattern SPACE = Pattern.compile(" ");
    private  LookupService lookupService;
    private transient final String geoIPFile;
    public StockAnalysis(String geoIPFile) {
        this.geoIPFile = geoIPFile;
        try {
            // lookupService: get stock code from a IP address
            File file = new File(this.geoIPFile);
            LOG.info("GeoIP file: " + file.getAbsolutePath());
            lookupService = new AdvancedLookupService(file, LookupService.GEOIP_MEMORY_CACHE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("serial")
    public void stat(String[] args) {
        JavaSparkContext ctx = new JavaSparkContext(args[0], "StockAnalysis",
                System.getenv("SPARK_HOME"), JavaSparkContext.jarOfClass(StockAnalysis.class));
        JavaRDD<String> lines = ctx.textFile(args[1], 1);
        // splits and extracts ip address filed
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterable<String> call(String s) {
                // 121.205.198.92 - - [21/Feb/2014:00:00:07 +0800] "GET /archives/417.html HTTP/1.1" 200 11465 "http://shiyanjun.cn/archives/417.html/" "Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20100101 Firefox/11.0"
                // ip address
                return Arrays.asList(SPACE.split(s)[0]);
            }
        });
        // map
        JavaPairRDD<String, Integer> ones = words.map(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        // reduce
        JavaPairRDD<String, Integer> counts = ones.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });
        List<Tuple2<String, Integer>> output = counts.collect();
        // sort statistics result by value
        Collections.sort(output, new Comparator<Tuple2<String, Integer>>() {
            public int compare(Tuple2<String, Integer> t1, Tuple2<String, Integer> t2) {
                if(t1._2 < t2._2) {
                    return 1;
                } else if(t1._2 > t2._2) {
                    return -1;
                }
                return 0;
            }
        });
        writeTo(args, output);
    }
    private void writeTo(String[] args, List<Tuple2<String, Integer>> output) {
        for (Tuple2<?, ?> tuple : output) {
            Stock stock = lookupService.getStock((String) tuple._1);
            LOG.info("[" + stock.getCode() + "] " + tuple._1 + "\t" + tuple._2);
        }
    }
    public static void main(String[] args) {
        // ./bin/run-my-java-example org.shirdrn.spark.job.StockAnalysis   hdfs://192.168.1.10:9000/inputFile.log /home/shirdrn/cloud/programs/spark-0.9.0-incubating-bin-hadoop1/java-examples/GeoIP_DATABASE.dat
        if (args.length < 3) {
            System.err.println("Usage: StockAnalysis <master> <inFile> <GeoIPFile>");
            System.err.println(" Example: org.shirdrn.spark.job.StockAnalysis   hdfs://192.168.1.10:9000/inputFile.log /home/shirdrn/cloud/programs/spark-0.9.0-incubating-bin-hadoop1/java-examples/GeoIP_DATABASE.dat");
            System.exit(1);
        }
        String geoIPFile = args[2];
        StockAnalysis stats = new StockAnalysis(geoIPFile);
        stats.stat(args);
        System.exit(0);
    }
}
