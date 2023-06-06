package GUI.FileInput;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Parser {

    private File file;

    public Parser(File file) {
        this.file = file;
    }

    public String[] parseData() {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> data = reader.readAll();
            return data.get(0);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

}
