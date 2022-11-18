package demo.entity;

import java.io.Serializable;

public class OnSiteInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 従業員ID */
    private String employeeId;
    /** 姓 */
    private String lastName;
    /** 名 */
    private String firstName;
    /** 開始日 */
    private String startDate;
    /** 配属 */
    private String teamName;
    /** 新採フラグ */
    private Integer rookieFlag;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getRookieFlag() {
        return rookieFlag;
    }

    public void setRookieFlag(Integer rookieFlag) {
        this.rookieFlag = rookieFlag;
    }

}
