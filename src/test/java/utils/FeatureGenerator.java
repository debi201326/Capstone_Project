package utils;

import java.io.FileWriter;

public class FeatureGenerator {

    public static void generateFeature(Object[][] data) throws Exception {

        FileWriter fw = new FileWriter("src/test/resources/features/login.feature");
        fw.write("@ui\n");
        fw.write("Feature: Login Test\n\n");

        for (Object[] row : data) {

            String user = row[0].toString();
            String pass = row[1].toString();
            String result = row[2].toString();

            fw.write("Scenario: Login with " + user + "\n");
            fw.write("Given user is on login page\n");
            fw.write("When user enters username \"" + user + "\" and password \"" + pass + "\"\n");
            fw.write("Then login should be \"" + result + "\"\n\n");
        }

        fw.close();
    }
}