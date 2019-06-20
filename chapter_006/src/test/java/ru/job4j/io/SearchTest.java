package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    Search search = new Search();
    String root;
    File file1;
    File file2;
    File file3;
    File file4;
    @Before
    public void creating() throws IOException {
        File tmp = new File(System.getProperty("java.io.tmpdir"));
        var rootDir = new File(tmp, "test");
        //noinspection all
        rootDir.mkdir();
        this.root = rootDir.getPath();
        var dir1 = new File(rootDir, "firstDir");
        //noinspection all
        dir1.mkdir();
        var nestedDir1 = new File(dir1, "nested");
        //noinspection all
        nestedDir1.mkdir();
        var nestedDir2 = new File(dir1, "nested2");
        //noinspection all
        nestedDir2.mkdir();

        this.file1 = new File(rootDir, "1.txt");
        //noinspection all
        this.file1.createNewFile();
        this.file2 = new File(dir1, "2.log");
        //noinspection all
        this.file2.createNewFile();
        this.file3 = new File(nestedDir1, "3.txt");
        //noinspection all
        this.file3.createNewFile();
        this.file4 = new File(nestedDir1, "4.txt");
        //noinspection all
        this.file4.createNewFile();
    }

    @Test
    public void whenSearchFilesThanGetListOfFiles() {
        assertThat(this.search.files(this.root, List.of("txt", "log")),
                is(List.of(file1, file2, file3, file4)));
    }

    @Test
    public void whenAttemptToSearchInNonExsistsFolderThenEmptyList() {
        assertThat(this.search.files("non exists", List.of("txt", "log")),
                is(new ArrayList<File>()));
    }

    @After
    public void clean() throws IOException {
        var rootDir = new File(this.root);

        FileUtils.deleteDirectory(rootDir);
    }
}

