/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.APIConstants
 */
package com.empay.exceptions;

public class APIConstants {
    public static final String FILE_NOT_EXIST = "The file was not found at the specified path. ";
    public static final String SYSTEM_ERROR = "INTERNAL_SYSTEM_ERROR; failed to process the request";
    public static final String PARAMETER_MISSING = "INVALID URL; missing parameter ";
    public static final String ALREADY_IN_PROCESSING = "FAILED_TO_PROCESS; one file in processing.";
    public static final String DUPLICATE_FILENAME = "DUPLICATE_FILENAME; provided filename is already exist.";
    public static final String ERROR_OCCURRED = "ERROR_OCCURRED; an error occurred while inserting into file_upload_log!";
    public static final String BIN_PROCESSING_SCHEDULED = "BIN File Processing Scheduled Successfully.";
    public static final String FAILED_TO_INSERT_JOB = "FAILED_TO_INSERT_JOB; failed to insert processing_job!";
    public static final String FILE_NOT_FOUND = "FILE_NOT_FOUND; there is no filename found ";
    public static final String DELETION_NOT_ALLOWED = "DELETION_NOT_ALLOWED; file deletion not allowed.";
    public static final String FILE_IS_EMPTY = "The file contains no data please check the file and process again";
    public static final String INVALID_FILE = "Invalid VISA file";

    private APIConstants() {
    }
}

