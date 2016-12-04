package dev.bmax.checklistdemo.abs;

/**
 * The home screen's abstract interface.
 */
public interface HomeScreen {
    /**
     * Presentation-tier commands.
     */
    interface Presentation {
        void showMessage(String message);
    }

    /**
     * Logic-tier messages.
     */
    interface Logic {
        void actionButtonPressed();
    }
}
