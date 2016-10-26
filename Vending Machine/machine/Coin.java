package machine;

public enum Coin {

    PENNY(0.01F), NICKEL(0.05F), DIME(0.1F), QUARTER(0.25F);

    private final float value;

    Coin(float val) {
        value = val;
    }

    public float getValue() { return value; }

}
