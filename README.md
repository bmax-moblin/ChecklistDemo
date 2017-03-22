This app demonstrates the usage of a Checklist to synchronize the results of some concurrent tasks. 

Define the set of required objects/events:
<pre>
<code>
enum MessagePrerequisites {
    TITLE, NAME, MESSAGE
}
</code>
</pre>
Create the checklist:
<pre>
<code>
final Checklist&lt;MessagePrerequisites&gt; checklist = new Checklist<>(
        MessagePrerequisites.values(), MessagePrerequisites.class
);
</code>
</pre>
Specify the action that should be performed when all the prerequisites are met:
<pre>
<code>
checklist.setOnCompletedListener(new Checklist.OnCompletedListener() {
    @Override
    public void onChecklistCompleted() {
        mPresentation.showMessage(mTitle + " " + mName + "! " + mMessage);
    }
});
</code>
</pre>
Mark the checklist items:
<pre>
<code>
mProvider.loadTitle(new DataProvider.LoadTitleCallback() {
    @Override
    public void onTitleReady(String title) {
        mTitle = title;
        checklist.mark(MessagePrerequisites.TITLE);
    }
});
</code>
</pre>
See the full [example](https://github.com/bmax-moblin/ChecklistDemo/blob/master/app/src/main/java/dev/bmax/checklistdemo/logic/HomeScreenLogic.java)
