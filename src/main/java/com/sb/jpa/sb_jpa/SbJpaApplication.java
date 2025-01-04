package com.sb.jpa.sb_jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sb.jpa.sb_jpa.entities.Person;
import com.sb.jpa.sb_jpa.repositories.PersonRepository;


@SpringBootApplication
public class SbJpaApplication  implements CommandLineRunner{

	@Autowired
	private PersonRepository _PersonRepository;
	public static void main(String[] args) {
		SpringApplication.run(SbJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//singleEntity();
		create();

	}

	@Transactional
	public void create(){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("escriba un nombre");
		String name = scanner.next();

		System.out.println("escriba un apellido");
		String lastName = scanner.next();

		System.out.println("escriba un atributo");
		String attribute = scanner.next();
		scanner.close();

		Person person = new Person(
			null,
			name,
			lastName,
			attribute
		);

		var result = _PersonRepository.save(person);

		if(result.getId() != null){

			System.out.println("Guardado con exito!!!");

		}else{

			System.out.println("ERROR GUARDANDO");
		}


	}

	@Transactional(readOnly = true)
	public void singleEntity(){

		Long id = 5L;

		/*Person person = null;
		Optional<Person> oPerson = _PersonRepository.findById(id);

		if(oPerson.isPresent())
		{
			person = oPerson.get();
			System.out.println("RESULT SINGLE ID: " + person.toString());
		}
		else{
			System.out.println("RESULT SINGLE ID NOT FOUNDED WITH VELUE: " + id );
		}*/

		_PersonRepository.findById(id).ifPresent(

			p -> System.out.println("FOUNDED WITH VALUE DEFAULT METHOD: "+ id + p.toString())
			
			);

		_PersonRepository.findByNameCustom("Daniel").ifPresent(System.out::println);

		_PersonRepository.findLikeName("lla").ifPresent(System.out::println);

		_PersonRepository.findByNameContaining("illi").ifPresent(System.out::println);

		
		
	}


	@Transactional(readOnly = true)
	public void list(){

		
		List<Person> persons =(List<Person>) _PersonRepository.findAll();
		persons.stream().forEach(e -> System.out.println("Find all: "+e));


		persons =(List<Person>) _PersonRepository.findByAttribute("Attribute 1");
		persons.stream().forEach(e -> System.out.println("Find by attribute: "+e));


		persons =(List<Person>) _PersonRepository.findByName("Olivia");
		persons.stream().forEach(e -> System.out.println("Find by name: "+e));

		
		persons =(List<Person>) _PersonRepository.findByLastName("Garcia");
		persons.stream().forEach(e -> System.out.println("Find by lastname: "+e));

		persons = (List<Person>) _PersonRepository.buscarPorNombre("John", "Doe");
		if(persons.size() < 1) System.out.println("PERSONS EMPTY");
		persons.stream().forEach(e -> System.out.println("Buscar por nombre y apellido: "+e));

		persons = (List<Person>) _PersonRepository.findByNameAndLastName("John", "Doe");
		persons.stream().forEach(e -> System.out.println("find By Name And LastName: "+e));
		

		List<Object[]> DataPersons =  (List<Object[]>) _PersonRepository.obtenerPersonData();
		DataPersons.stream().forEach(e -> System.out.println("Buscar data persona: "+e[0]+" "+e[1]));

		DataPersons =  (List<Object[]>) _PersonRepository.obtenerPersonDataByNameAndAttrib("John", "Doe");
		DataPersons.stream().forEach(e -> System.out.println("Buscar data persona by name and Last Name: "+e[0]+" "+e[1]));

	}

}
