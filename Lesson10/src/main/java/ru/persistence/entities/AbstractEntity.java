package ru.persistence.entities;

import javax.persistence.*;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "timestamp_create", updatable = false)
    private Date timestampCreate;

    @UpdateTimestamp
    @Column(name = "timestamp_modify")
    private Date timestampModify;
}