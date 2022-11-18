package training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.dto.BookStatusTypeDTO;
import training.service.LibraryCardService;

/**
 * 貸出表ドメインに関わるコントローラー。
 */
@RestController
@RequestMapping("/api/libcard")
public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;

    @GetMapping("/bookStatusType")
    public ResponseEntity<Object> getBookStatusType(@RequestParam("isbnCode") String isbnCode) {
        BookStatusTypeDTO dto = libraryCardService.getBookStatusType(isbnCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
