package org.xliu.cs.algs_ds.ds.graph;

import org.xliu.cs.projects.anno_for_doc.annotations.IgnoreNote;

import java.util.Objects;

@IgnoreNote
public class Vertex<T> {
    private long id;
    private T value;

    public Vertex(long id, T value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
