package org.fabasoad.db.pojo;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class ReferencePojo extends BasePojo {

    public int getId() {
        return (int) getProperty("SR_ID");
    }

    public void setId(int id) {
        setProperty("SR_ID", id);
    }

    public String getTitle() {
        return String.valueOf(getProperty("SR_TITLE"));
    }

    public void setTitle(String title) {
        setProperty("SR_TITLE", title);
    }

    public String getFileName() {
        return String.valueOf(getProperty("SR_FILE_NAME"));
    }

    public void setFileName(String fileName) {
        setProperty("SR_FILE_NAME", fileName);
    }
}
