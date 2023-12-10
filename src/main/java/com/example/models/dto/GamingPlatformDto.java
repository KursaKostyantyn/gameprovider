package com.example.models.dto;

public class GamingPlatformDto {
    private Long id;
    private String name;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "GamingPlatformDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

    public GamingPlatformDto(String name) {
        this.name = name;
    }

    public GamingPlatformDto() {
    }
}
