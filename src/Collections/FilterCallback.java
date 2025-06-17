package Collections;

public interface FilterCallback<E> {
    public boolean performFilter(E item); // Filtering logic can just exist inside the function
}
