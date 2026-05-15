package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static Object[][] readCSV(String filePath) {
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { // skip header
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                data.add(new Object[]{values[0], values[1]});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }
}

