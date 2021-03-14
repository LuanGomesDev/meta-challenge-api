package br.com.challenge.meta.model.ApprovalProcessing;

import br.com.challenge.meta.enumeration.AccessEnum;
import br.com.challenge.meta.enumeration.StatusEnum;
import br.com.challenge.meta.model.HangTag.HangTag;
import br.com.challenge.meta.model.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "approval_processing")
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class ApprovalProcessing implements Serializable {
  private static final long serialVersionUID = 196266143388770099L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "BINARY(16)", updatable = false, unique = true, nullable = false)
  private UUID id;

  @Column(name = "active", length = 1)
  @Enumerated(EnumType.ORDINAL)
  private AccessEnum access;

  @Column
  @Enumerated(EnumType.ORDINAL)
  private StatusEnum status;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false)
  private User responsible;

  @Size(max = 11)
  @Column(name = "due_date", length = 11)
  private LocalDateTime dueDate;

  @ManyToMany
  @JoinTable(name = "hang_tag_approval_processing", joinColumns = @JoinColumn(name = "approval_processing_id"), inverseJoinColumns = @JoinColumn(name = "hang_tag_id"))
  Set<HangTag> hangTags;

  @Size(max = 200)
  @Column(length = 200)
  private String buyerName;

  @Size(max = 120)
  @Column(length = 120)
  private String buyerEmail;

  @Size(max = 200)
  @Column(length = 200)
  private String purchaseOrderName;

  @Size(max = 200)
  @Column(length = 200)
  private String description;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }
}
