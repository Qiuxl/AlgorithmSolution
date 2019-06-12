package Utils;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassUtil {

    public static List<Class<?>> getAllClassByPackageName(Package pkg) {
        String packageName = pkg.getName();
        // 获取当前包下以及子包下所以的类
        List<Class<?>> returnClassList = getAllClass(packageName);
        return returnClassList;
    }

    private static List<Class<?>> getAllClass(String packageName) {

        List<Class<?>> classes = new ArrayList<>();

        boolean recursive = true;

        String packageDirName = packageName.replace(".", File.separator);

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                // 只处理文件类型的
                if ("file".equals(protocol)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ) {
                    String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name());
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 获取包下的所有class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {

        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirFiles = dir.listFiles(pathname -> (recursive && pathname.isDirectory() || pathname.getName().endsWith(".class")));
        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - ".class".length());
                try {
                    classes.add(Class.forName(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

