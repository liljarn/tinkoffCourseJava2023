package edu.hw1.task1;

public final class VideoLength {
    private final static int SECONDS = 60;

    private VideoLength() {
    }

    public static int minutesToSeconds(String time) throws NumberFormatException {
        int timeInSeconds;
        if (time != null) {
            String[] splitTime = time.split(":");
            if (splitTime.length == 2 && splitTime[0].length() > 1 && splitTime[1].length() == 2) {
                try {
                    int minutes = Integer.parseInt(splitTime[0]);
                    int seconds = Integer.parseInt(splitTime[1]);
                    if (minutes >= 0 && seconds >= 0 && seconds < SECONDS) {
                        timeInSeconds = minutes * SECONDS + seconds;
                        return timeInSeconds;
                    }
                    return -1;
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }
        return -1;
    }
}
