package com.example.ToDo.models;

public class Todo {

    String desc;
    String person;
    int id;
    
    public Todo(int id, String desc, String person){
        setDesc(desc);
        setPerson(person);
        setId(id);
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
