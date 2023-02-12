package com.example.testsapp.representation.DTO;

import com.example.testsapp.data.Entity.Roles;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link com.example.testsapp.data.Entity.Roles} entity
 */
@Data
public class RolesDto implements Serializable {
    private final Long id;
    private final String name;
    // TODO: 04.02.2023 уходит в переполнение стака
    //private final Set<UsersDto> users;

    public Roles toEntity()
    {
        return new Roles(
                this.id,
                this.name
        );
    }

    public static RolesDto toDto(Roles roles)
    {
        return new RolesDto(
                roles.getId(),
                roles.getName()
                //roles.getUsers().stream().map(UsersDto::toDto).collect(Collectors.toSet())
        );
    }
}