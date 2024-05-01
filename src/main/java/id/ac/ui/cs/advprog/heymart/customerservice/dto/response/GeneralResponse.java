package id.ac.ui.cs.advprog.heymart.customerservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse {
    String status;
    String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Object data;
}
