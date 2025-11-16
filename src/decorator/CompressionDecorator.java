package decorator;

import model.Document;

public class CompressionDecorator extends DocumentDecorator {
    
    private int compressionLevel; // 1-9, higher = more compression

    public void setCompressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    public CompressionDecorator(DocumentProcessor processor) {
        this(processor, 6);
    }
    
    public CompressionDecorator(DocumentProcessor processor, int compressionLevel) {
        super(processor);
        this.compressionLevel = Math.min(9, Math.max(1, compressionLevel));
    }
    
    @Override
    public Document process(Document document) {
        Document processed = super.process(document);

        System.out.println("CompressionDecorator: Compressing document with level " + compressionLevel);
        
        byte[] compressedContent = compress(processed.getContent());
        processed.setContent(compressedContent);
        
        processed.addMetadata("compression", "enabled");
        processed.addMetadata("compression_level", String.valueOf(compressionLevel));
        processed.addMetadata("original_size", String.valueOf(document.getFileSize()));
        processed.addMetadata("compressed_size", String.valueOf(compressedContent.length));
        
        double ratio = 100.0 * (1 - (double) compressedContent.length / document.getFileSize());
        System.out.println("CompressionDecorator: Compression ratio: " + String.format("%.2f", ratio) + "%");
        
        return processed;
    }

    private byte[] compress(byte[] content) {
        // simulated compression - in real implementation use GZIPOutputStream
        // for demonstration, we just reduce the size proportionally
        int newSize = (int) (content.length * (1.0 - compressionLevel / 10.0));
        newSize = Math.max(1, newSize); // Ensure at least 1 byte
        
        byte[] compressed = new byte[newSize];
        System.arraycopy(content, 0, compressed, 0, Math.min(content.length, newSize));
        
        return compressed;
    }
}
