package values;

public class Card {
    private int number;
    private boolean face;

    public Card(int number, boolean face) {
        this.number = number;
        this.face = face;
    }

    public Card(Card other){
        this.number = other.number;
        this.face = other.face;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFace() {
        return face;
    }

    public void setFace(boolean face) {
        this.face = face;
    }
}
