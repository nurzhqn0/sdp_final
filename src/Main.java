import facade.ConversionFacade;
import model.ConversionResult;
import observer.LogObserver;
import observer.ProgressObserver;
import decorator.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("Document Processing Pipeline - Design Patterns Demo");
        System.out.println("==============================================\n");

        ConversionFacade facade = new ConversionFacade();

        ProgressObserver progressObserver = new ProgressObserver("Main");
        LogObserver logObserver = new LogObserver();

        facade.attachObserver(progressObserver);
        facade.attachObserver(logObserver);

        demonstrateBasicConversion(facade);
        demonstrateDecoratorPattern(facade);
        demonstrateBatchConversion(facade);

        System.out.println("\n==============================================");
        logObserver.printLogs();

        System.out.println("==============================================");
        System.out.println("Demo completed successfully!");
        System.out.println("All 6 design patterns demonstrated:");
        System.out.println("✓ Strategy Pattern");
        System.out.println("✓ Factory Pattern");
        System.out.println("✓ Adapter Pattern");
        System.out.println("✓ Facade Pattern");
        System.out.println("✓ Observer Pattern");
        System.out.println("✓ Decorator Pattern");
        System.out.println("==============================================");
    }

    private static void demonstrateBasicConversion(ConversionFacade facade) {
        System.out.println("\n--- Demo 1: Basic Conversion (Strategy + Factory) ---");

        try {
            // create sample documents
            createSampleFile("sample.pdf", "This is a sample PDF document");
            createSampleFile("sample.docx", "This is a sample DOCX document");
            createSampleFile("sample.png", "This is a sample PNG image");

            // convert PDF to DOCX (using PdfToDocxStrategy via DocumentConverterFactory)
            System.out.println("\n1. Converting PDF to DOCX:");
            ConversionResult result1 = facade.convertDocument("sample.pdf", "DOCX");
            displayResult(result1);

            // convert DOCX to TXT (using DocxToTxtStrategy via DocumentConverterFactory)
            System.out.println("\n2. Converting DOCX to TXT:");
            ConversionResult result2 = facade.convertDocument("sample.docx", "TXT");
            displayResult(result2);

            // convert PNG to PDF (using ImageToPdfStrategy via ImageConverterFactory)
            System.out.println("\n3. Converting PNG to PDF:");
            ConversionResult result3 = facade.convertDocument("sample.png", "PDF");
            displayResult(result3);

        } catch (Exception e) {
            System.err.println("Error in basic conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateDecoratorPattern(ConversionFacade facade) {
        System.out.println("\n--- Demo 2: Decorator Pattern ---");

        try {
            createSampleFile("document.pdf", "Important confidential document");

            // create a processing pipeline with multiple decorators
            System.out.println("\nApplying multiple decorators:");

            // base processor
            DocumentProcessor processor = new BaseDocumentProcessor();

            // watermark
            processor = new WatermarkDecorator(processor, "CONFIDENTIAL");

            // compression
            processor = new CompressionDecorator(processor, 7);

            // encryption
            processor = new EncryptionDecorator(processor, "SecureKey123");

            // convert and process
            ConversionResult result = facade.convertAndProcess("document.pdf", "DOCX", processor);
            displayResult(result);

            if (result.isSuccess() && result.getDocument() != null) {
                System.out.println("\nDocument metadata after decorators:");
                result.getDocument().getMetadata().forEach((key, value) ->
                        System.out.println("  " + key + ": " + value)
                );
            }

        } catch (Exception e) {
            System.err.println("Error in decorator demo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateBatchConversion(ConversionFacade facade) {
        System.out.println("\n--- Demo 3: Batch Conversion ---");

        try {
            // create multiple sample files
            createSampleFile("file1.pdf", "Document 1");
            createSampleFile("file2.pdf", "Document 2");
            createSampleFile("file3.png", "Image 1");

            List<String> files = Arrays.asList(
                    "file1.pdf", "file2.pdf", "file3.png"
            );

            System.out.println("\nBatch converting " + files.size() + " files to PDF:");
            List<ConversionResult> results = facade.batchConvert(files, "PDF");

            System.out.println("\nBatch conversion results:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + files.get(i) + ": " +
                        (results.get(i).isSuccess() ? "SUCCESS" : "FAILED"));
            }

        } catch (Exception e) {
            System.err.println("Error in batch conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayResult(ConversionResult result) {
        System.out.println("Result: " + (result.isSuccess() ? "SUCCESS" : "FAILED"));
        System.out.println("Message: " + result.getMessage());
        System.out.println("Processing time: " + result.getProcessingTime() + "ms");
        if (result.getDocument() != null) {
            System.out.println("Output: " + result.getDocument());
        }
    }

    private static void createSampleFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes());
    }
}