package com.stevekung.springboot.elasticsearch.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

import com.stevekung.springboot.elasticsearch.model.User;
import com.stevekung.springboot.elasticsearch.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User entity)
    {
        return this.userRepository.save(entity);
    }

    @Override
    public List<User> findAll()
    {
        return StreamUtils.createStreamFromIterator(this.userRepository.findAll().iterator()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<User> findById(String id)
    {
        return this.userRepository.findById(id);
    }

    @Override
    public void deleteById(String id)
    {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> findByAgeBetween(int startAge, int endAge)
    {
        return this.findAll().stream().filter(userx ->
        {
            var start = userx.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            var end = LocalDate.now();
            var years = ChronoUnit.YEARS.between(start, end);
            return years >= startAge && years <= endAge;
        }).toList();
    }

    @Override
    public List<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName)
    {
        return this.userRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
    }

    @Override
    public List<User> findByInsuranceForm_Status(String status)
    {
        return this.findByInsuranceForm_Status(status);
    }

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public SearchHits<User> search(String firstName)
    {
        // Get all item with given name
        Query query = NativeQuery.builder().withQuery(q -> q.match(m -> m.field("firstName").query(firstName))).build();
        return this.elasticsearchOperations.search(query, User.class);
    }
}