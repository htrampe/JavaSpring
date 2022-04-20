package com.example.ToDo.models;

public class Todo {

    String desc;
    String person;
    int id;
    int personId;

    public Todo(int id, String desc, String person, int personId){
        setDesc(desc);
        setPerson(person);
        setId(id);
        setPersonId(personId);
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setPerson(String person) {
        this.person = person;
    }

    public String getPerson() {
        return person;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
