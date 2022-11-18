package training.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 貸出表を表現します。
 */
@Entity
@Table(name = "貸出表")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LibraryCard {

    @Id
    /** 貸出番号 */
    @Column(name = "貸出番号")
    private long checkoutNumber;
    /** ISBNコード */
    @Column(name = "ISBNコード")
    private String isbnCode;
    /** 従業員番号 */
    @Column(name = "従業員番号")
    private String employeeNumber;
    /** 貸出日 */
    @Column(name = "貸出日")
    private Date checkoutDate;
    /** 返却予定日 */
    @Column(name = "返却予定日")
    private Date returnPlansDate;
    /** 返却日 */
    @Column(name = "返却日")
    private Date returnDate;

}
