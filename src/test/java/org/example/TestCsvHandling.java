package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCsvHandling {

    private static final String[] HEADER = {"Smartwatch Name", "Price (PLN)", "Rating (x/6)"};
    private static final String CSV_REPOSITORY_TEST_FILE = ".\\src\\test\\test_files\\test_file.csv";
    private static final String CSV_REPOSITORY_TEST_FILE_SAVE = ".\\src\\test\\test_files\\test_save_results.csv";
    private static ArrayList<String[]> listOfRows;

    @BeforeAll
    static void setUpData() {
        listOfRows = new ArrayList<>();
        listOfRows.add(HEADER);
        listOfRows.add(new String[]{"Smartwatch Samsung Galaxy Watch Active 2 Aluminium 44mm Rose Gold", "119,00", "5,5"});
        listOfRows.add(new String[]{"Smartwatch Huawei Watch GT 2 Pro czarny", "200,00", "4,0"});
        listOfRows.add(new String[]{"Smartwatch Xiaomi Mi Watch Navy", "300,00", "3,5"});

    }

    @AfterAll
    static void deleteFiles() {
        File file = new File(CSV_REPOSITORY_TEST_FILE_SAVE);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    @Test
    void saveStringRowsToCsvFile() {
        CsvRepository csvRepo = new CsvRepository(CSV_REPOSITORY_TEST_FILE_SAVE);
        csvRepo.saveStringsList(listOfRows, HEADER);
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
        CsvRepository csvRepo = new CsvRepository(CSV_REPOSITORY_TEST_FILE);
        ArrayList<String[]> csvContents = csvRepo.read();
        assertThat(csvContents).hasSameElementsAs(listOfRows);
    }

    @Test
    void correctWorkingDirectory() {
        String currentWorkingDir = System.getProperty("user.dir");
        assertThat(currentWorkingDir).isEqualTo("C:\\Users\\mkuniczu\\IdeaProjects\\smartwatch-finder");
    }
}
