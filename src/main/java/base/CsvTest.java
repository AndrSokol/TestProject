package base;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvTest {

    public static void main(String[] args) {
        Map<String, String> testData = null;
        List<Object[]> list = new ArrayList<Object[]>();
        String parhname = "C:\\\\test.csv";
        File file = new File(parhname);
        try{
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext();
            if(keys != null){
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null){
                    testData = new HashMap<String, String>();
                    for (int i = 0; i < keys.length; i++){
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[]{ testData });
                }
            }
            reader.close();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i)[0]);
            }
            Map<String, String> map = new HashMap<String, String>((Map<? extends String, ? extends String>) list.get(0)[0]);
            System.out.println(map.get("id"));
        } catch (FileNotFoundException e){
            throw new RuntimeException("File " + parhname + " was not found.\n" + e.getStackTrace().toString());
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + parhname + " file.\n" + e.getStackTrace().toString());
        }
    }
}
