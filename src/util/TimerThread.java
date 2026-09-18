package util;

public class TimerThread extends Thread {
    private int remainingSeconds;
    private volatile boolean timeUp = false;

    public TimerThread(int seconds) {
        this.remainingSeconds = seconds;
    }

    @Override
    public void run() {
        try {
            while (remainingSeconds > 0) {
                Thread.sleep(1000);
                remainingSeconds--;

                if (remainingSeconds <= 10) {
                    System.out.println("\nTime remaining: " + remainingSeconds + " seconds");
                }
            }
            timeUp = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isTimeUp() {
        return timeUp;
    }

    public void stopTimer() {
        interrupt();
    }
}
