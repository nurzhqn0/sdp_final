package factory;

import strategy.ConversionStrategy;

public abstract class ConverterFactory {
    public abstract ConversionStrategy createConverter(String inputFormat, String outputFormat);

    public abstract boolean supportsConversion(String inputFormat, String outputFormat);
}
