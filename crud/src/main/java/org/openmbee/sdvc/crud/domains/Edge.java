package org.openmbee.sdvc.crud.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "edges")
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Node parent;

    @ManyToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Node child;

    @Column(columnDefinition = "smallint")
    private Integer edgeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Integer getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(Integer edgeType) {
        this.edgeType = edgeType;
    }
}
