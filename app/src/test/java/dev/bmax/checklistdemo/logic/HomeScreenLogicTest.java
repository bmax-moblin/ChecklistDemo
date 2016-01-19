package dev.bmax.checklistdemo.logic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import dev.bmax.checklistdemo.abs.DataProvider;
import dev.bmax.checklistdemo.abs.HomeScreen;

public class HomeScreenLogicTest {
    public static final String TEST_TITLE = "test_title";
    public static final String TEST_NAME = "test_name";
    public static final String TEST_MESSAGE = "test_message";
    public static final String TEST_RESULT = TEST_TITLE + " " + TEST_NAME + "! " + TEST_MESSAGE;

    @Mock
    HomeScreen.Presentation mPresentation;

    @Mock
    DataProvider mProvider;

    @Captor
    private ArgumentCaptor<DataProvider.LoadTitleCallback> mLoadTitleCallbackCaptor;

    @Captor
    private ArgumentCaptor<DataProvider.LoadNameCallback> mLoadNameCallbackCaptor;

    @Captor
    private ArgumentCaptor<DataProvider.LoadMessageCallback> mLoadMessageCallbackCaptor;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mHomeScreenLogic = new HomeScreenLogic(mPresentation, mProvider);
    }

    @Test
    public void pressing_action_button_shows_message() throws Exception {
        mHomeScreenLogic.actionButtonPressed();

        verify(mProvider).loadTitle(mLoadTitleCallbackCaptor.capture());
        verify(mProvider).loadName(mLoadNameCallbackCaptor.capture());
        verify(mProvider).loadMessage(mLoadMessageCallbackCaptor.capture());

        mLoadNameCallbackCaptor.getValue().onNameReady(TEST_NAME);
        mLoadMessageCallbackCaptor.getValue().onMessageReady(TEST_MESSAGE);
        mLoadTitleCallbackCaptor.getValue().onTitleReady(TEST_TITLE);

        verify(mPresentation).showMessage(TEST_RESULT);
    }

    /**
     * Private property.
     */
    private HomeScreenLogic mHomeScreenLogic;
}