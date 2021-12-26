package com.one2one.services;

import com.one2one.entities.Subject;
import com.one2one.repositories.SubjectRepository;
import com.one2one.utils.PaginationParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

import static com.one2one.utils.StringUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;
    private final EntityManager em;

    public Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Integer page, Integer size) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> query = cb.createQuery(Subject.class);
        Root<Subject> root = query.from(Subject.class);
        query.select(root);

        if (isNotEmpty(search)) {
            List<Predicate> predicates = new ArrayList<>();
            for (String s : searchable) {
                predicates.add(cb.like(root.get(s), "%" + search + "%"));
            }
            query.where(cb.or(predicates.toArray(new Predicate[]{})));
        }

        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }

        TypedQuery<Subject> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<Subject> result = tQuery.setFirstResult(Math.max((page - 1), 0) * size)
                .setMaxResults(size)
                .getResultList();

        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getdata(maps, page, total, size, result);

        return maps;
    }

    public List<Subject> findAll(String[] sortable, String sortBy) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> query = cb.createQuery(Subject.class);
        Root<Subject> root = query.from(Subject.class);
        query.select(root);

        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }

        TypedQuery<Subject> tQuery = em.createQuery(query);

        return tQuery.getResultList();
    }

    @Transactional
    public Subject save(Subject subject) {
        return repository.save(subject);
    }

    @Transactional
    public Subject update(Subject subject) {
        return repository.save(subject);
    }

    public Optional<Subject> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Subject> findBySubjectName(String subjectName) {
        return repository.findBySubjectName(subjectName);
    }

    public Optional<Subject> findBySubjectNameBn(String subjectNameBn) {
        return repository.findBySubjectNameBn(subjectNameBn);
    }

    public void delete(Subject subject) {
        repository.delete(subject);
    }
}

