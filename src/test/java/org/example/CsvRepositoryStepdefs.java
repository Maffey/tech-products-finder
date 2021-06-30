package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvRepositoryStepdefs implements En {

    private static final String CSV_REPOSITORY_TEST_FILE_SAVE = ".\\src\\test\\test_files\\test_save_results.csv";
    private static String[] HEADER;
    private CsvRepository csvRepository;
    private ArrayList<String[]> listOfRows;

    public CsvRepositoryStepdefs() {
        Given("CSV Repository", () -> {
            csvRepository = new CsvRepository(CSV_REPOSITORY_TEST_FILE_SAVE);
        });
        Given("a list of rows", (DataTable dataTable) -> {
            List<Map<String, String>> listOfMaps = dataTable.asMaps(String.class, String.class);
            listOfRows = new ArrayList<>();
            for (Map<String, String> map : listOfMaps) {
                listOfRows.add(new String[]{map.get("NAME"), map.get("PRICE"), map.get("RATING")});
            }
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
