package com.gepardec.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String firstName;
    private String lastName;

    @Override public String toString() {

        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
