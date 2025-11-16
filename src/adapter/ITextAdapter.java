package adapter;

import model.Document;
import strategy.ConversionStrategy;
import util.ConversionException;

public class ITextAdapter implements ConversionStrategy {
    private String libraryName = "iText 7.x";

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @Override
    public Document convert(Document input) throws ConversionException {
        System.out.println("ITextAdapter: Using " + libraryName + " for conversion");
        
        if (!supports(input.getFormat(), "PDF")) {
            throw new ConversionException("ITextAdapter only supports conversion to PDF");
        }
        
        try {
            byte[] pdfContent = adaptITextConversion(input);

            Document result = getDocument(input, pdfContent);

            System.out.println("ITextAdapter: Conversion completed using " + libraryName);
            return result;

        } catch (Exception e) {
            throw new ConversionException("ITextAdapter: Conversion failed", e);
        }
    }

    private Document getDocument(Document input, byte[] pdfContent) {
        String outputFileName = input.getFileName();
        if (!outputFileName.toLowerCase().endsWith(".pdf")) {
            int dotIndex = outputFileName.lastIndexOf('.');
            outputFileName = (dotIndex > 0 ? outputFileName.substring(0, dotIndex) : outputFileName) + ".pdf";
        }

        Document result = new Document(pdfContent, "PDF", outputFileName);
        result.addMetadata("conversion_strategy", getStrategyName());
        result.addMetadata("library", libraryName);
        result.addMetadata("original_format", input.getFormat());
        return result;
    }

    @Override
    public boolean supports(String inputFormat, String outputFormat) {
        return "PDF".equalsIgnoreCase(outputFormat);
    }
    
    @Override
    public String getStrategyName() {
        return "iText Library Adapter";
    }
    
    private byte[] adaptITextConversion(Document input) {
        String content = "PDF created by " + libraryName + " adapter\n" +
                        "Original format: " + input.getFormat() + "\n" +
                        "Original size: " + input.getContent().length + " bytes";
        return content.getBytes();
    }
}
