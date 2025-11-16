package adapter;

import model.Document;
import strategy.ConversionStrategy;
import util.ConversionException;

public class POIAdapter implements ConversionStrategy {
    private final String libraryName = "Apache POI 5.x";
    
    @Override
    public Document convert(Document input) throws ConversionException {
        System.out.println("POIAdapter: Using " + libraryName + " for conversion");
        
        if (!supports(input.getFormat(), "DOCX")) {
            throw new ConversionException("POIAdapter only supports conversion to DOCX");
        }
        
        try {
            byte[] docxContent = adaptPOIConversion(input);

            Document result = getDocument(input, docxContent);

            System.out.println("POIAdapter: Conversion completed using " + libraryName);
            return result;
            
        } catch (Exception e) {
            throw new ConversionException("POIAdapter: Conversion failed", e);
        }
    }

    private Document getDocument(Document input, byte[] docxContent) {
        String outputFileName = input.getFileName();
        if (!outputFileName.toLowerCase().endsWith(".docx")) {
            int dotIndex = outputFileName.lastIndexOf('.');
            outputFileName = (dotIndex > 0 ? outputFileName.substring(0, dotIndex) : outputFileName) + ".docx";
        }

        Document result = new Document(docxContent, "DOCX", outputFileName);
        result.addMetadata("conversion_strategy", getStrategyName());
        result.addMetadata("library", libraryName);
        result.addMetadata("original_format", input.getFormat());
        return result;
    }

    @Override
    public boolean supports(String inputFormat, String outputFormat) {
        return "DOCX".equalsIgnoreCase(outputFormat) ||
               "DOC".equalsIgnoreCase(outputFormat);
    }
    
    @Override
    public String getStrategyName() {
        return "Apache POI Library Adapter";
    }

    private byte[] adaptPOIConversion(Document input) {
        String content = "DOCX created by " + libraryName + " adapter\n" +
                        "Original format: " + input.getFormat() + "\n" +
                        "Original size: " + input.getContent().length + " bytes";
        return content.getBytes();
    }
}
