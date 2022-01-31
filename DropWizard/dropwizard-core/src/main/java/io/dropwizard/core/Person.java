package io.dropwizard.core;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "people")
@NamedQueries(
        {
                @NamedQuery(name = "io.dropwizard.core.findAll", query = "SELECT p from Person p")
        }
)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "jobTitle", nullable = false)
    private String jobTitle;

    @Column(name = "yearBorn", nullable = false)
    @Min(value = 0)
    @Max(value = 9999)
    private int yearBorn;

    public Person() {

    }

    public Person(String fullName, String jobTitle, int yearBorn) {
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.yearBorn = yearBorn;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getYearBorn() != person.getYearBorn()) return false;
        if (getFullName() != null ? !getFullName().equals(person.getFullName()) : person.getFullName() != null)
            return false;
        return getJobTitle() != null ? getJobTitle().equals(person.getJobTitle()) : person.getJobTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getJobTitle() != null ? getJobTitle().hashCode() : 0);
        result = 31 * result + getYearBorn();
        return result;
    }
}
