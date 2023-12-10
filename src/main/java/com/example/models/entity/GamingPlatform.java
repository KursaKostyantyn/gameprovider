package com.example.models.entity;

import com.example.constants.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.Set;

@Entity
public class GamingPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    @ElementCollection
    private Set<Role> role;

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GamingPlatform(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<Game> allowedGames;

    @Override
    public String toString() {
        return "GamingPlatform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allowedGames=" + allowedGames +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getAllowedGames() {
        return allowedGames;
    }

    public void setAllowedGames(List<Game> allowedGames) {
        this.allowedGames = allowedGames;
    }

    public GamingPlatform(String name, List<Game> allowedGames) {
        this.name = name;
        this.allowedGames = allowedGames;
    }

    public GamingPlatform() {
    }
}
