package org.openmbee.sdvc.core.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "organizations")
public class Organization extends Base {

    @JsonProperty("name")
    private String organizationName;

    @JsonProperty("id")
    private String organizationId;

    @OneToMany(mappedBy = "organization")
    private Collection<Project> projects;

    public Organization() {
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || id == null || getClass() != o.getClass()) {
            return false;
        }

        Organization node = (Organization) o;

        return id.equals(node.id);
    }

}