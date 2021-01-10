package com.familytree;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@Node("PersonWojtasik")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dateOfDeath;

    public Person(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    @Relationship(type = "PARENT")
    private Set<Person> parents = new HashSet<>();

    public void addParent(Person parent) {
        parents.add(parent);
    }

    public void removeParent(Person parent) {
        parents.remove(parent);
    }

}
