package com.a1tSign.techBoom.filters.specification;

import com.a1tSign.techBoom.data.entity.Statistic;
import com.a1tSign.techBoom.filters.SearchCriteria;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StatisticSpecification extends CustomSpecification<Statistic> {
    public StatisticSpecification(List<SearchCriteria> list) {
        super(list);
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Statistic> root, @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        var predicates = new ArrayList<Predicate>();
        addToPredicates(predicates, criteriaBuilder, root);

        return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
