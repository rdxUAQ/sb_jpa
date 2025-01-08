package com.sb.jpa.sb_jpa;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sb.jpa.sb_jpa.dto.PersonDto;
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
		//create();
		//edit();

		//delete();

		//getNameById();
		//getFullNameById();

		//getPersonsFullDataCustom();

		//getPersonFullDataCustom();

		//getMixCustom();

		//getPersonDto();

		//getAllNamesCustom();

		//getAllNamesDisticntCustom();

		//getCountNameDisticnt();

		//jpqlFunctions();

		//jpqlBetween();

		//getOrderByFuctions();

		getCountMaxMinLenght();
	}

	@Transactional(readOnly = true)
	public void getCountMaxMinLenght(){
		System.out.println("==================JPQL COUNT CUSTOM==================");
		System.out.println("Total Persons: "+_PersonRepository.countAllPersons());
		System.out.println("==================JPQL COUNT DEFAULT==================");
		System.out.println("Total Persons: "+_PersonRepository.count());
		System.out.println("==================JPQL MAX VALUE ID==================");
		System.out.println("Total Persons: "+_PersonRepository.maxValuePersonId());
		System.out.println("==================JPQL MIN VALUE ID==================");
		System.out.println("Total Persons: "+_PersonRepository.minValuePersonId());
		System.out.println("==================JPQL Length full name by ID 2==================");
		System.out.println("Name LENGHT for "+_PersonRepository.getFullNameById(2L)+": "+_PersonRepository.getLengthFullNameById(2L));
		System.out.println("==================JPQL Longest name==================");
		System.out.println("Longest name: "+_PersonRepository.getLongestName().get(0));
		System.out.println("==================JPQL smallest name==================");
		System.out.println("smallest name: "+_PersonRepository.getSmallestName().get(0));
	}

	@Transactional(readOnly = true)
	public void getOrderByFuctions(){

		System.out.println("==================JPQL Order by name ASC==================");
		_PersonRepository.getPersonsOrderByName().forEach(System.out::println);

		System.out.println("==================JPQL Order by name DESC==================");
		_PersonRepository.getPersonsOrderByNameDesc().forEach(System.out::println);

		System.out.println("==================JPQL Order by name BEtween 2 and 6=================");
		_PersonRepository.getPersonsOrderByNameBetweenId(2L,6L).forEach(System.out::println);

		System.out.println("==================JPQL Order by name asc and lastname desc=================");
		_PersonRepository.orderByNameAndLastName().forEach(System.out::println);

		System.out.println("==================JPQL default find all order by name desc =================");
		_PersonRepository.findAllByOrderByNameDesc().forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void jpqlBetween(){


		System.out.println("==================JPQL BETWEEN ID 2 AND 7==================");
		_PersonRepository.getBetweenId().forEach(System.out::println);

		System.out.println("==================JPQL BETWEEN Name J AND P ALPHABET==================");
		_PersonRepository.getBetweenByLetterName().forEach(System.out::println);


	}

	@Transactional(readOnly = true)
	public void jpqlFunctions(){

		Scanner scanner = new Scanner(System.in);

		_PersonRepository.findAll().forEach(System.out::println);

		System.out.println("Select an id to apply JPAQL Functions");

		Long id = scanner.nextLong();

		String result = _PersonRepository.getConcatNameAndAttrib(id);

		if(result != null){

			System.out.println("==================JPAQL CONCAT NAME ATTRIBUTE==================");
			System.out.println("Concatenated name+attribute = "+result );

			result = _PersonRepository.getNameLower(id);
			System.out.println("==================JPAQL LOWER NAME==================");
			System.out.println("lower name = "+result );

			result = _PersonRepository.getNameUpper(id);
			System.out.println("==================JPAQL UPPER NAME==================");
			System.out.println("upper name = "+result );

			result = _PersonRepository.getFullnameLowerUpper(id);
			System.out.println("==================JPAQL LOWER AND UPPER FULLNAME==================");
			System.out.println("upper and lower fullname name = "+result );
		}

		

	}

	@Transactional(readOnly = true)
	public void getCountNameDisticnt(){
		System.out.println("==================Get Count Person Distinct==================");

		Long result = _PersonRepository.countDisctinctName();

		System.out.println("Disticnt names count: "+result);
		
		
	}

	@Transactional(readOnly = true)
	public void getAllNamesDisticntCustom(){
		System.out.println("==================Get Names Person Distinct==================");

		List<String> result = _PersonRepository.findAllNamesDistinct();
		
		result.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void getAllNamesCustom(){
		System.out.println("==================Get Names Person==================");

		List<String> result = _PersonRepository.findAllNames();
		
		result.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void getPersonDto(){
		System.out.println("==================Get Custom Data PersonDto==================");
		List<PersonDto> result = _PersonRepository.findAllPersonDto();

		result.forEach(p -> {
			System.out.println(p.toString());
		});
	}

	@Transactional(readOnly = true)
	public void getMixCustom(){

		System.out.println("==================Get mix Custom Data Person==================");

		List<Object[]> result = _PersonRepository.obtenerMixPersonsData();

		result.forEach(p -> {
			System.out.println("Person: "+p[0]+"\nMix Attrib: "+p[1]);
		});


	}

	@Transactional(readOnly = true)
	public void getPersonFullDataCustom(){

		Scanner scanner = new Scanner(System.in);

		_PersonRepository.findAll().forEach(System.out::println);

		System.out.println("Select an id to get full data");

		Long id = scanner.nextLong();

		scanner.close();

		var result = _PersonRepository.obtenerFullPersonDataById(id);

		if (result == null || result.length == 0) {
			System.out.println("Data not found");
		} else {
			System.out.println("Person data:");
			for (Object attrib : result) {
				if (attrib instanceof Object[]) {
					Object[] attribArray = (Object[]) attrib;
					for (Object attr : attribArray) {
						System.out.println(attr);
					}
				} else {
					System.out.println(attrib);
				}
			}
		}
}


	

	@Transactional(readOnly = true)
	public void getPersonsFullDataCustom(){

		var result = _PersonRepository.obtenerFullPersonsData();

		if(result.isEmpty()){
			System.out.println("Data not founded");

		}else{

			System.out.println("Persons:\n");

			result.forEach(p -> {

				
				for (Object attrib : p) {
					System.out.print(attrib+" | ");
					
					}
					System.out.println();
				
			});
		}


	}

	@Transactional(readOnly = true)
	public void getFullNameById(){
		Scanner scanner = new Scanner(System.in);

		_PersonRepository.findAll().forEach(System.out::println);

		System.out.println("Select an id to get full name");

		Long id = scanner.nextLong();

		scanner.close();

		var result = _PersonRepository.getFullNameById(id);

		System.out.println(result != null ? "Found entity with id: " + id + "\nData: "+ result:"Not founded with id"+id);

		
	}

	@Transactional(readOnly = true)
	public void getNameById(){

		Scanner scanner = new Scanner(System.in);

		_PersonRepository.findAll().forEach(System.out::println);

		System.out.println("Write the user ID get its name:");
		Long id = scanner.nextLong();

		scanner.close();

		var result =_PersonRepository.getNameById(id);

		if(result != null){
			System.out.println("Name:"+result+"\nfor id result: "+id);
		}
	}
	@Transactional
	public void delete(){

		Scanner scanner = new Scanner(System.in);
		_PersonRepository.findAll().forEach(System.out::println);

		System.out.println("Write the user ID to delete: ");
		Long id = scanner.nextLong();

		scanner.close();


		_PersonRepository.findById(id).ifPresentOrElse(p ->{
			System.out.println("User founded");
			_PersonRepository.deleteById(p.getId());
			System.err.println("DELETED");

		},
		()->{
			System.out.println("Entity not founded with id: " + id);
		});
		
		
	}

	@Transactional
	public void edit(){

		Scanner scanner = new Scanner(System.in);

		System.out.println("Write the user ID: ");
		Long id = scanner.nextLong();
		scanner.close();
		

		_PersonRepository.findById(id).ifPresentOrElse(person -> {

			System.out.println("USER FOUNDED \nWrite a new name");
			scanner.nextLine();
			String name = scanner.nextLine();

			System.out.println("Write a new LastName");
			String lastName = scanner.nextLine();

			System.out.println("Write a new attribute");
			String attribute = scanner.nextLine();

			person.setName(name);
			person.setLastName(lastName);
			person.setAttribute(attribute);

			var result =_PersonRepository.save(person);

			System.out.println(result != null ? "SAVED CORRECTLY" : "ERROR SAVING");
		}, () -> {
			System.out.println("User not founded with id: "+id);
		});

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
