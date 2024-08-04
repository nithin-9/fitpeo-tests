package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Reporter;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;


public class parseJson {

    public static Object getTestInputDataFromJson(String Key,String Page,String TestDataType) throws Throwable {
        Reporter.log("Inside Test Data Json Key Function" + "<br>");

        try {
            Object obj = new JSONParser().parse(new FileReader("src/test/resources/test_data.json"));
            JSONObject jo = (JSONObject) obj;
             Map page = (Map) ((Map)jo.get(Page));//change

            Iterator<Map.Entry> itr1 = page.entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                //System.out.println("Page = "+pair.getKey() + " : " + pair.getValue());
                if (pair.getKey().toString().equalsIgnoreCase(TestDataType)){
                    //System.out.println("Current in If");
                    Map data = (Map) pair.getValue();
                    Iterator<Map.Entry> itr2 = data.entrySet().iterator();
                    while (itr2.hasNext()){
                        //System.out.println("Itr2 has Next");
                        Map.Entry pair2 = itr2.next();
                        //System.out.println("Date = "+pair2.getKey() + " : " + pair2.getValue());
                        if(pair2.getKey().equals(Key)) {
                            //System.out.println("Equals Key");
                            Reporter.log("Key Value Found in JSON Function is => "+pair2.getKey() + " : " + pair2.getValue()+ "<br>");
                            //System.out.println("Page = "+pair2.getKey() + " : " + pair2.getValue());
                            //Reporter.log("Key Value Returning from JSON Parsing is  => ["+pair.getKey() + " : " + pair.getValue()+"]"+ "<br>");
                            return pair2.getValue();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Reporter.log("Unable to find the Json Key Value" + "<br>");
            //System.out.println("Unable to find the json key value" + "<br>");
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Object getLoginDetailsFromEnvJson(String Key) throws Throwable {
        Reporter.log("Inside get Login Details from Env Json Function" + "<br>");
        try {
            Object obj = new JSONParser().parse(new FileReader("src/test/resources/env_data.json"));
            JSONObject jo = (JSONObject) obj;
            Map page = (Map) ((Map)jo.get(Reporter.getCurrentTestResult().getTestContext().getSuite().getParameter("ENVIRONMENT")));

            Iterator<Map.Entry> itr1 = page.entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                Reporter.log("Page = "+pair.getKey() + " : " + pair.getValue() + "<br>");
                if (pair.getKey().toString().equalsIgnoreCase(Key)){
                    Reporter.log("Pair Value is "+pair.getValue() + "<br>");
                    return pair.getValue();
                }
            }
        } catch (Exception e) {
            Reporter.log("Unable to find the Json Key Value" + "<br>");
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
