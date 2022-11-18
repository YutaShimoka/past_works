package training.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import training.entity.LibraryCard;

/**
 * 貸出表ドメインに関わるリポジトリ。
 */
@Repository
public class LibraryCardRepository {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public Optional<LibraryCard> findFirstByIsbnCodeOrderByCheckoutDate(String isbnCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LibraryCard> cq = cb.createQuery(LibraryCard.class);
        Root<LibraryCard> rt = cq.from(LibraryCard.class);
        cq.select(rt).where(
                cb.equal(rt.get("isbnCode"), isbnCode))
                .orderBy(cb.desc(rt.get("checkoutDate")));
        return em.createQuery(cq)
                .getResultStream()
                .findFirst();
    }
}
