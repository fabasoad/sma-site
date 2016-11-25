package org.fabasoad.db;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public enum SqlType {
    SQLITE("sqlite");

    private String folderName;

    SqlType(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }
}
