package model;


public class ConversionResult {
    private Document document;
    private boolean success;
    private String message;
    private long processingTime;

    public ConversionResult(Document document, boolean success, String message) {
        this.document = document;
        this.success = success;
        this.message = message;
    }

    public ConversionResult(Document document, boolean success, String message, long processingTime) {
        this(document, success, message);
        this.processingTime = processingTime;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        return "ConversionResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", processingTime=" + processingTime + "ms" +
                ", document=" + document +
                '}';
    }
}
