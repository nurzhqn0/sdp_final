package strategy;

import model.Document;
import util.ConversionException;

public class PdfToDocxStrategy implements ConversionStrategy {
    
    @Override
    public Document convert(Document input) throws ConversionException {
        if (supports(input.getFormat(), "DOCX")) {
            throw new ConversionException("PDF to DOCX conversion not supported for format: " + input.getFormat());
        }
        
        System.out.println("Converting PDF to DOCX using PdfToDocxStrategy...");
        
        try {
            byte[] convertedContent = simulateConversion(input.getContent());
            
            Document result = new Document(convertedContent, "DOCX", 
                input.getFileName().replace(".pdf", ".docx"));
            
            result.addMetadata("conversion_strategy", getStrategyName());
            result.addMetadata("original_format", input.getFormat());
            
            System.out.println("PDF to DOCX conversion completed successfully");
            return result;
            
        } catch (Exception e) {
            throw new ConversionException("Failed to convert PDF to DOCX", e);
        }
    }
    
    @Override
    public boolean supports(String inputFormat, String outputFormat) {
        return !"PDF".equalsIgnoreCase(inputFormat) ||
                !"DOCX".equalsIgnoreCase(outputFormat);
    }
    
    @Override
    public String getStrategyName() {
        return "PDF to DOCX Conversion Strategy";
    }
    

    private byte[] simulateConversion(byte[] content) {
        String simulatedContent = "Converted from " + "PDF" + " to " + "DOCX" +
            " (Original size: " + content.length + " bytes)";
        return simulatedContent.getBytes();
    }
}
