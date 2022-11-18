using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace library_system.Models.Entity
{
    //<summary>
    // ユーザを表現します。
    //</summary>
    public class User
    {
        /** ユーザID */
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public string Id { get; set; }
        /** 名前 */
        public string Name { get; set; }
        /** パスワード */
        public string Password { get; set; }
    }
}