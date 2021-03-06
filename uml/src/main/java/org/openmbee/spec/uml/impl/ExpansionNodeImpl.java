package org.openmbee.spec.uml.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.SecondaryTable;
import javax.persistence.Transient;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Table;
import org.openmbee.spec.uml.Activity;
import org.openmbee.spec.uml.ActivityEdge;
import org.openmbee.spec.uml.ActivityGroup;
import org.openmbee.spec.uml.ActivityNode;
import org.openmbee.spec.uml.ActivityPartition;
import org.openmbee.spec.uml.Behavior;
import org.openmbee.spec.uml.Classifier;
import org.openmbee.spec.uml.Comment;
import org.openmbee.spec.uml.Dependency;
import org.openmbee.spec.uml.Element;
import org.openmbee.spec.uml.ExpansionNode;
import org.openmbee.spec.uml.ExpansionRegion;
import org.openmbee.spec.uml.InterruptibleActivityRegion;
import org.openmbee.spec.uml.Namespace;
import org.openmbee.spec.uml.ObjectNodeOrderingKind;
import org.openmbee.spec.uml.RedefinableElement;
import org.openmbee.spec.uml.State;
import org.openmbee.spec.uml.StringExpression;
import org.openmbee.spec.uml.StructuredActivityNode;
import org.openmbee.spec.uml.Type;
import org.openmbee.spec.uml.ValueSpecification;
import org.openmbee.spec.uml.VisibilityKind;
import org.openmbee.spec.uml.jackson.MofObjectDeserializer;
import org.openmbee.spec.uml.jackson.MofObjectSerializer;

@Entity
@SecondaryTable(name = "ExpansionNode")
@Table(appliesTo = "ExpansionNode", fetch = FetchMode.SELECT, optional = false)
@DiscriminatorValue(value = "ExpansionNode")
@JsonTypeName(value = "ExpansionNode")
public class ExpansionNodeImpl extends MofObjectImpl implements ExpansionNode {

    private Element owner;
    private Collection<RedefinableElement> redefinedElement;
    private VisibilityKind visibility;
    private Collection<InterruptibleActivityRegion> inInterruptibleRegion;
    private ValueSpecification upperBound;
    private Type type;
    private Collection<ActivityGroup> inGroup;
    private ExpansionRegion regionAsInput;
    private Collection<State> inState;
    private String name;
    private Namespace namespace;
    private Collection<Dependency> clientDependency;
    private StringExpression nameExpression;
    private StructuredActivityNode inStructuredNode;
    private Activity activity;
    private Collection<ActivityNode> redefinedNode;
    private Boolean isLeaf;
    private ExpansionRegion regionAsOutput;
    private Collection<Element> ownedElement;
    private String qualifiedName;
    private Collection<ActivityEdge> outgoing;
    private Behavior selection;
    private Collection<ActivityPartition> inPartition;
    private Collection<ActivityEdge> incoming;
    private ObjectNodeOrderingKind ordering;
    private Collection<Classifier> redefinitionContext;
    private Boolean isControlType;
    private Collection<Comment> ownedComment;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Transient
    public Element getOwner() {
        return owner;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<RedefinableElement> getRedefinedElement() {
        if (redefinedElement == null) {
            redefinedElement = new ArrayList<>();
        }
        return redefinedElement;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RedefinableElementImpl.class)
    public void setRedefinedElement(Collection<RedefinableElement> redefinedElement) {
        this.redefinedElement = redefinedElement;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Enumerated(EnumType.STRING)
    public VisibilityKind getVisibility() {
        return visibility;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setVisibility(VisibilityKind visibility) {
        this.visibility = visibility;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "InterruptibleActivityRegionMetaDef", metaColumn = @Column(name = "inInterruptibleRegionType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_inInterruptibleRegion",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "inInterruptibleRegionId"))
    public Collection<InterruptibleActivityRegion> getInInterruptibleRegion() {
        if (inInterruptibleRegion == null) {
            inInterruptibleRegion = new ArrayList<>();
        }
        return inInterruptibleRegion;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = InterruptibleActivityRegionImpl.class)
    public void setInInterruptibleRegion(
        Collection<InterruptibleActivityRegion> inInterruptibleRegion) {
        this.inInterruptibleRegion = inInterruptibleRegion;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ValueSpecificationMetaDef", metaColumn = @Column(name = "upperBoundType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "upperBoundId", table = "ExpansionNode")
    public ValueSpecification getUpperBound() {
        return upperBound;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ValueSpecificationImpl.class)
    public void setUpperBound(ValueSpecification upperBound) {
        this.upperBound = upperBound;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "TypeMetaDef", metaColumn = @Column(name = "typeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId", table = "ExpansionNode")
    public Type getType() {
        return type;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = TypeImpl.class)
    public void setType(Type type) {
        this.type = type;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<ActivityGroup> getInGroup() {
        if (inGroup == null) {
            inGroup = new ArrayList<>();
        }
        return inGroup;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityGroupImpl.class)
    public void setInGroup(Collection<ActivityGroup> inGroup) {
        this.inGroup = inGroup;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ExpansionRegionMetaDef", metaColumn = @Column(name = "regionAsInputType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "regionAsInputId", table = "ExpansionNode")
    public ExpansionRegion getRegionAsInput() {
        return regionAsInput;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ExpansionRegionImpl.class)
    public void setRegionAsInput(ExpansionRegion regionAsInput) {
        this.regionAsInput = regionAsInput;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "StateMetaDef", metaColumn = @Column(name = "inStateType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_inState",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "inStateId"))
    public Collection<State> getInState() {
        if (inState == null) {
            inState = new ArrayList<>();
        }
        return inState;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = StateImpl.class)
    public void setInState(Collection<State> inState) {
        this.inState = inState;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", table = "ExpansionNode")
    public String getName() {
        return name;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Transient
    public Namespace getNamespace() {
        return namespace;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = NamespaceImpl.class)
    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Dependency> getClientDependency() {
        if (clientDependency == null) {
            clientDependency = new ArrayList<>();
        }
        return clientDependency;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = DependencyImpl.class)
    public void setClientDependency(Collection<Dependency> clientDependency) {
        this.clientDependency = clientDependency;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "StringExpressionMetaDef", metaColumn = @Column(name = "nameExpressionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "nameExpressionId", table = "ExpansionNode")
    public StringExpression getNameExpression() {
        return nameExpression;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = StringExpressionImpl.class)
    public void setNameExpression(StringExpression nameExpression) {
        this.nameExpression = nameExpression;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "StructuredActivityNodeMetaDef", metaColumn = @Column(name = "inStructuredNodeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "inStructuredNodeId", table = "ExpansionNode")
    public StructuredActivityNode getInStructuredNode() {
        return inStructuredNode;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = StructuredActivityNodeImpl.class)
    public void setInStructuredNode(StructuredActivityNode inStructuredNode) {
        this.inStructuredNode = inStructuredNode;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ActivityMetaDef", metaColumn = @Column(name = "activityType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", table = "ExpansionNode")
    public Activity getActivity() {
        return activity;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ActivityImpl.class)
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityNodeMetaDef", metaColumn = @Column(name = "redefinedNodeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_redefinedNode",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "redefinedNodeId"))
    public Collection<ActivityNode> getRedefinedNode() {
        if (redefinedNode == null) {
            redefinedNode = new ArrayList<>();
        }
        return redefinedNode;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityNodeImpl.class)
    public void setRedefinedNode(Collection<ActivityNode> redefinedNode) {
        this.redefinedNode = redefinedNode;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Column(name = "isLeaf", table = "ExpansionNode")
    public Boolean isLeaf() {
        return isLeaf;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ExpansionRegionMetaDef", metaColumn = @Column(name = "regionAsOutputType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "regionAsOutputId", table = "ExpansionNode")
    public ExpansionRegion getRegionAsOutput() {
        return regionAsOutput;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ExpansionRegionImpl.class)
    public void setRegionAsOutput(ExpansionRegion regionAsOutput) {
        this.regionAsOutput = regionAsOutput;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Element> getOwnedElement() {
        if (ownedElement == null) {
            ownedElement = new ArrayList<>();
        }
        return ownedElement;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedElement(Collection<Element> ownedElement) {
        this.ownedElement = ownedElement;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Transient
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityEdgeMetaDef", metaColumn = @Column(name = "outgoingType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_outgoing",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "outgoingId"))
    public Collection<ActivityEdge> getOutgoing() {
        if (outgoing == null) {
            outgoing = new ArrayList<>();
        }
        return outgoing;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityEdgeImpl.class)
    public void setOutgoing(Collection<ActivityEdge> outgoing) {
        this.outgoing = outgoing;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "BehaviorMetaDef", metaColumn = @Column(name = "selectionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "selectionId", table = "ExpansionNode")
    public Behavior getSelection() {
        return selection;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = BehaviorImpl.class)
    public void setSelection(Behavior selection) {
        this.selection = selection;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityPartitionMetaDef", metaColumn = @Column(name = "inPartitionType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_inPartition",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "inPartitionId"))
    public Collection<ActivityPartition> getInPartition() {
        if (inPartition == null) {
            inPartition = new ArrayList<>();
        }
        return inPartition;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityPartitionImpl.class)
    public void setInPartition(Collection<ActivityPartition> inPartition) {
        this.inPartition = inPartition;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ActivityEdgeMetaDef", metaColumn = @Column(name = "incomingType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_incoming",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "incomingId"))
    public Collection<ActivityEdge> getIncoming() {
        if (incoming == null) {
            incoming = new ArrayList<>();
        }
        return incoming;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActivityEdgeImpl.class)
    public void setIncoming(Collection<ActivityEdge> incoming) {
        this.incoming = incoming;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Enumerated(EnumType.STRING)
    public ObjectNodeOrderingKind getOrdering() {
        return ordering;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setOrdering(ObjectNodeOrderingKind ordering) {
        this.ordering = ordering;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @Transient
    public Collection<Classifier> getRedefinitionContext() {
        if (redefinitionContext == null) {
            redefinitionContext = new ArrayList<>();
        }
        return redefinitionContext;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ClassifierImpl.class)
    public void setRedefinitionContext(Collection<Classifier> redefinitionContext) {
        this.redefinitionContext = redefinitionContext;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Column(name = "isControlType", table = "ExpansionNode")
    public Boolean isControlType() {
        return isControlType;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setControlType(Boolean isControlType) {
        this.isControlType = isControlType;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @Column(name = "ownedCommentType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ExpansionNode_ownedComment",
        joinColumns = @JoinColumn(name = "ExpansionNodeId"),
        inverseJoinColumns = @JoinColumn(name = "ownedCommentId"))
    public Collection<Comment> getOwnedComment() {
        if (ownedComment == null) {
            ownedComment = new ArrayList<>();
        }
        return ownedComment;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CommentImpl.class)
    public void setOwnedComment(Collection<Comment> ownedComment) {
        this.ownedComment = ownedComment;
    }

}
