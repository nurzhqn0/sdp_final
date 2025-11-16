package observer;

import java.util.ArrayList;
import java.util.List;

public class ConversionSubject {
    
    private List<Observer> observers;
    private String currentMessage;
    private int currentProgress;
    
    public ConversionSubject() {
        this.observers = new ArrayList<>();
        this.currentProgress = 0;
        this.currentMessage = "";
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer attached: " + observer.getClass().getSimpleName());
        }
    }

    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer detached: " + observer.getClass().getSimpleName());
    }

    public void notifyObservers(String message, int progress) {
        this.currentMessage = message;
        this.currentProgress = progress;
        
        for (Observer observer : observers) {
            observer.update(message, progress);
        }
    }

    public void notifyObservers(String message) {
        notifyObservers(message, this.currentProgress);
    }

    public void updateProgress(int progress) {
        notifyObservers(this.currentMessage, progress);
    }
    
    public String getCurrentMessage() {
        return currentMessage;
    }
    
    public int getCurrentProgress() {
        return currentProgress;
    }
    
    public int getObserverCount() {
        return observers.size();
    }
}
