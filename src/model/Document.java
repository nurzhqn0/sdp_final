package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private byte[] content;
    private String format;
    private String fileName;
    private long fileSize;
    private Map<String, String> metadata;

    public Document(byte[] content, String format, String fileName) {
        this.content = content;
        this.format = format;
        this.fileName = fileName;
        this.fileSize = content != null ? content.length : 0;
        this.metadata = new HashMap<>();
    }

    public Document(byte[] content, String format) {
        this(content, format, "untitled." + format.toLowerCase());
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
        this.fileSize = content != null ? content.length : 0;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void addMetadata(String key, String value) {
        this.metadata.put(key, value);
    }

    public String getMetadata(String key) {
        return this.metadata.get(key);
    }

    @Override
    public String toString() {
        return "Document{" +
                "fileName='" + fileName + '\'' +
                ", format='" + format + '\'' +
                ", fileSize=" + fileSize +
                " bytes}";
    }
}
