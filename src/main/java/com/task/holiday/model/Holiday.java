package com.task.holiday.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "date",
        "observed",
        "public"
})
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Holiday {

    @JsonProperty("name")
    @Getter
    @Setter
    private String name;

    @JsonProperty("date")
    @Getter
    @Setter
    private String date;

    @JsonProperty("observed")
    @Getter
    @Setter
    private String observed;

    @JsonProperty("public")
    @Getter
    @Setter
    private Boolean isPublic;

}