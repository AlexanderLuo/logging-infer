package org.share.logging.infer.core.metrics;

public interface IWatch<E> {


    void unWatch(E e);
    void watching(E e);
    boolean isWatching(E e);



}
