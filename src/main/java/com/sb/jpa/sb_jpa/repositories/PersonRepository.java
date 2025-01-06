package com.sb.jpa.sb_jpa.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sb.jpa.sb_jpa.dto.PersonDto;
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


    //PersonDTO

    //use a full path for the dto class
    @Query("select new com.sb.jpa.sb_jpa.dto.PersonDto(p.name , p.lastName, p.attribute) from Person p")
    List<PersonDto> findAllPersonDto();
    
    
    //Person
    @Query("SELECT COUNT (DISTINCT (p.name)) from Person p")
    Long countDisctinctName();
    
    @Query("SELECT DISTINCT (p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT p.name from Person p")
    List<String> findAllNames();

    @Query("Select concat(p.name, ' ' , p.lastName) From Person p where id=?1")
    String getFullNameById(Long id);

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

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


    //variant
    @Query("select p.id, p.name, p.lastName, p.attribute from Person p")
    List<Object[]> obtenerFullPersonsData();

   
    @Query("select p.id, p.name, p.lastName, p.attribute from Person p Where id=?1")
    Object[] obtenerFullPersonDataById(Long id);

    
    @Query("select p, p.attribute from Person p")
    List<Object[]> obtenerMixPersonsData();


    
    
    
    
} 
