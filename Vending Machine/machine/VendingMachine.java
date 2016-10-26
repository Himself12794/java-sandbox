package machine;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class VendingMachine implements CoinSlot, Keypad, Display, ItemDispenser, ReturnButton {

    private float total = 0.0F;

    private String displayStr;

    public void insertCoin(Coin coin) {
        total += coin.getValue();
    }

    public String getView() {
        return displayStr;
    }

    public void setView(String value) {
        displayStr = value;
    }

    public float getTotal() {
        return total;
    }

    public Item receiveItem() {
        return null;
    }

    public boolean canDispenseItem() {
        return false;
    }

    public Map<Coin, Integer> returnChange() {
        return new HashMap<Coin, Integer>();
    }

    public void putSelection(char a, short b) {

    }

    public void submitSelection() {

    }

}
