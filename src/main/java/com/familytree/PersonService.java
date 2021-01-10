package com.familytree;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public void removeAllPeople() {
        personRepository.deleteAll();
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void addNewPerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> getLeaves() {
        List<Person> people = personRepository.findAll();
        List<Person> leaves = new ArrayList<>(people);

        for (Person person : people) {
            for (Person parent : person.getParents()) {
                leaves.remove(parent);
            }
        }

        return leaves;
    }

    public void addParent(Long childId, Person parent) {
        Optional<Person> childOpt = personRepository.findById(childId);
        if (childOpt.isEmpty()) return;
        Person child = childOpt.get();

        child.addParent(parent);
        personRepository.save(child);
        personRepository.save(parent);
    }

    public void addParent(Long childId, Long parentId) {
        Optional<Person> childOpt = personRepository.findById(childId);
        Optional<Person> parentOpt = personRepository.findById(parentId);
        if (childOpt.isEmpty() || parentOpt.isEmpty()) return;
        Person child = childOpt.get();
        Person parent = parentOpt.get();

        child.addParent(parent);
        personRepository.save(child);
    }

    public void removeConnection(Long parentId, Long childId) {
        Optional<Person> childOpt = personRepository.findById(childId);
        Optional<Person> parentOpt = personRepository.findById(parentId);
        if (childOpt.isEmpty() || parentOpt.isEmpty()) return;
        Person child = childOpt.get();
        Person parent = parentOpt.get();

        child.removeParent(parent);
        personRepository.save(child);
    }

    public void removePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    public void addChild(Long parentId, Person child) {
        Optional<Person> parentOpt = personRepository.findById(parentId);
        if (parentOpt.isEmpty()) return;
        Person parent = parentOpt.get();

        child.addParent(parent);
        personRepository.save(child);
    }

}
