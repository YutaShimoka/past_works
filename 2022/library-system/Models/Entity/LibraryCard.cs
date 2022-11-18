using System;
using System.ComponentModel.DataAnnotations;

namespace library_system.Models.Entity
{
    //<summary>
    // 貸出表を表現します。
    //</summary>
    public class LibraryCard
    {
        /** 貸出番号 */
        [Key]
        public long CheckoutNumber { get; set; }
        /** ISBNコード */
        public string IsbnCode { get; set; }
        /** ユーザID */
        public string UserId { get; set; }
        /** 貸出日 */
        public DateTime CheckoutDate { get; set; }
        /** 返却予定日 */
        public DateTime ReturnPlansDate { get; set; }
        /** 返却日 */
        public DateTime? ReturnDate { get; set; }
    }
}
