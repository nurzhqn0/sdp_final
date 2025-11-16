package strategy;

import model.Document;
import util.ConversionException;

public interface ConversionStrategy {
    Document convert(Document input) throws ConversionException;

    boolean supports(String inputFormat, String outputFormat);

    String getStrategyName();
}
