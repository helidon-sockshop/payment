package io.helidon.examples.sockshop.payment.jpa;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses relational database (via JPA) as a backend data store.
 */
@ApplicationScoped
@Specializes
public class JpaPaymentRepository extends DefaultPaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveAuthorization(Authorization authorization) {
        em.persist(authorization);
    }

    @Override
    @Transactional
    public Collection<? extends Authorization> findAuthorizationsByOrder(String orderId) {
        String jql = "select a from Authorization as a where a.orderId = :orderId";
        TypedQuery<Authorization> query = em.createQuery(jql, Authorization.class);
        query.setParameter("orderId", orderId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void clear() {
        em.createQuery("delete from Authorization").executeUpdate();
    }
}
