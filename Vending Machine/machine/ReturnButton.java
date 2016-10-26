package machine;

import java.util.Map;
import java.util.HashMap;

public interface ReturnButton {

    Map<Coin, Integer> returnChange();

    float getTotal();

    default Map<Coin,Integer> amountToCoins() {
        Map<Coin, Integer> coins = new HashMap<Coin, Integer>();

        int total = (int)(getTotal() * 100);

        int qCount = total / 25;
        total -= qCount * 25;

        int dCount = total / 10;
        total -= dCount * 10;

        int nCount = total / 5;
        total -= nCount * 5;

        int pCount = total;

        coins.put(Coin.QUARTER, qCount);
        coins.put(Coin.DIME, dCount);
        coins.put(Coin.NICKEL, nCount);
        coins.put(Coin.PENNY, pCount);

        return coins;
    }

}
