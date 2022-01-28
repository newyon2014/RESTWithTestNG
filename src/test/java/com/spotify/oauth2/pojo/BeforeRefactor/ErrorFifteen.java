package com.spotify.oauth2.pojo.BeforeRefactor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.oauth2.pojo.InnerError;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorFifteen {

    @JsonProperty("error")
    private InnerError error;

    public InnerError getError() {
        return error;
    }

    public void setError(InnerError error) {
        this.error = error;
    }
}
