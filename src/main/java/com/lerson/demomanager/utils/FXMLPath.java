package com.lerson.demomanager.utils;

public class FXMLPath {

    public static String createFXMLPath(String name) {
        return String.format("/com/lerson/demomanager/%s", name);
    }
}
