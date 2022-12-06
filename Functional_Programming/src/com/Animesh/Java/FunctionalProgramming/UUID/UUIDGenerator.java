package com.Animesh.Java.FunctionalProgramming.UUID;

import java.util.UUID;

public class UUIDGenerator {
    public static void main(String[] args) {
        System.out.println(getEventID());
    }

    private static String getEventID() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        final var result = "EV" + uuid;
        System.out.println(result.length());
        return result;
    }
}
