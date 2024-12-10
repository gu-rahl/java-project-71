package hexlet.code;

public class DiffEntry {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final Status status;

    public DiffEntry(String key, Object oldValue, Object newValue, Status status) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        ADDED,
        REMOVED,
        CHANGED,
        UNCHANGED
    }
}
