package com.saga.admin.entity;

import com.saga.database.config.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;
    private String icon;
    private Integer orders;
    private Long parentId;
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLRestriction("type='MENU'")
    private List<Translate> translates;
    @ManyToMany
    @JoinTable(
            name = "manage_group",
            joinColumns = @JoinColumn(name = "referent_id",table = "`group`"),
            inverseJoinColumns = @JoinColumn(name = "group_id",table = "`group`"))
    @SQLRestriction("type='GROUP_MENU'")
    private Set<Group> groups;

}
