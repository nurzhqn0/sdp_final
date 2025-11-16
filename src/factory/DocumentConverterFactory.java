package factory;

import strategy.ConversionStrategy;
import strategy.PdfToDocxStrategy;
import strategy.DocxToTxtStrategy;

public class DocumentConverterFactory extends ConverterFactory {
    
    @Override
    public ConversionStrategy createConverter(String inputFormat, String outputFormat) {
        System.out.println("DocumentConverterFactory: Creating converter for " + 
            inputFormat + " -> " + outputFormat);
        
        if ("PDF".equalsIgnoreCase(inputFormat) && "DOCX".equalsIgnoreCase(outputFormat)) {
            return new PdfToDocxStrategy();
        } 
        else if ("DOCX".equalsIgnoreCase(inputFormat) && "TXT".equalsIgnoreCase(outputFormat)) {
            return new DocxToTxtStrategy();
        }
        
        throw new UnsupportedOperationException(
            "Document conversion from " + inputFormat + " to " + outputFormat + " is not supported"
        );
    }
    
    @Override
    public boolean supportsConversion(String inputFormat, String outputFormat) {
        return ("PDF".equalsIgnoreCase(inputFormat) && "DOCX".equalsIgnoreCase(outputFormat)) ||
               ("DOCX".equalsIgnoreCase(inputFormat) && "TXT".equalsIgnoreCase(outputFormat));
    }
}
