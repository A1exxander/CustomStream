package Collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CustomStream<E> {

    private Collection<E> collection = null;

    public CustomStream(Collection<E> collection) {
        this.collection = collection;
    }

    public CustomStream<E> filter(FilterCallback<E> filterCallback) {

        Collection<E> newList = new LinkedList<>();

        for (E listItem : collection) {
            if (filterCallback.performFilter(listItem)) {
                newList.add(listItem);
            }
        }

        collection = newList;
        return this;

    }

    public CustomStream<E> limit(int size) {

        if (size < 0) {
            throw new IllegalArgumentException("\nSize must be positive!");
        }

        Collection<E> newCollection = new LinkedList<>();
        Iterator<E> iterator = collection.iterator();

        while (iterator.hasNext() && newCollection.size() < size) {
            newCollection.add(iterator.next());
        }

        collection = newCollection;
        return this;

    }

    public CustomStream<E> map(MapCallback<E> mapCallback) {

        Collection<E> newCollection = new LinkedList<>();
        for (E listItem : collection){
            listItem = mapCallback.performMap(listItem);
            newCollection.add(listItem);
        }
        collection = newCollection;
        return this;

    }

    public CustomStream<E> forEach(ForEachCallback<E> forEachCallback) {

        for (E listItem : collection) {
            forEachCallback.performForEach(listItem);
        }
        return this;

    }

    public Collection<E> collect() { // Collects as a generic collection - Let users decide what to convert it into
        return collection;
    }

}
