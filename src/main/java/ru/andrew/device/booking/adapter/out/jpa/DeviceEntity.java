package ru.andrew.device.booking.adapter.out.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.Map;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "devices")
@EntityListeners(AuditingEntityListener.class)
public class DeviceEntity {

    @Id
    private String id;
    @Version
    private Integer version;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    private String name;
    private Boolean available;
    private Boolean deleted;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "last_order_id")
    private OrderEntity lastOrder;

    @Convert(converter = JpaConverterJson.class)
    private Map<String, String> details;

}
