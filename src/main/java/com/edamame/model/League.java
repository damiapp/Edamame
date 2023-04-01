package com.edamame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "league")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeague;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private Date startDate;
    @Column(unique = true)
    private String inviteCode;
    @ManyToMany
    @JoinColumn(name="idUser")
    private List<User> users;
    @OneToMany
    @JoinColumn(name="idMatch")
    List<Match> matches;
}
