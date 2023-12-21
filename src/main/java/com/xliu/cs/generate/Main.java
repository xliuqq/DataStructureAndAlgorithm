package com.xliu.cs.generate;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

@Log4j2
public class Main {

    private static final Set<String> skipPkgs = Collections.singleton("com.xliu.cs.generate");

    private static class PkgAndClasses {
        String pkgName;
        List<String> classes = new ArrayList<>();

        int getLevel() {
            // com.xliu.cs
            return pkgName.split("\\.").length - 3;
        }
    }

    public static void main(String[] args) throws IOException {
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
        SortedMap<String, PkgAndClasses> pkgClasses = groupByPackage(classes);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("README.md")))) {
            writer.write("# 数据结构与算法\n\n");
            for (Map.Entry<String, PkgAndClasses> pkgClass : pkgClasses.entrySet()) {
                handlePkg(pkgClass.getValue(), writer);
                handleClasses(pkgClass.getValue(), writer);
            }
        }
    }

    private static SortedMap<String, PkgAndClasses> groupByPackage(List<String> classes) {
        SortedMap<String, PkgAndClasses> pkgClasses = new TreeMap<>();
        for (String clazz : classes) {
            // 获取包名
            int index = clazz.lastIndexOf(".");
            String pkg;
            if (index != -1) {
                pkg = clazz.substring(0, index);
            } else {
                // 不存在包名
                pkg = "";
            }

            // 过滤掉不需要匹配的包名
            if (skipPkgs.contains(pkg)) {
                continue;
            }

            pkgClasses.compute(pkg, (k, v) -> {
                if (v == null) {
                    v = new PkgAndClasses();
                }
                v.pkgName = pkg;
                // 忽略包名类的类注解
                if (!clazz.endsWith(".package-info")) {
                    v.classes.add(clazz);
                }

                return v;
            });
        }
        return pkgClasses;
    }

    private static void handlePkg(PkgAndClasses pkgAndClasses, Writer writer) throws IOException {
        String pkg = pkgAndClasses.pkgName;
        String pkgClass = String.format("%s.package-info", pkg);
        try {
            Class<?> aClass = Class.forName(pkgClass);
            PkgNote pkgNote = aClass.getAnnotation(PkgNote.class);

            if (pkgNote == null) {
                if (aClass.getAnnotation(IgnoreNote.class) == null) {
                    log.error("package [{}] has no pkg annotation.", pkg);
                }
                return;
            }
            log.debug("handle pkg [{}]", aClass.getPackage().getName());
            writer.write(String.format("#%s %s\n", "#".repeat(pkgAndClasses.getLevel()), pkgNote.value()));
            writer.write(pkgNote.description() + "\n\n");
        } catch (ClassNotFoundException e) {
            // ignore not found
            log.error("package [{}] has no pkg annotation.", pkg);
        }
    }

    private static void handleClasses(PkgAndClasses pkgAndClasses, Writer writer) throws IOException {
        for (String clazz : pkgAndClasses.classes) {
            // 根据名称加载类
            Class<?> aClass = null;
            try {
                aClass = Class.forName(clazz);
            } catch (ClassNotFoundException e) {
                log.error("class [{}] is not exist", clazz);
                continue;
            }

            ClassNote classNote = aClass.getAnnotation(ClassNote.class);
            if (classNote == null) {
                // 忽略内部类和显式标记不需要注解的类
                if (!aClass.getName().contains("$") && aClass.getAnnotation(IgnoreNote.class) == null) {
                    log.error("class [{}] has no class annotation.", clazz);
                }
                continue;
            }
            writer.write(String.format("[%s](%s)\n\n", classNote.value(), "src/main/java/" + aClass.getName().replace(".", "/") + ".java"));
            for (Method method : aClass.getMethods()) {
                MethodNote methodNote = method.getAnnotation(MethodNote.class);
                if (methodNote == null) {
//                    log.error("method [{}] has no method annotation.", method.getName());
                    continue;
                }
                writer.write(String.format("- %s\n\n", methodNote.value()));
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
