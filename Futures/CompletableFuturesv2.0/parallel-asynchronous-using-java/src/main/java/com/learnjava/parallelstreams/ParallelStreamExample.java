package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamExample {

    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }

    private static List<String> stringTransform(List<String> namesList) {
        return namesList.stream()
                .parallel()
                .map(ParallelStreamExample::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        startTimer();
        List<String> result = stringTransform(namesList);
        timeTaken();
        log("Result: " + result);
    }
}
