package facade;

import factory.ConverterFactory;
import factory.DocumentConverterFactory;
import factory.ImageConverterFactory;
import model.Document;
import model.ConversionResult;
import observer.ConversionSubject;
import observer.Observer;
import strategy.ConversionStrategy;
import util.ConversionException;
import decorator.DocumentProcessor;
import adapter.ITextAdapter;
import adapter.POIAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConversionFacade {
    
    private ConverterFactory documentFactory;
    private ConverterFactory imageFactory;
    private ConversionSubject subject;
    private List<ConversionStrategy> availableAdapters;

    public ConversionFacade() {
        this.documentFactory = new DocumentConverterFactory();
        this.imageFactory = new ImageConverterFactory();
        this.subject = new ConversionSubject();
        this.availableAdapters = new ArrayList<>();

        availableAdapters.add(new ITextAdapter());
        availableAdapters.add(new POIAdapter());
        
        System.out.println("ConversionFacade: Initialized with all subsystems");
    }

    public void setDocumentFactory(ConverterFactory documentFactory) {
        this.documentFactory = documentFactory;
    }

    public void setImageFactory(ConverterFactory imageFactory) {
        this.imageFactory = imageFactory;
    }

    public void setSubject(ConversionSubject subject) {
        this.subject = subject;
    }

    public void setAvailableAdapters(List<ConversionStrategy> availableAdapters) {
        this.availableAdapters = availableAdapters;
    }

    public void attachObserver(Observer observer) {
        subject.attach(observer);
    }

    public void detachObserver(Observer observer) {
        subject.detach(observer);
    }

    public ConversionResult convertDocument(String inputPath, String outputFormat) {
        long startTime = System.currentTimeMillis();
        
        try {
            subject.notifyObservers("Loading document: " + inputPath, 0);
            
            // load document from file
            Document input = loadDocumentFromFile(inputPath);
            subject.notifyObservers("Document loaded successfully", 20);
            
            // perform conversion
            Document output = convertDocument(input, outputFormat);
            subject.notifyObservers("Conversion completed", 100);
            
            long endTime = System.currentTimeMillis();
            return new ConversionResult(output, true, "Conversion successful", endTime - startTime);
            
        } catch (Exception e) {
            subject.notifyObservers("Conversion failed: " + e.getMessage(), 0);
            return new ConversionResult(null, false, "Conversion failed: " + e.getMessage());
        }
    }

    public Document convertDocument(Document input, String outputFormat) throws ConversionException {
        subject.notifyObservers("Starting conversion: " + input.getFormat() + " -> " + outputFormat, 30);
        
        // select5 appropriate conversion strategy
        ConversionStrategy strategy = selectStrategy(input.getFormat(), outputFormat);
        subject.notifyObservers("Selected strategy: " + strategy.getStrategyName(), 50);
        
        // perform conversion
        Document result = strategy.convert(input);
        subject.notifyObservers("Conversion completed successfully", 100);
        
        return result;
    }

    public ConversionResult convertAndProcess(String inputPath, String outputFormat, 
                                              DocumentProcessor processor) {
        long startTime = System.currentTimeMillis();
        
        try {
            subject.notifyObservers("Loading and converting document", 0);
            
            Document input = loadDocumentFromFile(inputPath);
            subject.notifyObservers("Document loaded", 20);
            
            Document converted = convertDocument(input, outputFormat);
            subject.notifyObservers("Document converted", 60);
            
            // apply decorators
            Document processed = processor.process(converted);
            subject.notifyObservers("Processing completed", 100);
            
            long endTime = System.currentTimeMillis();
            return new ConversionResult(processed, true, 
                "Conversion and processing successful", endTime - startTime);
            
        } catch (Exception e) {
            subject.notifyObservers("Operation failed: " + e.getMessage(), 0);
            return new ConversionResult(null, false, "Operation failed: " + e.getMessage());
        }
    }

    public List<ConversionResult> batchConvert(List<String> inputPaths, String outputFormat) {
        List<ConversionResult> results = new ArrayList<>();
        
        subject.notifyObservers("Starting batch conversion of " + inputPaths.size() + " files", 0);
        
        for (int i = 0; i < inputPaths.size(); i++) {
            int progress = (int) ((i + 1) * 100.0 / inputPaths.size());
            subject.notifyObservers("Converting file " + (i + 1) + " of " + inputPaths.size(), progress);
            
            ConversionResult result = convertDocument(inputPaths.get(i), outputFormat);
            results.add(result);
        }
        
        subject.notifyObservers("Batch conversion completed", 100);
        return results;
    }

    public void saveDocument(Document document, String outputPath) throws IOException {
        subject.notifyObservers("Saving document to: " + outputPath, 90);
        
        Path path = Paths.get(outputPath);
        Files.write(path, document.getContent());
        
        subject.notifyObservers("Document saved successfully", 100);
        System.out.println("Document saved to: " + outputPath);
    }

    private ConversionStrategy selectStrategy(String inputFormat, String outputFormat) 
            throws ConversionException {
        
        // document factory
        if (documentFactory.supportsConversion(inputFormat, outputFormat)) {
            return documentFactory.createConverter(inputFormat, outputFormat);
        }
        
        // image factory
        if (imageFactory.supportsConversion(inputFormat, outputFormat)) {
            return imageFactory.createConverter(inputFormat, outputFormat);
        }
        
        // adapters
        for (ConversionStrategy adapter : availableAdapters) {
            if (adapter.supports(inputFormat, outputFormat)) {
                return adapter;
            }
        }
        
        throw new ConversionException("No suitable converter found for " + 
            inputFormat + " -> " + outputFormat);
    }

    private Document loadDocumentFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }
        
        byte[] content = Files.readAllBytes(path);
        String fileName = path.getFileName().toString();
        String format = extractFormat(fileName);
        
        return new Document(content, format, fileName);
    }

    private String extractFormat(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toUpperCase();
        }
        return "UNKNOWN";
    }
}
