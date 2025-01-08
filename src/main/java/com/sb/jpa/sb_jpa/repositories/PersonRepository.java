package com.sb.jpa.sb_jpa.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sb.jpa.sb_jpa.dto.PersonDto;
import com.sb.jpa.sb_jpa.entities.Person;
import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Long>{

    //default recognized methods
    List<Person> findByAttribute(String attribute);
    List<Person> findByLastName(String lastName);
    List<Person> findByName(String name);
    List<Person> findByNameAndLastName(String name, String lastName);
    Optional<Person> findByNameContaining(String name);
    List<Person> findAllByOrderByNameDesc();

    //this is case sensitive, use attrib from class instead from db column


    //PersonDTO
    //use a full path for the dto class
    @Query("select new com.sb.jpa.sb_jpa.dto.PersonDto(p.name , p.lastName, p.attribute) from Person p")
    List<PersonDto> findAllPersonDto();




    //JPQL Funtions examples
    @Query("SELECT UPPER(p.name) as concatna from Person p WHERE id=?1")
    String getNameUpper(Long id);

    @Query("SELECT LOWER(p.name) as concatna from Person p WHERE id=?1")
    String getNameLower(Long id);

    @Query("SELECT CONCAT(Lower(p.name),' ' ,upper(p.lastName)) from Person p WHERE id=?1")
    String getFullnameLowerUpper(Long id);

    //@Query("SELECT p.name || ' ' || p.attribute as concatna from Person p WHERE id=?1")
    @Query("SELECT CONCAT(p.name, ' ', p.attribute) as concatna from Person p WHERE id=?1")
    String getConcatNameAndAttrib(Long id);

    //JPQL BETWEEN
    //@Query("select p from Person p where p.id between ?1 and ?2") in case parameters needed
    @Query("select p from Person p where p.id between 2 and 7")
    List<Person> getBetweenId();

    //@Query("select p from Person p where p.name between ?1 and ?2 ") in case parameters needed
    @Query("select p from Person p where p.name between 'J' and 'P' ")
    List<Person> getBetweenByLetterName();

    //JPQL COUNT MAX MIN

    @Query("Select COUNT(p) FROM Person as p")
    Long countAllPersons();

    @Query("SELECT MAX(p.id) From Person as p")
    Long maxValuePersonId();

    @Query("SELECT MIN(p.id) FROM Person as p")
    Long minValuePersonId();

    //JPQL ORDER BY
    @Query("SELECT p from Person p ORDER by p.name")
    List<Person> getPersonsOrderByName();
    @Query("SELECT p from Person p ORDER by p.name desc")
    List<Person> getPersonsOrderByNameDesc();
    @Query("SELECT p from Person p WHERE p.id between ?1 and ?2 ORDER BY p.name")
    List<Person> getPersonsOrderByNameBetweenId(Long startId, Long endId);
    @Query("SELECT p from Person p Order by p.name asc, p.lastName desc")
    List<Person> orderByNameAndLastName();
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
