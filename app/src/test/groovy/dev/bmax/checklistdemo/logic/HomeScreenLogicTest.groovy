package dev.bmax.checklistdemo.logic

import dev.bmax.checklistdemo.abs.DataProvider
import dev.bmax.checklistdemo.abs.HomeScreen
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations

@CompileStatic
class HomeScreenLogicTest {
    String TITLE = 'test_title'
    String NAME = 'test_name'
    String MESSAGE = 'test_message'
    String RESULT = "${TITLE} ${NAME}! ${MESSAGE}"

    @Mock
    HomeScreen.Presentation presentation

    @Mock
    DataProvider provider;

    @Captor
    ArgumentCaptor<DataProvider.LoadTitleCallback> loadTitleCallbackCaptor

    @Captor
    ArgumentCaptor<DataProvider.LoadNameCallback> loadNameCallbackCaptor

    @Captor
    ArgumentCaptor<DataProvider.LoadMessageCallback> loadMessageCallbackCaptor

    HomeScreenLogic homeScreenLogic

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
        homeScreenLogic = new HomeScreenLogic(presentation, provider)
    }

    @Test
    void pressing_action_button_shows_message() {
        homeScreenLogic.actionButtonPressed()

        verify(provider).loadTitle(loadTitleCallbackCaptor.capture())
        verify(provider).loadName(loadNameCallbackCaptor.capture())
        verify(provider).loadMessage(loadMessageCallbackCaptor.capture())

        loadNameCallbackCaptor.value.onNameReady(NAME)
        loadMessageCallbackCaptor.value.onMessageReady(MESSAGE)
        loadTitleCallbackCaptor.value.onTitleReady(TITLE)

        verify(presentation).showMessage(RESULT)
    }

}
