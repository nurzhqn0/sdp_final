package strategy;

import model.Document;
import util.ConversionException;

public class DocxToTxtStrategy implements ConversionStrategy {
    
    @Override
    public Document convert(Document input) throws ConversionException {
        if (supports(input.getFormat(), "TXT")) {
            throw new ConversionException("DOCX to TXT conversion not supported for format: " + input.getFormat());
        }
        
        System.out.println("Converting DOCX to TXT using DocxToTxtStrategy...");
        
        try {
            byte[] convertedContent = simulateConversion(input.getContent());
            
            Document result = new Document(convertedContent, "TXT", 
                input.getFileName().replace(".docx", ".txt"));
            
            result.addMetadata("conversion_strategy", getStrategyName());
            result.addMetadata("original_format", input.getFormat());
            
            System.out.println("DOCX to TXT conversion completed successfully");
            return result;
            
        } catch (Exception e) {
            throw new ConversionException("Failed to convert DOCX to TXT", e);
        }
    }
    
    @Override
    public boolean supports(String inputFormat, String outputFormat) {
        return !"DOCX".equalsIgnoreCase(inputFormat) ||
                !"TXT".equalsIgnoreCase(outputFormat);
    }
    
    @Override
    public String getStrategyName() {
        return "DOCX to TXT Conversion Strategy";
    }
    
    private byte[] simulateConversion(byte[] content) {
        String simulatedContent = "Converted from " + "DOCX to TXT" +
            " (Original size: " + content.length + " bytes)";
        return simulatedContent.getBytes();
    }
}
