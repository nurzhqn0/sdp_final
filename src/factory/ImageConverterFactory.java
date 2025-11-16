package factory;

import strategy.ConversionStrategy;
import strategy.ImageToPdfStrategy;

public class ImageConverterFactory extends ConverterFactory {
    
    @Override
    public ConversionStrategy createConverter(String inputFormat, String outputFormat) {
        System.out.println("ImageConverterFactory: Creating converter for " + 
            inputFormat + " -> " + outputFormat);
        
        if (isImageFormat(inputFormat) && "PDF".equalsIgnoreCase(outputFormat)) {
            return new ImageToPdfStrategy();
        }
        
        throw new UnsupportedOperationException(
            "Image conversion from " + inputFormat + " to " + outputFormat + " is not supported"
        );
    }
    
    @Override
    public boolean supportsConversion(String inputFormat, String outputFormat) {
        return isImageFormat(inputFormat) && "PDF".equalsIgnoreCase(outputFormat);
    }
    
    private boolean isImageFormat(String format) {
        return "PNG".equalsIgnoreCase(format) || 
               "JPG".equalsIgnoreCase(format) || 
               "JPEG".equalsIgnoreCase(format);
    }
}
