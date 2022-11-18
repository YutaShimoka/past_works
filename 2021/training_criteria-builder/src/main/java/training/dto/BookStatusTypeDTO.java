package training.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import training.entity.LibraryCard;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookStatusTypeDTO {

    public BookStatusTypeDTO(LibraryCard entity) {
        this.statusType = getStatusType(entity);
    }

    @JsonProperty("status_type")
    private String statusType;

    private String getStatusType(LibraryCard entity) {
        return Objects.isNull(entity.getReturnDate()) ? "貸出中" : "貸出可";
    }

}
