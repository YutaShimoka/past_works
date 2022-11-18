package training.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.dto.BookStatusTypeDTO;
import training.entity.LibraryCard;
import training.repository.LibraryCardRepository;

/**
 * 貸出表ドメインに関わるサービス。
 */
@Service
public class LibraryCardService {

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    public BookStatusTypeDTO getBookStatusType(String isbnCode) {
        LibraryCard entity = libraryCardRepository.findFirstByIsbnCodeOrderByCheckoutDate(isbnCode)
                .orElseThrow(() -> new EntityNotFoundException());
        return new BookStatusTypeDTO(entity);
    }

}
