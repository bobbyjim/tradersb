package net.eagle.tas.tradersb.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {

    @Size(min = 2, message = "Name should have at least 2 characters")
    public String name;

    @Past
    public Date birthDate;

    @Id
    public Integer id;

    protected User() {
        // default no-arg for Jackson bean construction...
    }

    public User(String name, Date birthDate, Integer id) {
        this.name = name;
        this.birthDate = birthDate;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
