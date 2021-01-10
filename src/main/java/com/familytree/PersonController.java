package com.familytree;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class PersonController {

    private PersonService personService;

    @GetMapping(value = "/person/")
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @PutMapping(value = "/person/")
    public ResponseEntity<List<Person>> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/leaves/")
    public ResponseEntity<List<Person>> getLeaves() {
        return ResponseEntity.ok(personService.getLeaves());
    }

    @PostMapping(value = "/person/")
    public ResponseEntity<Void> addNewPerson(@RequestBody Person person) {
        personService.addNewPerson(person);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/parent/{childId}")
    public ResponseEntity<Void> addParent(@RequestBody Person parent, @PathVariable Long childId) {
        personService.addParent(childId, parent);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/person/{parentId}/{childId}")
    public ResponseEntity<Void> connect(@PathVariable Long childId, @PathVariable Long parentId) {
        personService.addParent(childId, parentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/child/{parentId}")
    public ResponseEntity<Void> addChild(@RequestBody Person child, @PathVariable Long parentId) {
        personService.addChild(parentId, child);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/person/")
    public ResponseEntity<Void> deleteAllPeople() {
        personService.removeAllPeople();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/person/{personId}")
    public ResponseEntity<Void> removePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/person/{parentId}/{childId}")
    public ResponseEntity<Void> removeConnection(@PathVariable Long parentId, @PathVariable Long childId) {
        personService.removeConnection(parentId, childId);
        return ResponseEntity.ok().build();
    }
}
