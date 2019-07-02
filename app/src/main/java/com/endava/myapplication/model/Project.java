package com.endava.myapplication.model;

import java.util.List;

public class Project {

    private String id;

    private String name;

    private List<String> technologies;

    private String logo;

    private List<Employee> keyEmployees;

    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Employee> getKeyEmployees() {
        return keyEmployees;
    }

    public void setKeyEmployees(List<Employee> keyEmployees) {
        this.keyEmployees = keyEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (technologies != null ? !technologies.equals(project.technologies) : project.technologies != null)
            return false;
        if (logo != null ? !logo.equals(project.logo) : project.logo != null) return false;
        return keyEmployees != null ? keyEmployees.equals(project.keyEmployees) : project.keyEmployees == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (technologies != null ? technologies.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (keyEmployees != null ? keyEmployees.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", technologies=" + technologies +
                ", logo='" + logo + '\'' +
                ", keyEmployees=" + keyEmployees +
                '}';
    }
}
