package demo.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import com.univocity.parsers.annotations.Parsed;

public class OnSiteInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Parsed(field = "employee_id")
    private String employeeId;
    @Parsed(field = "last_name")
    private String lastName;
    @Parsed(field = "first_name")
    private String firstName;
    @Parsed(field = "start_date")
    private String startDate;
    @Parsed(field = "team_name")
    private String teamName;
    @Parsed(field = "rookie_flag")
    private String rookieFlag;

    public String getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getRookieFlag() {
        return rookieFlag;
    }

    public Integer getRookieFlagAsInteger() {
        Integer _rookieFlag = Optional.ofNullable(rookieFlag).map(x -> Integer.valueOf(x)).orElse(null);
        if (Objects.isNull(_rookieFlag)) {
            return null;
        }
        return _rookieFlag == 1 ? _rookieFlag : null;
    }

    @Override
    public String toString() {
        return "OnSiteInfoDTO [employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName
                + ", startDate=" + startDate + ", teamName=" + teamName + ", rookieFlag=" + rookieFlag + "]";
    }

}
