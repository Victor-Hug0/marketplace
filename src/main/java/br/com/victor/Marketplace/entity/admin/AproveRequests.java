package br.com.victor.Marketplace.entity.admin;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "aprove_requests")
public class AproveRequests {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_id",  nullable = false)
    private String entityId;

    @Column(name = "entity_type",  nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @Column(name = "request_type",   nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "revisor_id")
    private Administrator revisor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AproveStatus status;

    private String justification;

    @Column(name = "created_at",  nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "answered_in",  nullable = false)
    private LocalDateTime answeredIn;

    public AproveRequests(Long entityId, EntityType entityType, RequestType requestType) {
        this.entityId = String.valueOf(entityId);
        this.entityType = entityType;
        this.requestType = requestType;
    }

    public AproveRequests(UUID entityId, EntityType entityType, RequestType requestType) {
        this.entityId = entityId.toString();
        this.entityType = entityType;
        this.requestType = requestType;
    }

    public AproveRequests () {}

    public Long getEntityIdAsLong() {
        try {
            return Long.parseLong(entityId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public UUID getEntityIdAsUUID() {
        try {
            return UUID.fromString(entityId);
        }  catch (IllegalArgumentException e) {
            return null;
        }
    }


    public Long getId() {
        return id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public AproveStatus getStatus() {
        return status;
    }

    public void setStatus(AproveStatus status) {
        this.status = status;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getAnsweredIn() {
        return answeredIn;
    }

    public void setAnsweredIn(LocalDateTime answeredIn) {
        this.answeredIn = answeredIn;
    }

    public Administrator getRevisor() {
        return revisor;
    }

    public void setRevisor(Administrator revisor) {
        this.revisor = revisor;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
