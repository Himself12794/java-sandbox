package machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Keeps track of what type of items the vending machine has stored, can store,
 * and current values.
 */
public class ItemStorage {

    private int totalSlots;

    private int maxSlotCount;

    private final Set<Item> itemTypes;

    private final Map<Item, Integer> currentInventory = new HashMap<>();

    public ItemStorage(int _totalSlots, int _maxSlotCount) {
        this(_totalSlots, _maxSlotCount, new HashSet<Item>());
    }

    public ItemStorage(int _totalSlots, int _maxSlotCount, Set<Item> _itemTypes) {
        totalSlots = _totalSlots;
        maxSlotCount = _maxSlotCount;
        itemTypes = _itemTypes;
    }

    public boolean registerItem(Item item) {
        return itemTypes.add(item);
    }

    public Optional<Integer> getItemCountFor(Item item) {
        return !itemTypes.contains(item) ? Optional.empty() : Optional.of((int)currentInventory.getOrDefault(item, 0));
    }

    public Optional<Item> retrieveOne(Item item) {

        Optional<Integer> amount = getItemCountFor(item);
        int total = amount.orElse(0);
        if (amount.isPresent() && total > 0) {
            currentInventory.put(item, total - 1);
            return Optional.of(item);
        } else {
            return Optional.empty();
        }

    }

}
