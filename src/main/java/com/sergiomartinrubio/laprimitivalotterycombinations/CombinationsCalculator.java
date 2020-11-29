package com.sergiomartinrubio.laprimitivalotterycombinations;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CombinationsCalculator {

    private static final String COMBINATIONS_INPUT_FILE = "./historic_results_1985_2020.csv";
    private static final String COMBINATIONS_PAIRS_OUTPUT_FILE = "./combinations_pairs_output_file.csv";
    private static final int MAX_NUMBER = 6;


    public static void main(String[] args) throws IOException, CsvException {
        int[][] countPairsCombinations = generatePairsCountMatrix();

        generatePairsCountCSVFile(countPairsCombinations);
    }

    private static void generatePairsCountCSVFile(int[][] countPairsCombinations) throws IOException {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(COMBINATIONS_PAIRS_OUTPUT_FILE));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)
        ) {
            int[] headerRecord = IntStream.range(1, 50).toArray();
            String[] headerString = Arrays.stream(headerRecord)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
            csvWriter.writeNext(headerString);


            for (int[] rowOfNumbers : countPairsCombinations) {
                String[] rowCountString = Arrays.stream(rowOfNumbers)
                        .mapToObj(String::valueOf)
                        .toArray(String[]::new);
                csvWriter.writeNext(rowCountString);
            }

        }
    }

    private static int[][] generatePairsCountMatrix() throws IOException, CsvException {
        // We store the count of all the pairs here
        int[][] countPairsCombinations = new int[49][49];

        try (CSVReader csvReader = new CSVReader(new FileReader(COMBINATIONS_INPUT_FILE))) {
            List<String[]> rows = csvReader.readAll();

            // Iterate through all the rows.
            // Time complexity = O(n)
            for (int rowPosition = 1; rowPosition < rows.size(); rowPosition++) {
                // increment counter for each pair in the combination row.
                // Time complexity = O(7 * 7)
                for (int i = 1; i <= MAX_NUMBER; i++) {
                    for (int j = i + 1; j <= MAX_NUMBER; j++) {
                        String[] row = rows.get(rowPosition);
                        if (Arrays.stream(row).noneMatch(String::isEmpty)) {
                            int columnIndex = getIntegerIndex(row[i]);
                            int rowIndex = getIntegerIndex(row[j]);
                            countPairsCombinations[columnIndex - 1][rowIndex - 1]++;
                        }
                    }
                }
            }
        }
        return countPairsCombinations;
    }

    private static int getIntegerIndex(String s) {
        return Integer.parseInt(s.replaceFirst("^0+(?!$)", ""));
    }
}
