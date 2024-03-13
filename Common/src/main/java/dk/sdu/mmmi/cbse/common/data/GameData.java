package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int time = 0;

    private long timeInMilli = System.currentTimeMillis();


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getTimeInMilli() {
        return timeInMilli;
    }

    public void setTimeInMilli(long timeInMilli) {
        this.timeInMilli = timeInMilli;
    }
}
