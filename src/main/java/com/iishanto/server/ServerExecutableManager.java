package com.iishanto.server;

import com.intellij.openapi.util.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ServerExecutableManager {
    public static ProcessBuilder runServer() throws IOException {
        File executableFile = FileUtil.createTempFile("apex-jorje-lsp", ".jar");
        try (InputStream in = ServerExecutableManager.class.getResourceAsStream("/bin/apex-jorje-lsp.jar")) {
            assert in != null;
            Files.copy(in, executableFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        executableFile.setExecutable(true);
        System.out.println(executableFile.getAbsolutePath());

        String []command=new String[]{
                "java",
                "-Xms512m",
                "-Xmx1024m",
                "-jar",
                "-Ddebug.internal.errors=true",
                "-Ddebug.semantic.errors=true",
                "-Ddebug.completion.statistics=true",
                executableFile.getAbsolutePath(),
        };
        return new ProcessBuilder(command);
    }
}
