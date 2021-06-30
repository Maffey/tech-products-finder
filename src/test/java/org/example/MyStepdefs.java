package org.example;

import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs implements En {

    private static final String CSV_REPOSITORY_TEST_FILE_SAVE = ".\\src\\test\\test_files\\test_save_results.csv";
    private static String[] HEADER;
    private CsvRepository csvRepository;
    private ArrayList<String[]> listOfRows;

    public MyStepdefs() {
        Given("CSV Repository", () -> {
            csvRepository = new CsvRepository(CSV_REPOSITORY_TEST_FILE_SAVE);
        });
        // TODO: this needs a serious rework
        And("a list of rows", () -> {
            listOfRows = new ArrayList<>();
            listOfRows.add(new String[]{"Smartwatch Samsung Galaxy Watch Active 2 Aluminium 44mm Rose Gold", "119,00", "5,5"});
            listOfRows.add(new String[]{"Smartwatch Huawei Watch GT 2 Pro czarny", "200,00", "4,0"});
            listOfRows.add(new String[]{"Smartwatch Xiaomi Mi Watch Navy", "300,00", "3,5"});
        });
        Given("a header", () -> {
            HEADER = new String[]{"Smartwatch Name", "Price (PLN)", "Rating (x/6)"};
        });
        When("CSV Repository saves data to a file", () -> {
            csvRepository.saveStringsList(listOfRows);
        });
        Then("the contents inside the file are the same as the list of rows", () -> {
            ArrayList<String[]> csvContents = csvRepository.read();
            assertThat(listOfRows).hasSameElementsAs(csvContents);
        });
    }
}
