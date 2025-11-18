package decorator;

import model.Document;

public class WatermarkDecorator extends DocumentDecorator {
    
    private String watermarkText;

    public WatermarkDecorator(DocumentProcessor processor) {
        this(processor, "CONFIDENTIAL");
    }
    
    public WatermarkDecorator(DocumentProcessor processor, String watermarkText) {
        super(processor);
        this.watermarkText = watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }


    @Override
    public Document process(Document document) {
        // first, process with wrapped processor
        Document processed = super.process(document);
        
        // then add watermark
        System.out.println("WatermarkDecorator: Adding watermark '" + watermarkText + "'");
        
        byte[] watermarkedContent = addWatermark(processed.getContent());
        processed.setContent(watermarkedContent);
        
        processed.addMetadata("watermark", "enabled");
        processed.addMetadata("watermark_text", watermarkText);
        
        return processed;
    }

    private byte[] addWatermark(byte[] content) {
        String watermarkData = "\n[WATERMARK: " + watermarkText + "]\n";
        byte[] watermarkBytes = watermarkData.getBytes();
        
        byte[] result = new byte[content.length + watermarkBytes.length];
        System.arraycopy(content, 0, result, 0, content.length);
        System.arraycopy(watermarkBytes, 0, result, content.length, watermarkBytes.length);
        
        return result;
    }
}
