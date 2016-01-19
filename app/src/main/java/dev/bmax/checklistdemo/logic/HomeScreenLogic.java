package dev.bmax.checklistdemo.logic;

import dev.bmax.checklistdemo.abs.DataProvider;
import dev.bmax.checklistdemo.abs.HomeScreen;
import dev.bmax.checklistdemo.util.Checklist;

/**
 * Concrete logic-tier implementation for the home screen.
 */
public class HomeScreenLogic implements HomeScreen.Logic {
    public HomeScreenLogic(HomeScreen.Presentation presentation, DataProvider provider) {
        this.mPresentation = presentation;
        this.mProvider = provider;
    }

    enum MessagePrerequisites {
        TITLE, NAME, MESSAGE
    }

    @Override
    public void actionButtonPressed() {
        final Checklist<MessagePrerequisites> checklist = new Checklist<>(
                MessagePrerequisites.values(), MessagePrerequisites.class
        );

        checklist.setOnCompletedListener(new Checklist.OnCompletedListener() {
            @Override
            public void onChecklistCompleted() {
                mPresentation.showMessage(mTitle + " " + mName + "! " + mMessage);
            }
        });

        mProvider.loadTitle(new DataProvider.LoadTitleCallback() {
            @Override
            public void onTitleReady(String title) {
                mTitle = title;
                checklist.mark(MessagePrerequisites.TITLE);
            }
        });

        mProvider.loadName(new DataProvider.LoadNameCallback() {
            @Override
            public void onNameReady(String name) {
                mName = name;
                checklist.mark(MessagePrerequisites.NAME);
            }
        });

        mProvider.loadMessage(new DataProvider.LoadMessageCallback() {
            @Override
            public void onMessageReady(String message) {
                mMessage = message;
                checklist.mark(MessagePrerequisites.MESSAGE);
            }
        });
    }

    /**
     * Private property.
     */
    private HomeScreen.Presentation mPresentation;
    private DataProvider mProvider;
    private String mTitle, mName, mMessage;
}
