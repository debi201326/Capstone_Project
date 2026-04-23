package utils;

import java.io.*;

public class JMeterUtils {

    static String JMETER_PATH = "C:\\Users\\debi2\\Downloads\\apache-jmeter-5.6.3\\bin\\jmeter.bat";

    public static void runTest(int threshold) throws Exception {

        String projectPath = System.getProperty("user.dir");

        String testPlan = projectPath + "/src/test/resources/jmeter/login.jmx";
        String resultFile = projectPath + "/target/results.jtl";
        String logFile = projectPath + "/target/jmeter.log";

        // Ensure target folder exists
        new File(projectPath + "/target").mkdirs();

        ProcessBuilder pb = new ProcessBuilder(
                JMETER_PATH,
                "-n",
                "-t", testPlan,
                "-l", resultFile,
                "-j", logFile
        );

        pb.directory(new File(projectPath));
        pb.redirectErrorStream(true);

        System.out.println("Running JMeter...");
        System.out.println("Threshold: " + threshold + " ms");

        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("JMeter Exit Code: " + exitCode);

        if (exitCode != 0) {
            throw new RuntimeException("JMeter execution failed");
        }

        validatePerformance(resultFile, threshold);
    }

    public static void validatePerformance(String resultFile, int threshold) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(resultFile));

        String line;
        long total = 0;
        int count = 0;

        while ((line = br.readLine()) != null) {

            if (line.startsWith("timeStamp")) continue; 

            String[] data = line.split(",");
            long responseTime = Long.parseLong(data[1]); 

            total += responseTime;
            count++;
        }

        br.close();

        long avg = total / count;

        System.out.println("Average Response Time: " + avg + " ms");

        if (avg > threshold) {
            throw new RuntimeException("Performance FAILED! Avg: " + avg + " ms > " + threshold);
        } else {
            System.out.println("Performance PASSED");
        }
    }
}