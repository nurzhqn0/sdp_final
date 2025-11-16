package decorator;

import model.Document;

public class BaseDocumentProcessor implements DocumentProcessor {
    
    @Override
    public Document process(Document document) {
        System.out.println("BaseDocumentProcessor: Processing document " + document.getFileName());
        document.addMetadata("base_processing", "completed");
        return document;
    }
}
