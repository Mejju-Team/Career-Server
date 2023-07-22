package com.example.career.domain.user.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Tag")
public class Tag {

    @Id
    private Long tutor_id;

    @Column(nullable = false)
    private Long idx;

    @Column(nullable = false, columnDefinition = "VARCHAR(15)")
    private String name;
}
