package com.example.project.exam.mapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class ResponseDefault {
    @JsonProperty("responseCode")
    private String responseCode = null;
    @JsonProperty("responseDescription")
    private String description = null;

}
