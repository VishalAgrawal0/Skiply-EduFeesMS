package com.edufees.studentservice.constants;

import com.edufees.studentservice.model.FeeDefinition;
import java.util.List;
import java.util.Map;

public class FeeConstants {

    public static final Map<String, List<FeeDefinition>> FEES_BY_GRADE = Map.of(
        "1", List.of(
            new FeeDefinition(FeeType.ADMISSION, 2000),
            new FeeDefinition(FeeType.TUITION, 1000),
            new FeeDefinition(FeeType.EXAM, 500)
        ),
        "2", List.of(
            new FeeDefinition(FeeType.ADMISSION, 2200),
            new FeeDefinition(FeeType.TUITION, 1200),
            new FeeDefinition(FeeType.EXAM, 600)
        ),
        "3", List.of(
            new FeeDefinition(FeeType.ADMISSION, 2500),
            new FeeDefinition(FeeType.TUITION, 1400),
            new FeeDefinition(FeeType.EXAM, 700)
        ),
        "4", List.of(
            new FeeDefinition(FeeType.ADMISSION, 2800),
            new FeeDefinition(FeeType.TUITION, 1600),
            new FeeDefinition(FeeType.EXAM, 800)
        ),
        "5", List.of(
            new FeeDefinition(FeeType.ADMISSION, 3000),
            new FeeDefinition(FeeType.TUITION, 1800),
            new FeeDefinition(FeeType.EXAM, 900)
        ),
        "6", List.of(
            new FeeDefinition(FeeType.ADMISSION, 3200),
            new FeeDefinition(FeeType.TUITION, 2000),
            new FeeDefinition(FeeType.EXAM, 1000)
        ),
        "7", List.of(
            new FeeDefinition(FeeType.ADMISSION, 3500),
            new FeeDefinition(FeeType.TUITION, 2200),
            new FeeDefinition(FeeType.EXAM, 1100)
        ),
        "8", List.of(
            new FeeDefinition(FeeType.ADMISSION, 3800),
            new FeeDefinition(FeeType.TUITION, 2400),
            new FeeDefinition(FeeType.EXAM, 1200)
        ),
        "9", List.of(
            new FeeDefinition(FeeType.ADMISSION, 4000),
            new FeeDefinition(FeeType.TUITION, 2600),
            new FeeDefinition(FeeType.EXAM, 1300)
        ),
        "10", List.of(
            new FeeDefinition(FeeType.ADMISSION, 4500),
            new FeeDefinition(FeeType.TUITION, 2800),
            new FeeDefinition(FeeType.EXAM, 1500)
        )
    );

     public static List<FeeDefinition> getFeesByGrade(String grade) {
        return FEES_BY_GRADE.getOrDefault(grade, List.of());
    }
}
