package org.xliu.cs.algs_ds.ds.graph;

import org.xliu.cs.projects.anno_for_doc.annotations.IgnoreNote;

/**
 * 边的属性
 */
@IgnoreNote
public class Edge {
    private long from;
    private long to;

    // more attributes

    public Edge(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}
