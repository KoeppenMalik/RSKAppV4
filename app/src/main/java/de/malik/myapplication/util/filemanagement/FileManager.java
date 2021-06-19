// Created on 29.01.2021, 16:46

package de.malik.myapplication.util.filemanagement;

import android.content.Context;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static final String COMMENT_PREFIX = "##";
    public static final String SPLIT_REGEX = ",";

    private File customersFile, pausesFile, archiveFile, archivedPausesFile, requestsFile,
            savedCustomerNamesFile, savedWorkDescriptionsFile, tempFile, appDataFile;
    private Printer printer;

    public FileManager(Context context, Printer printer) {
        this.printer = printer;
        try {
            createFiles(context);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void createFiles(Context context) throws IOException {
        final String CUSTOMERS_FILE_NAME = "current.csv", ARCHIVE_FILE_NAME = "archive.csv", TEMP_FILE_NAME = "temp.csv",
                PAUSES_FILE_NAME = "pauses.csv", ARCHIVE_PAUSES_FILE_NAME = "archivePauses.csv", FOLDER_NAME = "RSK",
                REQUESTS_FILE_NAME = "requests.csv", SAVED_CUSTOMER_NAMES_FILE_NAME = "savedCustomerNames.csv",
                SAVED_WORK_DESCRIPTIONS_FILE_NAME = "savedWorkDescriptions.csv", APP_DATA_FILE_NAME = "data.csv";
        final File FOLDER = new File(context.getFilesDir(), FOLDER_NAME);
        customersFile = new File(FOLDER, CUSTOMERS_FILE_NAME);
        archiveFile = new File(FOLDER, ARCHIVE_FILE_NAME);
        tempFile = new File(FOLDER, TEMP_FILE_NAME);
        pausesFile = new File(FOLDER, PAUSES_FILE_NAME);
        archivedPausesFile = new File(FOLDER, ARCHIVE_PAUSES_FILE_NAME);
        savedCustomerNamesFile = new File(FOLDER, SAVED_CUSTOMER_NAMES_FILE_NAME);
        savedWorkDescriptionsFile = new File(FOLDER, SAVED_WORK_DESCRIPTIONS_FILE_NAME);
        requestsFile = new File(FOLDER, REQUESTS_FILE_NAME);
        appDataFile = new File(FOLDER, APP_DATA_FILE_NAME);

        if (!FOLDER.mkdir()) {
            FOLDER.mkdir();
        }
        if (FOLDER.mkdir()) {
            customersFile.createNewFile();
            archiveFile.createNewFile();
            savedCustomerNamesFile.createNewFile();
            pausesFile.createNewFile();
            archivedPausesFile.createNewFile();
            savedWorkDescriptionsFile.createNewFile();
            requestsFile.createNewFile();
            appDataFile.createNewFile();
        }
//        customersFile.delete();
//        archiveFile.delete();
//        pausesFile.delete();
//        archivedPausesFile.delete();
//        requestsFile.delete();
//        savedCustomerNamesFile.delete();
//        savedWorkDescriptionsFile.delete();
//        appDataFile.delete();
    }

    public Printer getPrinter() {
        return printer;
    }

    public File getCustomersFile() {
        return customersFile;
    }

    public File getArchiveFile() {
        return archiveFile;
    }

    public File getPausesFile() {
        return pausesFile;
    }

    public File getArchivedPausesFile() {
        return archivedPausesFile;
    }

    public File getRequestsFile() {
        return requestsFile;
    }

    public File getSavedCustomerNamesFile() {
        return savedCustomerNamesFile;
    }

    public File getSavedWorkDescriptionsFile() {
        return savedWorkDescriptionsFile;
    }

    public File getAppDataFile() {
        return appDataFile;
    }

    public File getTempFile() {
        return tempFile;
    }

}
