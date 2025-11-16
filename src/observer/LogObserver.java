package observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogObserver implements Observer {
    
    private List<String> logEntries;
    private DateTimeFormatter formatter;

    public LogObserver() {
        this.logEntries = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void setLogEntries(List<String> logEntries) {
        this.logEntries = logEntries;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void update(String message, int progress) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] [Progress: %d%%] %s", 
            timestamp, progress, message);
        
        logEntries.add(logEntry);
        System.out.println("[LOG OBSERVER] " + logEntry);

    }

    public List<String> getLogEntries() {
        return new ArrayList<>(logEntries);
    }

    public void printLogs() {
        System.out.println("\n=== Conversion Log ===");
        for (String entry : logEntries) {
            System.out.println(entry);
        }
        System.out.println("===================\n");
    }

    public void clearLogs() {
        logEntries.clear();
        System.out.println("[LOG OBSERVER] Logs cleared");
    }
}
