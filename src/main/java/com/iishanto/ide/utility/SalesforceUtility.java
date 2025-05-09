package com.iishanto.ide.utility;

import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.iishanto.ide.config.SalesforceProjectConfig;
import com.iishanto.language.sf.ApexIcons;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static com.intellij.configurationStore.StorageUtilKt.NOTIFICATION_GROUP_ID;

public class SalesforceUtility {

    private static String getFileDir(String fileName){
        return fileName.substring(0,fileName.lastIndexOf('/'));
    }
    private static String getFileTitle(String fileName){
        String fileTitle=fileName.substring(fileName.lastIndexOf('/')+1);
        return fileTitle.substring(0,fileTitle.lastIndexOf('.'));
    }

    public static void createApexClassFile(String fileName,String location, Project project, VirtualFile projectDir) {
        // Create the file
        String sfCliPath = SalesforceProjectConfig.getInstance(project).getSfCliPath();
        //sf apex generate class --name myClass --output-dir force-app/main/default/classes
        IDEUtility.SalesforceOutputManager outputManager = IDEUtility.getSalesforceOutputManager(project);
        VirtualFile fileDirVf = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(location));
        System.out.println("Creating apex class: "+fileName+" in "+location+" GIVEN "+fileName);
        GeneralCommandLine commandLine = new GeneralCommandLine(sfCliPath, "apex", "generate", "class", "--name", fileName, "--output-dir", location)
                .withWorkDirectory(project.getBasePath())
                .withCharset(Charsets.UTF_8);
        try {
            OSProcessHandler processHandler = new OSProcessHandler(commandLine);
            processHandler.addProcessListener(new ProcessListener() {
                @Override
                public void processTerminated(@NotNull ProcessEvent event) {
                    if(fileDirVf!=null){
                        System.out.println("Apex class created successfully");
                        submitForIndexing(project,fileDirVf);
                        IDEUtility.forceRefreshAndExpand(fileDirVf, project,null);
                    }
                    showNotification(project, "New apex class created", "Apex class is created in: %s with metadata.".formatted(fileName));
                }

                @Override
                public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                    System.out.println(event.getText());
                }
            });
            processHandler.startNotify();
            outputManager.attachToProcess(processHandler, "Creating Apex Class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deployFile(String fileName, DeploymentType deploymentType, Project project) {
        // Create the file
        String sfCliPath = SalesforceProjectConfig.getInstance(project).getSfCliPath();
        //sf apex generate class --name myClass --output-dir force-app/main/default/classes
        IDEUtility.SalesforceOutputManager outputManager = IDEUtility.getSalesforceOutputManager(project);
        GeneralCommandLine commandLine = new GeneralCommandLine(sfCliPath, "project", "deploy","start", "--source-dir", fileName,"--target-org",SalesforceProjectConfig.getInstance(project).getSelectedAlias())
                .withWorkDirectory(project.getBasePath())
                .withCharset(Charsets.UTF_8);
        try {
            OSProcessHandler processHandler = new OSProcessHandler(commandLine);
            processHandler.addProcessListener(new ProcessListener() {
                @Override
                public void processTerminated(@NotNull ProcessEvent event) {
                    System.out.println("Apex class created successfully");
                    showNotification(project, "New apex class created", "Apex class is created in: %s with metadata.".formatted(fileName));
                }

                @Override
                public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                    System.out.println(event.getText());
                    outputManager.log(event.getText());
                }
            });
            processHandler.startNotify();
            outputManager.attachToProcess(processHandler, "Creating Apex Class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showNotification(Project project, String title, String content){
        Notification notification = new Notification(NOTIFICATION_GROUP_ID, title, content, NotificationType.INFORMATION);
        notification.setIcon(ApexIcons.APEX_ICON);
        ApplicationManager.getApplication().invokeLater(() -> Notifications.Bus.notify(notification, project));
    }

    public static void createApexTriggerFile(String string) {
    }

    private static void submitForIndexing(Project project,String filePath){
        ApplicationManager.getApplication().invokeLater(()->{
            LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(filePath));
            project.getBaseDir().refresh(false,true);
        });
    }

    private static void submitForIndexing(Project project,VirtualFile file){
        submitForIndexing(project,file.getPath());
    }

    public static void extractJavaApexTypeToApexClass(Project project) {
        String projectDirPath = project.getBasePath();
        String typeDirectory = projectDirPath + "/.sfdx/tools/252/StandardApexLibrary";
        Path storageDirectory = Paths.get(typeDirectory);
        String unzipLocation = projectDirPath + "/.jetforce/java-types";

        try{
            File zipFile;
            try (InputStream resourceStream = SalesforceUtility.class.getClassLoader().getResourceAsStream("package/standard-types-main.zip")) {
                if (resourceStream == null) {
                    throw new FileNotFoundException("Resource not found: package/standard-types-main.zip");
                }
                Path tempFile = Files.createTempFile("standard-types-main", ".zip");
                Files.copy(resourceStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                zipFile = tempFile.toFile();
            } catch (IOException e) {
                throw new RuntimeException("Failed to load resource: package/standard-types-main.zip", e);
            }
            //extract the zip file to the project directory
            if(!Files.exists(storageDirectory)){
                Files.createDirectories(storageDirectory);
            }
            //unzip the file
            Path ZipFileUnzipLocation = Paths.get(unzipLocation);
            if(!Files.exists(ZipFileUnzipLocation)){
                Files.createDirectories(ZipFileUnzipLocation);
            }
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            File dest=new File(unzipLocation);
            byte[] buffer = new byte[1024];
            while(zipEntry != null){
                File newFile = newFile(dest, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            convertJavaApexTypeToApexClass(typeDirectory,unzipLocation,project);
        }
    }

    private static void convertJavaApexTypeToApexClass(String storagePath,String extractionPath,Project project) {
        try {
            Path currentPath = Paths.get(extractionPath+"/standard-types-main/src/main/java/com/nawforce/runforce");
            try (Stream<Path> walk = Files.walk(currentPath)) {
                walk.filter(Files::isRegularFile)
                        .filter(p -> p.toString().toLowerCase().endsWith(".java"))
                        .filter(p -> !p.getFileName().toString().equals("JavaToApexBatchConverter.java"))
                        .forEach(path -> {
                            try {
                                System.out.println("Processing: " + path);
                                convertFile(storagePath,path.toString(),project);
                            } catch (IOException e) {
                                System.err.println("Error processing file " + path + ": " + e.getMessage());
                            }
                        });
            }
            System.out.println("Conversion completed!");
        } catch (IOException e) {
            System.err.println("Error walking through directory: " + e.getMessage());
        }
    }

    private static void convertFile(String storagePath,String inputPath,Project project) throws IOException {
        // Read the file
        String content = Files.readString(Path.of(inputPath));

        // Remove package statements
        content = content.replaceAll("package\\s+[a-zA-Z0-9_.]+;\\s*", "");

        // Remove import statements
        content = content.replaceAll("import\\s+.*", "");

        // Convert double quotes to single quotes
        content = convertQuotes(content);

        // Create output path (same location but .cls extension)
        String fileName = new File(inputPath).getName();
        String immediateDirectory = new File(new File(inputPath).getParent()).getName();
        storagePath += "/" +immediateDirectory+ "/" + fileName;
        String outputPath = storagePath.substring(0, storagePath.lastIndexOf('.')) + ".cls";

        // Write the converted content
        NotificationHub.getInstance().willCreateFile(outputPath, new LspResponseListener() {
            @Override
            public void listen(JsonObject jsonObject) {
                super.listen(jsonObject);
            }
        });
        Files.writeString(Path.of(outputPath), content);
        createClassMetadataFile(outputPath);
        NotificationHub.getInstance().didCreateFile(outputPath, new LspResponseListener() {
            @Override
            public void listen(JsonObject jsonObject) {
                super.listen(jsonObject);
            }
        });
        System.out.println("Created: " + outputPath);
        submitForIndexing(project, outputPath);
    }


    private static void createClassMetadataFile(String outputPath) throws IOException {
        String metadataPath = outputPath.substring(0, outputPath.lastIndexOf('.')) + ".cls-meta.xml";
        String metadataContent = """
                <?xml version="1.0" encoding="UTF-8"?>
                <ApexClass xmlns="http://soap.sforce.com/2006/04/metadata">
                    <apiVersion>59.0</apiVersion>
                    <status>Active</status>
                </ApexClass>
                """;
        Files.writeString(Path.of(metadataPath), metadataContent);
        System.out.println("Created: " + metadataPath);
    }
    private static String convertQuotes(String content) {
        StringBuilder result = new StringBuilder();
        boolean inString = false;
        boolean inComment = false;
        boolean inLineComment = false;
        char[] chars = content.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];

            // Handle comments
            if (!inString) {
                if (!inComment && !inLineComment && i < chars.length - 1) {
                    if (current == '/' && chars[i + 1] == '*') {
                        inComment = true;
                    } else if (current == '/' && chars[i + 1] == '/') {
                        inLineComment = true;
                    }
                } else if (inComment && i < chars.length - 1) {
                    if (current == '*' && chars[i + 1] == '/') {
                        inComment = false;
                    }
                } else if (inLineComment && current == '\n') {
                    inLineComment = false;
                }
            }

            // Handle strings
            if (!inComment && !inLineComment) {
                if (current == '"' && (i == 0 || chars[i - 1] != '\\')) {
                    inString = !inString;
                    result.append('\'');
                    continue;
                }
            }

            result.append(current);
        }

        return result.toString();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static enum DeploymentType{
        ApexClass,
        CustomObject,
    }
}
