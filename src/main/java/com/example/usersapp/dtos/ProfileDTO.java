package com.example.usersapp.dtos;

import com.example.usersapp.entities.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ProfileDTO {
    @NotBlank(message = "The firstName is required")
    private String firstName;

    @NotBlank(message = "The lastName is required")
    private String lastName;

    @NotNull(message = "The birthDate is required")
    @Past(message = "The date of birth must be in the past.")
    private Date birthDate;

    public Profile toProfile() {
        Profile profile = new Profile();
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setBirthDate(birthDate);
        return profile;
    }
}
