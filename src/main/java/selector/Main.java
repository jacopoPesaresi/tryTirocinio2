package selector;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import utilities.Value;

public class Main{

    public static void main(String[] args) {
        System.out.println("Hello World");
        String ROOT = System.getProperty("user.dir");
        String SEP = System.getProperty("file.separator");
        String PATH = ROOT + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "TOSELECT";
        String PATH1 = ROOT + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "CLASSIFIED";

        List<String> names = List.of("ONE","TWO","THREE");

        String filename = "ciao.txt";

        File f = new File(PATH1 + SEP + "THREE" + SEP + filename);
        File f2 = new File(PATH1 + SEP + "TWO" + SEP + filename);
        System.out.println(f2.renameTo(f));

        Arrays.asList((Value.values())).stream().forEach(s -> System.out.println(s.toString() + "  " + s.ordinal()));




        // boolean success = true;

        // for (String i : names) {
        //     success = success && (new File(PATH1 + SEP + i).mkdirs());
        // }

        

        // if (success)
        // {
        // System.out.println("OK");
        // }else{
        //     System.out.println("NOOK");
        // }





        //System.out.println(PATH);

        //var folder = new File(PATH);

        //ImageManagerImpl im = new ImageManagerImpl(folder);
        //im.getActFile();

        // String[] file = folder.list ();

        // for (String tmp : file){
        //     System.out.println(tmp);
        // }
        
        
    }
}