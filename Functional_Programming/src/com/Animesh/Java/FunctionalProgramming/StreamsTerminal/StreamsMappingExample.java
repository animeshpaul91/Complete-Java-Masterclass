package com.Animesh.Java.FunctionalProgramming.StreamsTerminal;

import com.Animesh.Java.FunctionalProgramming.data.Student;
import com.Animesh.Java.FunctionalProgramming.data.StudentDataBase;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class StreamsMappingExample {
    public static void main(String[] args) {
        Set<String> namesSet = StudentDataBase.getAllStudents()
                .stream()
                .collect(mapping(Student::getName, toSet())); // this avoids the additional map intermediate operation.

        System.out.println("namesSet : " + namesSet);

        List<String> namesList = StudentDataBase.getAllStudents()
                .stream()
                .collect(mapping(Student::getName, toList())); // this avoids the additional map intermediate operation.

        System.out.println("namesList : " + namesList);
    }
}
