using System.Collections.Generic;
using library_system.Models.Entity;
using library_system.Controllers;

namespace library_system.Models
{
    //<summary>
    // 貸出表ドメインのEntity、DTOを管理します。
    //</summary>
    public class LibraryCardMgr
    {

        public LibraryCard LibraryCard { get; set; }
        public IEnumerable<LibraryCardController.LibraryCardUI> LibraryCardUI { get; set; }
    }
}
