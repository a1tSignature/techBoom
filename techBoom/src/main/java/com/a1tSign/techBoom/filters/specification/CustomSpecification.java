package com.a1tSign.techBoom.filters.specification;

import com.a1tSign.techBoom.data.entity.Item;
import com.a1tSign.techBoom.filters.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public abstract class CustomSpecification<T> implements Specification<T> {

    protected List<SearchCriteria> list;

    public void addCriteria(SearchCriteria criteria) {
        list.add(criteria);
    }

    protected void addToPredicates(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                   Root<T> root) {
        for (SearchCriteria criteria : list) {
            switch (criteria.getComparison()) {
                case GREATER_THAN:
                    predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case LESS_THAN:
                    predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case EQUAL:
                    predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case MATCH:
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case MATCH_START:
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase()));
                    break;
                case MATCH_END:
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case IN:
                    predicates.add(criteriaBuilder.in(root.get(criteria.getKey()))
                            .value(criteria.getValue()));
                    break;
                case NOT_IN:
                    predicates.add(criteriaBuilder.not(root.get(criteria.getKey()))
                            .in(criteria.getValue()));
                    break;
                case IS_NULL:
                    predicates.add(criteriaBuilder.isNull(root.get(criteria.getKey())));
                    break;
            }
        }

    }
}
