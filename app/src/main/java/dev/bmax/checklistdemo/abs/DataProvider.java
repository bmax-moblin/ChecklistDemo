package dev.bmax.checklistdemo.abs;

/**
 * This abstract interface defines the way, the logic tier should
 * request data from the data tier.
 */
public interface DataProvider {
    void loadTitle(LoadTitleCallback callback);

    interface LoadTitleCallback {
        void onTitleReady(String title);
    }

    void loadName(LoadNameCallback callback);

    interface LoadNameCallback {
        void onNameReady(String name);
    }

    void loadMessage(LoadMessageCallback callback);

    interface LoadMessageCallback {
        void onMessageReady(String message);
    }
}
