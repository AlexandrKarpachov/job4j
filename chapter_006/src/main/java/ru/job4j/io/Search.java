package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * {@code Search} provides functionality of searching files
 *
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 20.06.2019
 */
public class Search {

    /**
     * Search files by extensions.
     * @param parent directory to be searched.
     * @param exts list with extensions.
     * @return list with found files, or empty list if files not found or <tt>parent</tt> is wrong.
     */
    public List<File> files(String parent, List<String> exts) {
        var root = new File(parent);
        var result = new ArrayList<File>();
        Queue<File> directories = new LinkedList<>();
        if (root.isDirectory()) {
            directories.add(root);
        }
        while (!directories.isEmpty()) {
            var file = directories.poll();
            var fileList = file.listFiles();
            //noinspection ConstantConditions
            for (File f : fileList) {
                if (f.isDirectory()) {
                    directories.add(f);
                } else if (this.contains(exts, f.getName())) {
                    result.add(f);
                }
            }
        }
        return result;
    }

    private boolean contains(List<String> exts, String fileName) {
        boolean result = false;
        for (String ext : exts) {
            if (fileName.endsWith(ext)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
