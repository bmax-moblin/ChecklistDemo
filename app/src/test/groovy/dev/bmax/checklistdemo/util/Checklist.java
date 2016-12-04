package dev.bmax.checklistdemo.util;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * This class helps to ensure consistency and completeness in carrying out a task.
 * List items are represented by an Enum. An optional callback may be provided,
 * that will be invoked when all fields have been ticked off.
 */
public class Checklist<T extends Enum> {
    public interface OnCompletedListener {
        void onChecklistCompleted();
    }

    /**
     * @param allFields - the values of the underlying Enum.
     * @param clazz - the class of the underlying Enum.
     */
    public Checklist(T[] allFields, Class<T> clazz) {
        this.mAllFields = allFields;
        this.mMarkedFields = Collections.synchronizedSet(EnumSet.noneOf(clazz));
    }

    /**
     * Marks the provided checklist field. If a mListener has been set, the
     * completion of all checklist fields is checked here.
     */
    public synchronized void mark(T field) {
        mMarkedFields.add(field);

        if (mListener != null && completed()) {
            mListener.onChecklistCompleted();
        }
    }

    /**
     * Checks if the given checklist field has been marked.
     */
    public synchronized boolean isMarked(T field) {
        return mMarkedFields.contains(field);
    }

    /**
     * Checks if all checklist fields have been marked.
     */
    public synchronized boolean completed() {
        return mMarkedFields.size() == mAllFields.length;
    }

    /**
     * Sets a callback, that should be invoked upon completion of the checklist.
     */
    public void setOnCompletedListener(OnCompletedListener listener) {
        this.mListener = listener;
    }

    /**
     * Private property.
     */
    private T[] mAllFields;
    private Set<T> mMarkedFields;
    private OnCompletedListener mListener;
}
