package main.java;

import java.io.*;

import static main.java.AndroidxMapping.*;

/**
 * @ProjectName: AdsMainSdk_Androidx
 * @Package: com.copy
 * @ClassName: CopyHelper
 * @Description: CopyHelper
 * @Author: Roy
 * @CreateDate: 2020/3/13 15:19
 */

public class CopyHelper {
    static boolean firstWhile = true;// 添加换行符

    public static void dirCopy(String srcPath, String destPath) {
        File src = new File(srcPath);
        if (!new File(destPath).exists()) {
            new File(destPath).mkdirs();
        }

        if (new File(destPath).exists()) {
            deleteDir(destPath);
            new File(destPath).mkdirs();
        }
        for (File s : src.listFiles()) {
            if (s.isFile()) {
                if (s.getPath().endsWith(".java")) {
                    javaFileCopy(s.getPath(), destPath + File.separator + s.getName());
                } else if (s.getPath().endsWith("xml")) {
                    xmlFileCopy(s.getPath(), destPath + File.separator + s.getName()); // 因为修改的xml都会有个换行符
                } else {
                    originalFileCopy(s.getPath(), destPath + File.separator + s.getName());
                }

            } else {
                dirCopy(s.getPath(), destPath + File.separator + s.getName());
            }
        }
    }


    public static void javaFileCopy(String srcPath, String destPath) {
        File src = new File(srcPath);
        File dest = new File(destPath);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        InputStreamReader inReader = null;
        OutputStreamWriter outWriter = null;
        try {
            inReader = new InputStreamReader(new FileInputStream(src), "UTF-8");
            outWriter = new OutputStreamWriter(new FileOutputStream(dest), "UTF-8");

            reader = new BufferedReader(inReader);
            writer = new BufferedWriter(outWriter);
            String lineStr = null;
            while ((lineStr = reader.readLine()) != null) {
                if (lineStr.startsWith("import") || lineStr.contains(sup_vertical)) {
                    lineStr = convertSupportClassToAndroidx(lineStr);
                }
                writer.write(lineStr + "\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(reader);
            closeStream(writer);
            closeStream(inReader);
            closeStream(outWriter);
        }
    }

    public static void xmlFileCopy(String srcPath, String destPath) {
        File src = new File(srcPath);
        File dest = new File(destPath);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        InputStreamReader inReader = null;
        OutputStreamWriter outWriter = null;
        try {
            inReader = new InputStreamReader(new FileInputStream(src), "UTF-8");
            outWriter = new OutputStreamWriter(new FileOutputStream(dest), "UTF-8");

            reader = new BufferedReader(inReader);
            writer = new BufferedWriter(outWriter);
            String lineStr = null;
            while ((lineStr = reader.readLine()) != null) {
                if (lineStr.contains("android.support")) {
                    lineStr = convertSupportClassToAndroidx(lineStr);
                    writer.write(lineStr + "\r\n");
                }
                if (firstWhile) {
                    writer.write(lineStr);
                    firstWhile=false;
                }else{
                    writer.write("\n"+lineStr);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(reader);
            closeStream(writer);
            closeStream(inReader);
            closeStream(outWriter);
        }
    }

    //
    public static void originalFileCopy(String srcPath, String destPath) {
        File src = new File(srcPath);
        File dest = new File(destPath);
        //使用jdk1.7 try-with-resource 释放资源，并添加了缓存流
        InputStream is = null;
        OutputStream out = null;
        try {
            is = new BufferedInputStream(new FileInputStream(src));
            out = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] flush = new byte[1024];
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                out.write(flush, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(is);
            closeStream(out);
        }
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    private static String convertSupportClassToAndroidx(String line) {
        String destLine = "";
        if (line.contains(AndroidxMapping.Support_Prex) || line.contains(sup_vertical)) {
            if (line.contains(sup_NonNull)) {
                destLine = line.replace(sup_NonNull, ax_NonNull);
            } else if (line.contains(sup_LinearLayoutManager)) {
                destLine = line.replace(sup_LinearLayoutManager, ax_LinearLayoutManager);
            } else if (line.contains(sup_OrientationHelper)) {
                destLine = line.replace(sup_OrientationHelper, ax_OrientationHelper);
            } else if (line.contains(sup_RecyclerView)) {
                destLine = line.replace(sup_RecyclerView, ax_RecyclerView);
            } else if (line.contains(sup_Fragment)) {
                destLine = line.replace(sup_Fragment, ax_Fragment);
            } else if (line.contains(sup_vertical)) {
                destLine = line.replace(sup_vertical, ax_vertical);
            } else if (line.contains(sup_FragmentActivity)) {
                destLine = line.replace(sup_FragmentActivity, ax_FragmentActivity);
            } else if (line.contains(sup_FragmentManager)) {
                destLine = line.replace(sup_FragmentManager, ax_FragmentManager);
            } else if (line.contains(sup_FragmentTransactionl)) {
                destLine = line.replace(sup_FragmentTransactionl, ax_FragmentTransactionl);
            } else if (line.contains(sup_ContextCompat)) {
                destLine = line.replace(sup_ContextCompat, ax_ContextCompat);
            } else if (line.contains(sup_vertical)) {
                destLine = line.replace(sup_vertical, ax_vertical);
            }
            return destLine;
        } else {
            return line;
        }
    }

}
