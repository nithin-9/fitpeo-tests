//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ModifyPriority {
//
//    public static void main(String[] args) {
//        String input ="";
//        String output = replacePriority(input,"samplenumber");
//
//        String code = output;
//
//        int counter = 1;
//        while(code.contains("samplenumber")) {
//            code = code.replaceFirst("samplenumber", String.valueOf(counter));
//            counter++;
//        }
//        System.out.println(code);
//
//
//    }
//    public static String replacePriority(String input, String replacement){
//        Pattern pattern = Pattern.compile("priority = \\d++");
//        Matcher matcher = pattern.matcher(input);
//        return matcher.replaceAll("priority = "+ replacement);
//    }
//}
//
//
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
////import static java.util.regex.Pattern.matches;
//
//public class ModifyPriority {
//
//    public static void main(String[] args) {
//        String initialCode = "";
//
//        Pattern p = Pattern.compile("priority = \\d++");
//        Matcher m = p.matcher(initialCode);
//
////        boolean abcd = m.matches();
//
//       boolean abc = Pattern.matches("priority = \\d++", initialCode);
//
//        System.out.println(abc);
//
//            if(!abc) {
//                String modifiedCode = m.replaceAll("priority = samplenumber");
//
//                String finalCode = modifiedCode;
//
//                int counter = 1;
//                while (finalCode.contains("samplenumber")) {
//                    finalCode = finalCode.replaceFirst("samplenumber", String.valueOf(counter));
//                    counter++;
//                }
//                System.out.println(finalCode);
//            }
//
//    }
//}

                                //********************** using long string through file ********************

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyPriority {

    public static void main(String[] args) throws IOException {

//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicTextAnswerSection2.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicNumberResponse.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicNumberResponseLTSection2.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicNumberResponseLESection3.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicNumberResponseNESection4.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicNumberResponseGESection5.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicNumberResponseGTSection6.java";
//        String filePath = "D:\\Round authoring\\cbo-webapp-automation\\src\\test\\java\\pagetests\\rounds\\e2e\\conditionalLogicCheckbox.java";
        String filePath = "C:\\Fipeo\\cbo-webapp-automation\\src\\test\\java\\pagetests\\fitpeoTests.java";

        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) !=null){
                sb.append(line).append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        String s= sb.toString();


        Pattern p = Pattern.compile("priority = \\d++");
        Matcher m = p.matcher(s);

        String modifiedCode = m.replaceAll("priority = samplenumber");


        int counter = 1;
        while(modifiedCode.contains("samplenumber")) {
            modifiedCode = modifiedCode.replaceFirst("samplenumber", String.valueOf(counter));
            counter++;
        }

        try(FileWriter fw = new FileWriter(filePath)) {
            fw.write(modifiedCode);
        } catch (IOException e){
        e.printStackTrace();
        }

        System.out.println(modifiedCode);
    }
}





                                //********************** using long string directly ********************

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ModifyPriority {
//
//    public static void main(String[] args) throws IOException {
//
//        StringBuilder sb = new StringBuilder();
//
//        StringReader sr= new StringReader("");
//
//       try(BufferedReader br= new BufferedReader(sr)){
//        String line;
//        while (( line = br.readLine()) !=null){
//            sb.append(line).append("\n");
//        }
//       }catch (IOException e){
//           e.printStackTrace();
//       }
//
//        String s= sb.toString();
//
//        String initialCode =s;
//
//        Pattern p = Pattern.compile("priority = \\d++");
//        Matcher m = p.matcher(initialCode);
//
//        String modifiedCode = m.replaceAll("priority = samplenumber");
//
//        String finalCode = modifiedCode;
//
//        int counter = 1;
//        while(finalCode.contains("samplenumber")) {
//            finalCode = finalCode.replaceFirst("samplenumber", String.valueOf(counter));
//            counter++;
//        }
//        System.out.println(finalCode);
//    }
//}

