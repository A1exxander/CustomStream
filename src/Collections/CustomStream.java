package Collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class CustomStream<E> {

    private Collection<E> collection = null;

    public CustomStream(Collection<E> collection) {
        this.collection = collection;
    }

    public CustomStream<E> filter(Predicate<E> filter) {

        Collection<E> newList = new LinkedList<>();

        for (E listItem : collection) {
            if (filter.test(listItem)) {
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

    public CustomStream<E> map(Test<E> testFunctionalInterface) {

        Collection<E> newCollection = new LinkedList<>();
        for (E listItem : collection){
            listItem = testFunctionalInterface.testMethod(listItem);
            newCollection.add(listItem);
        }
        collection = newCollection;
        return this;

    }

    public CustomStream<E> forEach(Test<E> testFunctionalInterface){

        for (E listItem : collection) {
            testFunctionalInterface.testMethod(listItem);
        }
        return this;

    }

    public Collection<E> collect() {
        return collection;
    }

}
