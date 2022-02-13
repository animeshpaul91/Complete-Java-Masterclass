package com.Animesh.Java.FunctionalProgramming.MethodReferences;

import java.util.function.Function;

public class FunctionMethodReferenceExample {

    /**
     * Class::instance method
     *
     * There are 3 types of method references
     * ClassName::instanceMethodName
     * ClassName::staticMethodName
     * Instance::methodName
     */

    private static final Function<String, String> toUpperCaseLambda = (s) -> s.toUpperCase();
    private static final Function<String, String> toUpperCaseMethodReference = String::toUpperCase;

    public static void main(String[] args) {
        System.out.println(toUpperCaseLambda.apply("java8"));
        System.out.println(toUpperCaseMethodReference.apply("java8"));
    }
}
