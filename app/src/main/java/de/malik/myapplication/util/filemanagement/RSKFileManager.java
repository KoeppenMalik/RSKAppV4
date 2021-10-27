// Created on 29.01.2021, 16:46

package de.malik.myapplication.util.filemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.malik.myapplication.gui.MainActivity;

public class RSKFileManager {

    public static final Map<String, File> CREATED_FILES = new HashMap<>();
    public static final Map<String, File> CREATED_FOLDERS = new HashMap<>();

    public static final String COMMENT_PREFIX = "##";
    public static final String SPLIT_REGEX = "  ";
    public static final String[] FILE_NAMES = {
            "current_projects.csv", "archived_projects.csv",
            "current_projects_pauses.csv", "archived_projects_pauses.csv",
            "requests.csv",
            "saved_project_names.csv", "saved_work_descriptions.csv"
    };
    public static final String FOLDER_NAME = "rsk_data";
    
    private Printer printer;

    public RSKFileManager(Printer printer) {
        this.printer = printer;
        try {
            createFiles();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void createFiles() throws IOException {
        createFolder(FOLDER_NAME, MainActivity.FOLDER_PATH);
        File folder = CREATED_FOLDERS.get(FOLDER_NAME);
        for (String fileName : FILE_NAMES) {
            createFile(fileName, folder);
        }
        createFile("temp.csv", folder);
//        for (String s : FILE_NAMES) {
//            FileManager.CREATED_FILES.get(s).delete();
//        }
    }

    /**
     * creates a new file named <code>fileName</code> in <code>folder</code>. The created file will
     * be added to the HashMap <code>CREATED_FILES</code>
     * @param fileName the name which the new created file will have
     * @param folder the folder in which the file will be created
     * @return the file which just have been created
     * @throws IOException if an I/O error occurred
     */
    public static boolean createFile(@NonNull String fileName, @NonNull File folder) throws IOException {
        File file = new File(folder, fileName);
        boolean created = file.createNewFile();
        CREATED_FILES.put(fileName, file);
        return created;
    }

    /**
     * creates a folder named <code>folderName</code> at the path <code>folderPath</code>. The
     * created folder will be added to the HashMap <code>CREATED_FOLDERS</code>
     * @param folderName the name which the new created folder will have
     * @param folderPath the path at which the folder will be created
     * @return the folder which just have been created
     */
    public static boolean createFolder(@NonNull String folderName, @Nullable String folderPath) {
        File folder = new File(folderPath, folderName);
        boolean created = false;
        if (!folder.exists()) {
            created = folder.mkdir();
        }
        CREATED_FOLDERS.put(folderName, folder);
        return created;
    }

    public Printer getPrinter() {
        return printer;
    }
}