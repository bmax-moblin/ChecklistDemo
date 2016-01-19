package dev.bmax.checklistdemo.data;

import android.os.AsyncTask;

import dev.bmax.checklistdemo.abs.DataProvider;

/**
 * Prepares data asynchronously and returns to the provided callback.
 */
public class AsyncMessageProvider implements DataProvider {
    @Override
    public void loadTitle(final LoadTitleCallback callback) {
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                /* Hard work would be done here. */
                return "Agent";
            }

            @Override
            protected void onPostExecute(String result) {
                callback.onTitleReady(result);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void loadName(final LoadNameCallback callback) {
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                /* Hard work would be done here. */
                return "Bond";
            }

            @Override
            protected void onPostExecute(String result) {
                callback.onNameReady(result);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void loadMessage(final LoadMessageCallback callback) {
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                /* Hard work would be done here. */
                return "Your shaken, not stirred martini is ready.";
            }

            @Override
            protected void onPostExecute(String result) {
                callback.onMessageReady(result);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
