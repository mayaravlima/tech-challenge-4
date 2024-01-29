package com.postech.catalog.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record CreateUserRequest(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("favorites") Set<String> favorites
) {
}
