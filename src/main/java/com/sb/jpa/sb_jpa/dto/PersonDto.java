package com.sb.jpa.sb_jpa.dto;


public class PersonDto {

    
    private String name;
   
    private String lastName;

    private String attribute;

    public PersonDto() {
    }

    public PersonDto(String name, String lastName, String attribute) {
        this.name = name;
        this.lastName = lastName;
        this.attribute = attribute;
    }

    public String getName(){
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "PersonDto"+"[name: "+name+", lastName: "+lastName+", attrib: "+attribute+"]";
    }

    

}
