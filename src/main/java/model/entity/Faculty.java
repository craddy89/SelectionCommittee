package model.entity;

import java.util.List;
import java.util.Objects;

public class Faculty {

    private int id;
    private String name;
    private int capacity;
    private List<Subject> requiredSubjects;

    public Faculty() {

    }

    public Faculty(int id, String name, int capacity, List<Subject> requiredSubjects) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.requiredSubjects = requiredSubjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Subject> getRequiredSubjects() {
        return requiredSubjects;
    }

    public void setRequiredSubjects(List<Subject> requiredSubjects) {
        this.requiredSubjects = requiredSubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Faculty faculty = (Faculty) o;
        if (id != faculty.id) {
            return false;
        }
        if (capacity != faculty.capacity) {
            return false;
        }
        return Objects.equals(name, faculty.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", requiredSubjects=" + requiredSubjects +
                '}';
    }
}



