package decorator;

import model.Document;

public abstract class DocumentDecorator implements DocumentProcessor {
    
    protected DocumentProcessor wrappedProcessor;
    
    public DocumentDecorator(DocumentProcessor processor) {
        this.wrappedProcessor = processor;
    }
    
    @Override
    public Document process(Document document) {
        return wrappedProcessor.process(document);
    }
}
