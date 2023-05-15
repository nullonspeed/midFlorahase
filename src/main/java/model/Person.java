package model;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "person_seq", sequenceName = "Person.seq", allocationSize = 1)//erstellt sequence
public class Person {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "VORNAME")
    private String vorname;
    @Basic
    @Column(name = "NACHNAME")
    private String nachname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (vorname != null ? !vorname.equals(person.vorname) : person.vorname != null) return false;
        if (nachname != null ? !nachname.equals(person.nachname) : person.nachname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        return result;
    }

    public Person(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Person() {
    }
}
