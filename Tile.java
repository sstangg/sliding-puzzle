public class Tile {
    private final int x;
    private final int y;
    private int currentValue;
    private int correctValue;

    // Tile constructor
    public Tile(int x, int y, int currentValue) {
        this.x = x;
        this.y = y;
        this.currentValue = currentValue;
    }

    public boolean checkCorrect() {
        return currentValue == correctValue;
    }

    // GETTERS
    public int getCurrentValue() {
        return this.currentValue;
    }
    public int getCorrectValue() {
        return this.correctValue;
    }
    public int getRow() {
        return this.x;
    }
    public int getHeight() {
        return this.y;
    }

    // SETTERS
    public void setCurrentValue(int num) {
        this.currentValue = num;
    }
    public void setCorrectValue(int num) {
        this.correctValue = num;
    }


}
