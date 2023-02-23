package com.edamame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResult;
    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;
    @OneToOne
    @JoinColumn(name = "idMatch")
    private Match match;
    @Column
    private Integer placement;
    @Column
    private Integer spice;
    @Column
    private Integer solari;
    @Column
    private Integer water;
    @Column
    private Integer victoryPoints;
    @Column
    private boolean chaumurky;
}
