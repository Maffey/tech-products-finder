import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCsvHandling {

    // TODO: test_output.csv should be created before all and then removed after all.

    private static ArrayList<String[]> listOfRows;
    private static final String[] header = {"Smartwatch Name", "Price (PLN)", "Rating (x/6)"};
    private static final String testFilePath = ".\\src\\test\\files\\test_output.csv";

    @BeforeAll
    static void setUpData() {
        listOfRows = new ArrayList<>();
        listOfRows.add(header);
        listOfRows.add(new String[]{"Smartwatch Samsung Galaxy Watch Active 2 Aluminium 44mm Rose Gold", "119,00", "5,5"});
        listOfRows.add(new String[]{"Smartwatch Huawei Watch GT 2 Pro czarny", "200,00", "4,0"});
        listOfRows.add(new String[]{"Smartwatch Xiaomi Mi Watch Navy", "300,00", "3,5"});
    }

    @Test
    void saveResultsToCsvFile() {
        CsvRepository csvRepo = new CsvRepository(testFilePath);
        csvRepo.save(listOfRows, header);
        ArrayList<String[]> csvContents = csvRepo.read();
        assertThat(listOfRows).hasSameElementsAs(csvContents);
    }

    @Test
    void returnEmptyArrayOnError() {
        String fileThatDoesntExist = ".\\non\\existent\\dir\\file.csv";
        CsvRepository csvRepo = new CsvRepository(fileThatDoesntExist);
        ArrayList<String[]> csvContents = csvRepo.read();
        assertThat(csvContents).isEmpty();
    }

    @Test
    void readCorrectContents() {
        CsvRepository csvRepo = new CsvRepository(testFilePath);
        ArrayList<String[]> csvContents = csvRepo.read();
        assertThat(csvContents).hasSameElementsAs(listOfRows);
    }

    @Test
    void correctWorkingDirectory() {
        String currentWorkingDir = System.getProperty("user.dir");
        assertThat(currentWorkingDir).isEqualTo("C:\\Users\\mkuniczu\\IdeaProjects\\smartwatch-finder");
    }
}
