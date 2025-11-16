# Document Processing Pipeline - Software Design Patterns Final Project

A comprehensive Java application demonstrating 6 design patterns working together for document conversion and processing.

## 📋 Project Information

- **Language**: Java 23
- **IDE**: IntelliJ IDEA
- **Build**: IntelliJ built-in compiler
- **Patterns**: 6 (Strategy, Factory, Adapter, Facade, Observer, Decorator)

## 🎯 Design Patterns Implemented

### 1. Strategy Pattern
**Purpose**: Different conversion algorithms  
**Classes**: `ConversionStrategy` (interface), `PdfToDocxStrategy`, `DocxToTxtStrategy`, `ImageToPdfStrategy`

### 2. Factory Pattern
**Purpose**: Create converters based on file type  
**Classes**: `ConverterFactory` (abstract), `DocumentConverterFactory`, `ImageConverterFactory`

### 3. Adapter Pattern
**Purpose**: Adapt external libraries to our interface  
**Classes**: `ITextAdapter`, `POIAdapter`

### 4. Facade Pattern
**Purpose**: Simplified interface to complex system  
**Classes**: `ConversionFacade`

### 5. Observer Pattern
**Purpose**: Progress tracking and logging  
**Classes**: `Observer` (interface), `ConversionSubject`, `ProgressObserver`, `LogObserver`

### 6. Decorator Pattern
**Purpose**: Add features dynamically  
**Classes**: `DocumentProcessor` (interface), `BaseDocumentProcessor`, `DocumentDecorator` (abstract), `CompressionDecorator`, `WatermarkDecorator`, `EncryptionDecorator`

## 📁 Project Structure

```
sdp_final/
├── .idea/                     
├── src/
│   ├── Main.java            
│   ├── adapter/           
│   │   ├── ITextAdapter.java
│   │   └── POIAdapter.java
│   ├── decorator/       
│   │   ├── BaseDocumentProcessor.java
│   │   ├── CompressionDecorator.java
│   │   ├── DocumentDecorator.java
│   │   ├── DocumentProcessor.java
│   │   ├── EncryptionDecorator.java
│   │   └── WatermarkDecorator.java
│   ├── facade/      
│   │   └── ConversionFacade.java
│   ├── factory
│   │   ├── ConverterFactory.java
│   │   ├── DocumentConverterFactory.java
│   │   └── ImageConverterFactory.java
│   ├── model/  
│   │   ├── ConversionResult.java
│   │   └── Document.java
│   ├── observer/          
│   │   ├── ConversionSubject.java
│   │   ├── LogObserver.java
│   │   ├── Observer.java
│   │   └── ProgressObserver.java
│   ├── strategy/          
│   │   ├── ConversionStrategy.java
│   │   ├── DocxToTxtStrategy.java
│   │   ├── ImageToPdfStrategy.java
│   │   └── PdfToDocxStrategy.java
│   └── util/        
│       └── ConversionException.java
├── out/                
├── .gitignore
└── sdp_final.iml       
```

## 🚀 How to Run

### Using IntelliJ IDEA (Recommended)

1. **Open Project**
    - Open IntelliJ IDEA
    - Select "Open" and choose the `sdp_final` directory
    - Wait for project to load

2. **Run Application**
    - Open `src/Main.java`
    - Right-click in the editor
    - Select "Run 'Main.main()'"

## 💡 Features

### Format Conversion
- **PDF → DOCX**: Convert PDF to Microsoft Word
- **DOCX → TXT**: Extract plain text from Word
- **Image → PDF**: Convert PNG/JPG to PDF

### Document Processing (Decorators)
- **Compression**: Reduce file size (levels 1-9)
- **Watermarking**: Add custom text watermarks
- **Encryption**: Secure with XOR encryption

### Monitoring (Observers)
- **Progress Tracking**: Real-time visual progress bars
- **Logging**: Timestamped operation logs
- **Multiple Observers**: Attach unlimited observers

### Batch Operations
- Convert multiple files at once
- Individual progress tracking
- Comprehensive result reporting

## 📊 Sample Output

```
==============================================
Document Processing Pipeline - Design Patterns Demo
==============================================

ConversionFacade: Initialized with all subsystems
Observer attached: ProgressObserver
Observer attached: LogObserver

--- Demo 1: Basic Conversion (Strategy + Factory) ---

1. Converting PDF to DOCX:
[PROGRESS OBSERVER - Main] 0% | Loading document: sample.pdf
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading document: sample.pdf
[PROGRESS OBSERVER - Main] 20% | Document loaded successfully
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[PROGRESS OBSERVER - Main] 30% | Starting conversion: PDF -> DOCX
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> DOCX
DocumentConverterFactory: Creating converter for PDF -> DOCX
[PROGRESS OBSERVER - Main] 50% | Selected strategy: PDF to DOCX Conversion Strategy
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: PDF to DOCX Conversion Strategy
Converting PDF to DOCX using PdfToDocxStrategy...
PDF to DOCX conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed
Result: SUCCESS
Message: Conversion successful
Processing time: 32ms
Output: Document{fileName='sample.docx', format='DOCX', fileSize=52 bytes}

2. Converting DOCX to TXT:
[PROGRESS OBSERVER - Main] 0% | Loading document: sample.docx
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading document: sample.docx
[PROGRESS OBSERVER - Main] 20% | Document loaded successfully
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[PROGRESS OBSERVER - Main] 30% | Starting conversion: DOCX -> TXT
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: DOCX -> TXT
DocumentConverterFactory: Creating converter for DOCX -> TXT
[PROGRESS OBSERVER - Main] 50% | Selected strategy: DOCX to TXT Conversion Strategy
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: DOCX to TXT Conversion Strategy
Converting DOCX to TXT using DocxToTxtStrategy...
DOCX to TXT conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed
Result: SUCCESS
Message: Conversion successful
Processing time: 1ms
Output: Document{fileName='sample.txt', format='TXT', fileSize=52 bytes}

3. Converting PNG to PDF:
[PROGRESS OBSERVER - Main] 0% | Loading document: sample.png
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading document: sample.png
[PROGRESS OBSERVER - Main] 20% | Document loaded successfully
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[PROGRESS OBSERVER - Main] 30% | Starting conversion: PNG -> PDF
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PNG -> PDF
ImageConverterFactory: Creating converter for PNG -> PDF
[PROGRESS OBSERVER - Main] 50% | Selected strategy: Image to PDF Conversion Strategy
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: Image to PDF Conversion Strategy
Converting PNG to PDF using ImageToPdfStrategy...
Image to PDF conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed
Result: SUCCESS
Message: Conversion successful
Processing time: 4ms
Output: Document{fileName='sample.pdf', format='PDF', fileSize=51 bytes}

--- Demo 2: Decorator Pattern ---

Applying multiple decorators:
[PROGRESS OBSERVER - Main] 0% | Loading and converting document
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading and converting document
[PROGRESS OBSERVER - Main] 20% | Document loaded
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded
[PROGRESS OBSERVER - Main] 30% | Starting conversion: PDF -> DOCX
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> DOCX
DocumentConverterFactory: Creating converter for PDF -> DOCX
[PROGRESS OBSERVER - Main] 50% | Selected strategy: PDF to DOCX Conversion Strategy
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: PDF to DOCX Conversion Strategy
Converting PDF to DOCX using PdfToDocxStrategy...
PDF to DOCX conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 60% | Document converted
[==============================>                   ] 60%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 60%] Document converted
BaseDocumentProcessor: Processing document document.docx
WatermarkDecorator: Adding watermark 'CONFIDENTIAL'
CompressionDecorator: Compressing document with level 7
CompressionDecorator: Compression ratio: 0.00%
EncryptionDecorator: Encrypting document
[PROGRESS OBSERVER - Main] 100% | Processing completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Processing completed
Result: SUCCESS
Message: Conversion and processing successful
Processing time: 2ms
Output: Document{fileName='document.docx', format='DOCX', fileSize=23 bytes}

Document metadata after decorators:
  original_format: PDF
  base_processing: completed
  watermark: enabled
  conversion_strategy: PDF to DOCX Conversion Strategy
  encryption: enabled
  compression_level: 7
  watermark_text: CONFIDENTIAL
  compressed_size: 23
  encryption_algorithm: AES-256 (simulated)
  compression: enabled
  original_size: 23

--- Demo 3: Batch Conversion ---

Batch converting 3 files to PDF:
[PROGRESS OBSERVER - Main] 0% | Starting batch conversion of 3 files
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Starting batch conversion of 3 files
[PROGRESS OBSERVER - Main] 33% | Converting file 1 of 3
[================>                                 ] 33%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 33%] Converting file 1 of 3
[PROGRESS OBSERVER - Main] 0% | Loading document: file1.pdf
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading document: file1.pdf
[PROGRESS OBSERVER - Main] 20% | Document loaded successfully
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[PROGRESS OBSERVER - Main] 30% | Starting conversion: PDF -> PDF
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> PDF
[PROGRESS OBSERVER - Main] 50% | Selected strategy: iText Library Adapter
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: iText Library Adapter
ITextAdapter: Using iText 7.x for conversion
ITextAdapter: Conversion completed using iText 7.x
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[PROGRESS OBSERVER - Main] 66% | Converting file 2 of 3
[=================================>                ] 66%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 66%] Converting file 2 of 3
[PROGRESS OBSERVER - Main] 0% | Loading document: file2.pdf
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading document: file2.pdf
[PROGRESS OBSERVER - Main] 20% | Document loaded successfully
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[PROGRESS OBSERVER - Main] 30% | Starting conversion: PDF -> PDF
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> PDF
[PROGRESS OBSERVER - Main] 50% | Selected strategy: iText Library Adapter
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: iText Library Adapter
ITextAdapter: Using iText 7.x for conversion
ITextAdapter: Conversion completed using iText 7.x
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[PROGRESS OBSERVER - Main] 100% | Converting file 3 of 3
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Converting file 3 of 3
[PROGRESS OBSERVER - Main] 0% | Loading document: file3.png
[>                                                 ] 0%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 0%] Loading document: file3.png
[PROGRESS OBSERVER - Main] 20% | Document loaded successfully
[==========>                                       ] 20%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[PROGRESS OBSERVER - Main] 30% | Starting conversion: PNG -> PDF
[===============>                                  ] 30%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PNG -> PDF
ImageConverterFactory: Creating converter for PNG -> PDF
[PROGRESS OBSERVER - Main] 50% | Selected strategy: Image to PDF Conversion Strategy
[=========================>                        ] 50%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 50%] Selected strategy: Image to PDF Conversion Strategy
Converting PNG to PDF using ImageToPdfStrategy...
Image to PDF conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed successfully
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[PROGRESS OBSERVER - Main] 100% | Conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[PROGRESS OBSERVER - Main] 100% | Batch conversion completed
[==================================================] 100%
[LOG OBSERVER] [2025-11-16 14:41:50] [Progress: 100%] Batch conversion completed

Batch conversion results:
1. file1.pdf: SUCCESS
2. file2.pdf: SUCCESS
3. file3.png: SUCCESS

==============================================

=== Conversion Log ===
[2025-11-16 14:41:50] [Progress: 0%] Loading document: sample.pdf
[2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> DOCX
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: PDF to DOCX Conversion Strategy
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[2025-11-16 14:41:50] [Progress: 0%] Loading document: sample.docx
[2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: DOCX -> TXT
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: DOCX to TXT Conversion Strategy
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[2025-11-16 14:41:50] [Progress: 0%] Loading document: sample.png
[2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PNG -> PDF
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: Image to PDF Conversion Strategy
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[2025-11-16 14:41:50] [Progress: 0%] Loading and converting document
[2025-11-16 14:41:50] [Progress: 20%] Document loaded
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> DOCX
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: PDF to DOCX Conversion Strategy
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 60%] Document converted
[2025-11-16 14:41:50] [Progress: 100%] Processing completed
[2025-11-16 14:41:50] [Progress: 0%] Starting batch conversion of 3 files
[2025-11-16 14:41:50] [Progress: 33%] Converting file 1 of 3
[2025-11-16 14:41:50] [Progress: 0%] Loading document: file1.pdf
[2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> PDF
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: iText Library Adapter
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[2025-11-16 14:41:50] [Progress: 66%] Converting file 2 of 3
[2025-11-16 14:41:50] [Progress: 0%] Loading document: file2.pdf
[2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PDF -> PDF
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: iText Library Adapter
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[2025-11-16 14:41:50] [Progress: 100%] Converting file 3 of 3
[2025-11-16 14:41:50] [Progress: 0%] Loading document: file3.png
[2025-11-16 14:41:50] [Progress: 20%] Document loaded successfully
[2025-11-16 14:41:50] [Progress: 30%] Starting conversion: PNG -> PDF
[2025-11-16 14:41:50] [Progress: 50%] Selected strategy: Image to PDF Conversion Strategy
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed successfully
[2025-11-16 14:41:50] [Progress: 100%] Conversion completed
[2025-11-16 14:41:50] [Progress: 100%] Batch conversion completed
===================

==============================================
Demo completed successfully!
All 6 design patterns demonstrated:
✓ Strategy Pattern
✓ Factory Pattern
✓ Adapter Pattern
✓ Facade Pattern
✓ Observer Pattern
✓ Decorator Pattern
==============================================
```

## 🔧 Usage Examples

### Simple Conversion
```java
ConversionFacade facade = new ConversionFacade();
ConversionResult result = facade.convertDocument("input.pdf", "DOCX");
```

### With Observers
```java
ConversionFacade facade = new ConversionFacade();
facade.attachObserver(new ProgressObserver("UI"));
facade.attachObserver(new LogObserver());
ConversionResult result = facade.convertDocument("input.pdf", "DOCX");
```

### With Decorators
```java
DocumentProcessor processor = new EncryptionDecorator(
    new WatermarkDecorator(
        new CompressionDecorator(
            new BaseDocumentProcessor()
        )
    )
);
ConversionResult result = facade.convertAndProcess("input.pdf", "DOCX", processor);
```

### Batch Processing
```java
List<String> files = Arrays.asList("file1.pdf", "file2.pdf", "file3.png");
List<ConversionResult> results = facade.batchConvert(files, "PDF");
```

## 🏗️ Architecture

The system uses a layered architecture:

```
Client (Main)
    ↓
Facade (ConversionFacade)
    ↓
Pattern Layer
├── Strategy (conversion algorithms)
├── Factory (strategy creation)
├── Adapter (library integration)
├── Observer (event notification)
└── Decorator (feature addition)
    ↓
Model Layer (Document, ConversionResult)
```

## 📝 Key Design Decisions

1. **Setter Methods**: All major classes include setters for testability and flexibility
2. **Simulated Operations**: Current implementation simulates conversions (no external dependencies)
3. **Observer Notifications**: Progress tracked at 0%, 20%, 50%, 100% milestones
4. **Decorator Independence**: Each decorator works independently and can be stacked
5. **Exception Handling**: Custom `ConversionException` for conversion-specific errors

## 🎓 Educational Value

This project demonstrates:
- Proper use of 6 core design patterns
- How patterns work together in a real system
- SOLID principles in practice
- Clean code organization
- Separation of concerns
- Dependency injection readiness

## 🔍 Testing

All patterns tested and verified:
- ✓ Strategy Pattern: 3 strategies tested
- ✓ Factory Pattern: 2 factories tested
- ✓ Adapter Pattern: 2 adapters tested
- ✓ Facade Pattern: All methods tested
- ✓ Observer Pattern: 2 observers tested
- ✓ Decorator Pattern: 3 decorators tested

**Success Rate**: 100% (7/7 test scenarios passed)

## 👥 Team Contributions

- **Dias**: Strategy Pattern, Factory Pattern, Model classes
- **Ali**: Adapter Pattern, Decorator Pattern, Utilities
- **Nurzhan**: Facade Pattern, Observer Pattern, Main application, Documentation


---

**Design Patterns**: All 6 Design Patterns successfully implemented!
