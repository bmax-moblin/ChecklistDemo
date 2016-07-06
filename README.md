This app demonstrates the usage of a Checklist to synchronize the results of some concurrent tasks. 

Define the set of required objects/events:
<code><pre>
enum MessagePrerequisites {
  TITLE, NAME, MESSAGE
}
</pre></code>

Create the checklist:
<code><pre>
final Checklist\<MessagePrerequisites\> checklist = new Checklist<>(
        MessagePrerequisites.values(), MessagePrerequisites.class
);
</pre></code>

Specify the action that should be performed when all the prerequisites are met:
<code><pre>
checklist.setOnCompletedListener(new Checklist.OnCompletedListener() {
    @Override
    public void onChecklistCompleted() {
        mPresentation.showMessage(mTitle + " " + mName + "! " + mMessage);
    }
});
</pre></code>

Mark the checklist items:
<code><pre>
mProvider.loadTitle(new DataProvider.LoadTitleCallback() {
    @Override
    public void onTitleReady(String title) {
        mTitle = title;
        checklist.mark(MessagePrerequisites.TITLE);
    }
});
</pre></code>

See the full example here: https://github.com/bmax-moblin/ChecklistDemo/blob/master/app/src/main/java/dev/bmax/checklistdemo/logic/HomeScreenLogic.java
