package decorator;

import model.Document;

public class EncryptionDecorator extends DocumentDecorator {
    
    private String encryptionKey;

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public EncryptionDecorator(DocumentProcessor processor) {
        this(processor, "DEFAULT_KEY_12345");
    }
    
    public EncryptionDecorator(DocumentProcessor processor, String encryptionKey) {
        super(processor);
        this.encryptionKey = encryptionKey;
    }
    
    @Override
    public Document process(Document document) {
        Document processed = super.process(document);

        System.out.println("EncryptionDecorator: Encrypting document");
        
        byte[] encryptedContent = encrypt(processed.getContent());
        processed.setContent(encryptedContent);
        
        processed.addMetadata("encryption", "enabled");
        processed.addMetadata("encryption_algorithm", "AES-256 (simulated)");
        
        return processed;
    }

    private byte[] encrypt(byte[] content) {
        byte[] keyBytes = encryptionKey.getBytes();
        byte[] encrypted = new byte[content.length];
        
        for (int i = 0; i < content.length; i++) {
            encrypted[i] = (byte) (content[i] ^ keyBytes[i % keyBytes.length]);
        }
        
        return encrypted;
    }

    public byte[] decrypt(byte[] encryptedContent) {
        return encrypt(encryptedContent);
    }
}
