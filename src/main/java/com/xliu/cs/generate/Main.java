package com.xliu.cs.generate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Main {

    static class PkgAndClass {
        Class pkgInfoClass;
        List<String> classes = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String pkgPrefix = "com/xliu/cs";
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(pkgPrefix);
        List<String> classes = new ArrayList<>();
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            if (url.getProtocol().equals("file")) {
                classes.addAll(listClasses(new File(url.getFile()), pkgPrefix));
            }
        }
        // 获取所有的 package-info，获取其包名，和包下的所有类
        Map<String, List<String>> pkgClasses = groupByPackage(classes);
        for (Map.Entry<String, List<String>> pkgClass : pkgClasses.entrySet()) {
            handlePkg(pkgClass.getKey());
            handleClasses(pkgClass.getValue());
            System.out.println(pkgClass);
        }
    }

    private static Map<String, PkgAndClass> groupByPackage(List<String> classes) {
        Map<String, PkgAndClass> pkgClasses = new HashMap<>();
        for (String clazz : classes) {
            String pkg = clazz.split("\\.")[0];
            pkgClasses.compute(pkg, (k, v) -> {
                if (v == null) {
                    v = new PkgAndClass();
                }
//                v.pkgInfoClass;
                return v;
            });
        }
        return pkgClasses;
    }

    private static void handlePkg(String pkg) {

    }

    private static void handleClasses(List<String> classes) throws ClassNotFoundException {
        for (String clazz : classes) {
            // 根据名称加载类
            Class<?> aClass = Class.forName(clazz);

            PkgNote classNote = aClass.getAnnotation(PkgNote.class);

            if (classNote != null) {
                System.out.println(aClass.getName() + ":" + classNote.value());
            }
        }

    }

    private static List<String> listClasses(File file, String pkgPrefix) {
        List<String> classes = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    classes.addAll(listClasses(f, pkgPrefix));
                }
            }
        } else {
            if (file.getName().endsWith(".class")) {
                // windows 下的路径兼容，将 '\' 换成 '/'
                String fPath = file.getAbsolutePath().replaceAll("\\\\", "/");
                String className = fPath.substring(fPath.lastIndexOf(pkgPrefix))
                        .replace(".class", "").replaceAll("/", ".");
                classes.add(className);
            }
        }
        return classes;
    }
}
