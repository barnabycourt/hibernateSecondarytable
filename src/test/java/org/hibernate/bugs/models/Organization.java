package org.hibernate.bugs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
public class Organization {

    @Id
    private String organization_id;

    private String name;

    public Organization(String organization_id, String name) {
        this.organization_id = organization_id;
        this.name = name;
    }

    public String getId() {
        return this.organization_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(organization_id, that.organization_id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization_id, name);
    }
}