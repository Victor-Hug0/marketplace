package br.com.victor.Marketplace.entity.carrier;

import br.com.victor.Marketplace.entity.enums.MimeType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_images")
public class VehicleImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Lob
    @Column(name = "file_content", nullable = false)
    private byte[] fileContent;

    @Column(name = "mime_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MimeType mimeType;

    @Column(name = "byte_size", nullable = false)
    private Integer byteSize;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
