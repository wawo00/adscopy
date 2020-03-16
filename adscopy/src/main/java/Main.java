package main.java;

import java.io.File;

public class Main {


    public static void main(String[] args) {

        if (args != null) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            File workfile = new File(workingDir);
            String oldPath = args[0];
            String newPath = args[1];
            System.out.println("oldPath:" + oldPath);
            System.out.println("newPath:" + newPath);
            CopyHelper.dirCopy(oldPath, newPath);
        } else {
            System.out.println("Fail to run main(), args.length is null");
        }
//        CopyHelper.dirCopy("D:\\TestCopy\\ad_sdk",System.getProperty("user.dir")+File.separator+"ads");
    }


}
