package com.sergiomartinrubio.laprimitivalotterycombinations;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combination {

    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private String firstNumber;

    @CsvBindByPosition(position = 2)
    private String secondNumber;

    @CsvBindByPosition(position = 3)
    private String thirdNumber;

    @CsvBindByPosition(position = 4)
    private String fourthNumber;

    @CsvBindByPosition(position = 5)
    private String fifthNumber;

    @CsvBindByPosition(position = 6)
    private String sixthNumber;
}
