package org.fabasoad.db.pojo;

/**
 * @author efabizhevsky
 * @date 11/25/2016.
 */
public class ReferencePojo extends BasePojo {

    private int id;
    private String title;
    private String fileName;

    public ReferencePojo(int id, String title, String fileName) {
        this.id = id;
        this.title = title;
        this.fileName = fileName;
    }
}
