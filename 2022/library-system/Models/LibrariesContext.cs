using Microsoft.EntityFrameworkCore;

namespace library_system.Models
{
    public class LibrariesContext : DbContext
    {
        public LibrariesContext(DbContextOptions<LibrariesContext> options) : base(options) { }
        public DbSet<Entity.LibraryCard> LibraryCards { get; set; }
        public DbSet<Entity.User> Users { get; set; }
    }
}
