package observer;

public class ProgressObserver implements Observer {
    
    private String name;
    
    public ProgressObserver(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String message, int progress) {
        System.out.println("[PROGRESS OBSERVER - " + name + "] " + progress + "% | " + message);

        displayProgressBar(progress);
    }

    private void displayProgressBar(int progress) {
        int barLength = 50;
        int filled = (int) (barLength * progress / 100.0);
        
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                bar.append("=");
            } else if (i == filled) {
                bar.append(">");
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(progress).append("%");
        
        System.out.println(bar);
    }
}
