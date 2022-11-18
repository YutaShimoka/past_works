package demo.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorHolder {

    @JsonProperty("error_code")
    private int errorCode;

    @JsonProperty("msg")
    private String msg;

    private HttpStatus status;

    public ErrorHolder(HttpStatus status, String msg) {
        this.errorCode = status.value();
        this.msg = msg;
        this.status = status;
    }

    public ResponseEntity<Object> result() {
        return new ResponseEntity<>(new ErrorHolder(status, msg), status);
    }
}
