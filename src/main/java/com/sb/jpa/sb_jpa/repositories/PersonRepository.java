package com.sb.jpa.sb_jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sb.jpa.sb_jpa.entities.Person;
import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Long>{

    List<Person> findByAttribute(String attribute);
    List<Person> findByLastName(String lastName);
    List<Person> findByName(String name);
    List<Person> findByNameAndLastName(String name, String lastName);
    Optional<Person> findByNameContaining(String name);

    //this is case sensitive, use attrib from class instead from db column

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findByNameCustom (String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findLikeName(String name);

    @Query("select p from Person p Where p.name=?1 and p.lastName =?2")
    List<Person> buscarPorNombre(String name, String lastName);

    @Query("select p.name, p.attribute from Person p")
    List<Object[]> obtenerPersonData();
    
    @Query("select p.name, p.lastName from Person p where p.name=?1 and p.lastName=?2")
    List<Object[]> obtenerPersonDataByNameAndAttrib(String name, String lastName);

    
    
} 
