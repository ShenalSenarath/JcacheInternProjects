package me.shenalsenarath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shselk on 11/17/2014.
 */
public class CustomModelDAO {
    private final Map<Integer, CustomModel> customModelsCollection = new ConcurrentHashMap<Integer, CustomModel>();

    public Collection<Integer> allUserIds() {
        return new ArrayList<Integer>(customModelsCollection.keySet()); }

    public CustomModel findById(int id) {
        CustomModel result = customModelsCollection.get(id);
        if (result != null) {
            // Add latency to show the caching effect
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignore) {
            }
            return result;
        }
        throw new IllegalArgumentException("User not found");
    }

    public void storeCustomModel(int id, CustomModel model) {
        customModelsCollection.put(id, model);
    }

    public void removeUser(int id) {
        customModelsCollection.remove(id);
    }

}
