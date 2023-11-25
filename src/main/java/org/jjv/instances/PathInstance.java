package org.jjv.instances;

public class PathInstance {
    private static String path;

    public static void create(String directory){
        path = directory;
    }

    public static String getPath(){
        return path;
    }
}
