package org.xliu.cs.algs_ds;

import org.xliu.cs.projects.anno_for_doc.annotations.IgnoreNote;
import org.xliu.cs.projects.anno_for_doc.generate.GenerateMarkDown;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@IgnoreNote
public class GenerateReadme {
    public static void main(String[] args) throws IOException {
        String pkgPrefix = "org.xliu.cs.algs_ds";
        String outFilePath = "README.md";
        Set<String> skipPkgs = Collections.emptySet();

        GenerateMarkDown.generate(pkgPrefix, outFilePath, skipPkgs);
    }
}
