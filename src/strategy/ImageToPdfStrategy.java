package strategy;

import model.Document;
import util.ConversionException;

public class ImageToPdfStrategy implements ConversionStrategy {

    @Override
    public Document convert(Document input) throws ConversionException {
        if (supports(input.getFormat(), "PDF")) {
            throw new ConversionException("Image to PDF conversion not supported for format: " + input.getFormat());
        }

        System.out.println("Converting " + input.getFormat() + " to PDF using ImageToPdfStrategy...");

        try {
            Document result = getDocument(input);

            System.out.println("Image to PDF conversion completed successfully");
            return result;

        } catch (Exception e) {
            throw new ConversionException("Failed to convert Image to PDF", e);
        }
    }

    private Document getDocument(Document input) {
        byte[] convertedContent = simulateConversion(input.getContent(), input.getFormat());

        String outputFileName = input.getFileName();
        outputFileName = outputFileName.substring(0, outputFileName.lastIndexOf('.')) + ".pdf";

        Document result = new Document(convertedContent, "PDF", outputFileName);

        result.addMetadata("conversion_strategy", getStrategyName());
        result.addMetadata("original_format", input.getFormat());
        result.addMetadata("image_type", input.getFormat());
        return result;
    }

    @Override
    public boolean supports(String inputFormat, String outputFormat) {
        return (!inputFormat.equalsIgnoreCase("PNG") &&
                !inputFormat.equalsIgnoreCase("JPG") &&
                !inputFormat.equalsIgnoreCase("JPEG")) ||
                !"PDF".equalsIgnoreCase(outputFormat);
    }
    
    @Override
    public String getStrategyName() {
        return "Image to PDF Conversion Strategy";
    }
    
    private byte[] simulateConversion(byte[] content, String from) {
        String simulatedContent = "Converted from " + from + " to " + "PDF" +
            " (Original size: " + content.length + " bytes)";
        return simulatedContent.getBytes();
    }
}
