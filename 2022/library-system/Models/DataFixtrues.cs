using System;
using System.Linq;
using library_system.Models.Entity;
using System.Collections.Generic;

namespace library_system.Models
{
    public class DataFixtrues
    {
        public static void Initialize(LibrariesContext context)
        {
            context.Database.EnsureCreated();
            if (context.LibraryCards.Any()) { return; }
            InsertTestData(context);
        }

        private static void InsertTestData(LibrariesContext context)
        {
            context.LibraryCards.AddRange(
                LibraryCardList()
            );
            context.Users.AddRange(
                UserList()
            );
            context.SaveChanges();
        }

        /** 貸出表の簡易生成 */
        private static List<LibraryCard> LibraryCardList()
        {
            return new List<LibraryCard> {
                new LibraryCard
                {
                    IsbnCode = "978-4-905318-63-7",
                    UserId = "1001",
                    CheckoutDate = DateTime.Parse("2015-12-16"),
                    ReturnPlansDate = DateTime.Parse("2016-01-16"),
                    ReturnDate = DateTime.Parse("2016-01-09")
                },
                new LibraryCard
                {
                    IsbnCode = "978-4-905318-63-7",
                    UserId = "1004",
                    CheckoutDate = DateTime.Parse("2016-07-18"),
                    ReturnPlansDate = DateTime.Parse("2016-08-18"),
                    ReturnDate = DateTime.Parse("2016-07-20")
                },
                new LibraryCard
                {
                    IsbnCode = "978-4-905318-63-7",
                    UserId = "2001",
                    CheckoutDate = DateTime.Parse("2018-06-11"),
                    ReturnPlansDate = DateTime.Parse("2018-07-11"),
                    ReturnDate = DateTime.Parse("2018-06-30")
                },
                new LibraryCard
                {
                    IsbnCode = "978-4-905318-63-7",
                    UserId = "1004",
                    CheckoutDate = DateTime.Parse("2018-04-08"),
                    ReturnPlansDate = DateTime.Parse("2018-05-08"),
                    ReturnDate = null
                }
            };
        }

        /** ユーザの簡易生成 */
        private static List<User> UserList()
        {
            return new List<User> {
                new User
                {
                    Id = "1001",
                    Name = "Hoge",
                    Password = "p@$$w0rd_01"
                },
                new User
                {
                    Id = "1004",
                    Name = "Fuga",
                    Password = "p@$$w0rd_02"
                },
                new User
                {
                    Id = "2001",
                    Name = "Piyo",
                    Password = "p@$$w0rd_03"
                }
            };
        }
    }
}
