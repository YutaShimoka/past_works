using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using library_system.Models;
using library_system.Models.Entity;

namespace library_system.Controllers
{
    public class LibraryCardController : Controller
    {
        private readonly LibrariesContext _context;

        public LibraryCardController(LibrariesContext context)
        {
            _context = context;
        }

        // GET: LibraryCard
        public async Task<IActionResult> Index(string searchWord)
        {
            LibraryCardMgr mgr = new LibraryCardMgr();
            List<LibraryCardUI> libraryCardUIList = new List<LibraryCardUI>();
            List<User> userList = await _context.Users.ToListAsync();

            if (searchWord == null)
            // Find All
            {
                libraryCardUIList = await _context.LibraryCards.Select(x => LibraryCardUI.Of(x, userList)).ToListAsync();
                mgr.LibraryCardUI = libraryCardUIList;
            }
            else
            // Find by IsbnCode
            {
                libraryCardUIList = await _context.LibraryCards
                    .Where(model => model.IsbnCode == searchWord)
                    .Select(x => LibraryCardUI.Of(x, userList)).ToListAsync();
                mgr.LibraryCardUI = libraryCardUIList;
            }
            return View(mgr);
        }

        // GET: LibraryCard/Details/5
        public async Task<IActionResult> Details(long? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var libraryCard = await _context.LibraryCards
                .FirstOrDefaultAsync(m => m.CheckoutNumber == id);
            if (libraryCard == null)
            {
                return NotFound();
            }

            List<User> userList = await _context.Users.ToListAsync();
            return View(LibraryCardUI.Of(libraryCard, userList));
        }

        // GET: LibraryCard/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: LibraryCard/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("CheckoutNumber,IsbnCode,UserId,CheckoutDate,ReturnPlansDate,ReturnDate")] LibraryCard libraryCard)
        {
            if (ModelState.IsValid)
            {
                List<String> userIdList = await _context.Users.Select(x => x.Id).ToListAsync();
                if (!userIdList.Contains(libraryCard.UserId))
                {
                    return View("NotFound");
                }
                libraryCard.CheckoutDate = DateTime.Now;
                libraryCard.ReturnPlansDate = DateTime.Now.AddMonths(1); // 1か月後
                libraryCard.ReturnDate = null;
                _context.Add(libraryCard);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(libraryCard);
        }

        // GET: LibraryCard/Edit/5
        public async Task<IActionResult> Edit(long? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var libraryCard = await _context.LibraryCards.FindAsync(id);
            if (libraryCard == null)
            {
                return NotFound();
            }
            return View(libraryCard);
        }

        // POST: LibraryCard/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(long id, [Bind("CheckoutNumber,IsbnCode,UserId,CheckoutDate,ReturnPlansDate,ReturnDate")] LibraryCard libraryCard)
        {
            if (id != libraryCard.CheckoutNumber)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(libraryCard);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!LibraryCardExists(libraryCard.CheckoutNumber))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(libraryCard);
        }

        // GET: LibraryCard/Delete/5
        public async Task<IActionResult> Delete(long? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var libraryCard = await _context.LibraryCards
                .FirstOrDefaultAsync(m => m.CheckoutNumber == id);
            if (libraryCard == null)
            {
                return NotFound();
            }

            List<User> userList = await _context.Users.ToListAsync();
            return View(LibraryCardUI.Of(libraryCard, userList));
        }

        // POST: LibraryCard/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(long id)
        {
            var libraryCard = await _context.LibraryCards.FindAsync(id);
            _context.LibraryCards.Remove(libraryCard);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool LibraryCardExists(long id)
        {
            return _context.LibraryCards.Any(e => e.CheckoutNumber == id);
        }

        //<summary>クライアント利用用途に絞ったパラメタ</summary>
        public class LibraryCardUI
        {
            public long CheckoutNumber { get; set; }
            public string IsbnCode { get; set; }
            public string UserName { get; set; }
            public string CheckoutDate { get; set; }
            public string ReturnPlansDate { get; set; }
            public string ReturnDate { get; set; }
            public string SearchWord { get; set; }

            public static LibraryCardUI Of(LibraryCard m, List<User> userList)
            {
                return new LibraryCardUI
                {
                    CheckoutNumber = m.CheckoutNumber,
                    IsbnCode = m.IsbnCode,
                    UserName = userList.FirstOrDefault(user => user.Id == m.UserId).Name,
                    CheckoutDate = m.CheckoutDate.ToString("yyyy/MM/dd"),
                    ReturnPlansDate = m.ReturnPlansDate.ToString("yyyy/MM/dd"),
                    ReturnDate = m.ReturnDate.HasValue ? m.ReturnDate.Value.ToString("yyyy/MM/dd") : string.Empty
                    // see https://stackoverflow.com/questions/1445766/c-sharp-nullabledatetime-to-string
                };
            }
        }
    }
}
