package com.stevekung.springboot.elasticsearch.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import com.stevekung.springboot.elasticsearch.model.InsuranceForm;
import com.stevekung.springboot.elasticsearch.model.User;
import com.stevekung.springboot.elasticsearch.repo.InsuranceFormRepository;
import com.stevekung.springboot.elasticsearch.repo.UserRepository;
import com.stevekung.springboot.elasticsearch.utils.DateUtils;
import com.stevekung.springboot.elasticsearch.utils.Utils;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataSetup
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsuranceFormRepository insuranceFormRepository;

    @PostConstruct
    public void setupData()
    {
        this.userRepository.deleteAll();
        this.insuranceFormRepository.deleteAll();

        var userList = new ArrayList<User>();

        var user = new User();
        user.setId(Utils.randomUUID());
        user.setPrefix("Mr.");
        user.setFirstName("Wasinthorn");
        user.setLastName("Suksri");
        user.setPhoneNumber("088-544-2355");
//        user.setBirthDate(DateUtils.date("02/02/1999"));
        user.setCardId("16799248692387");
        this.setUserImage(user);
        user.setEmail("wasinthorn.suksri@gmail.com");
        user.setAddress("Bangkok, Thailand");

        userList.add(user);

        user = new User();
        user.setId(Utils.randomUUID());
        user.setPrefix("Mr.");
        user.setFirstName("Muha");
        user.setLastName("Haha");
        user.setPhoneNumber("02-434-4352");
//        user.setBirthDate(DateUtils.date("02/02/2006"));
        user.setCardId("8375598208475");
        this.setUserImage(user);
        user.setEmail("muha@gmail.com");
        user.setAddress("Krungsri, Thailand");

        userList.add(user);

        user = new User();
        user.setId(Utils.randomUUID());
        user.setPrefix("Mrs.");
        user.setFirstName("Somya");
        user.setLastName("Yasom");
        user.setPhoneNumber("05-123-9707");
//        user.setBirthDate(DateUtils.date("02/02/1998"));
        user.setCardId("43899650938896");
        this.setUserImage(user);
        user.setEmail("som@hotmail.com");
        user.setAddress("Thep, Thailand");

        userList.add(user);

        user = new User();
        user.setId(Utils.randomUUID());
        user.setPrefix("Mr.");
        user.setFirstName("Awow");
        user.setLastName("Meow");
        user.setPhoneNumber("087-235-4577");
//        user.setBirthDate(DateUtils.date("02/02/2010"));
        user.setCardId("43899650938896");
        this.setUserImage(user);
        user.setEmail("awow@hotmail.com");
        user.setAddress("Gree, Thailand");

        userList.add(user);

        this.userRepository.saveAll(userList);

        this.userRepository.findByFirstNameLike("Was").ifPresentOrElse(userx ->
        {
            System.out.println(userx.getFullName());

            var form = new InsuranceForm();
            form.setId(Utils.randomUUID());
            form.setUserId(userx.getId());
            form.setStatus("draft");
            userx.setInsuranceForm(this.insuranceFormRepository.save(form));

            System.out.println(userx.getInsuranceForm());
            this.userRepository.save(userx);

        }, () -> System.out.println("Not found"));

        this.userRepository.findByAddressContaining("Krungsri").ifPresentOrElse(userx ->
        {
            System.out.println(userx.getFullName());

            var form = new InsuranceForm();
            form.setId(Utils.randomUUID());
            form.setUserId(userx.getId());
            form.setStatus("approved");
            userx.setInsuranceForm(this.insuranceFormRepository.save(form));

            System.out.println(userx.getInsuranceForm());
            this.userRepository.save(userx);

        }, () -> System.out.println("Not found"));

        this.userRepository.findByPrefixStartingWith("Mrs").forEach(userx ->
        {
            System.out.println(userx.getFullName());

            var form = new InsuranceForm();
            form.setId(Utils.randomUUID());
            form.setUserId(userx.getId());
            form.setStatus("rejected");
            userx.setInsuranceForm(this.insuranceFormRepository.save(form));

            System.out.println(userx.getInsuranceForm());
            this.userRepository.save(userx);
        });

        System.out.println(this.userRepository.findByInsuranceForm_Status("approved"));

        System.out.println(this.userRepository.findByEmailContains("gmail.com"));

        System.out.println(this.userRepository.findByInsuranceFormIsNull());

        System.out.println(this.userRepository.findByPhoneNumberStartingWith("08"));
        
        
        for (var c : this.search("Awow").getSearchHits())
        {
            System.out.println(c.getContent());
        }
    }
    
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public SearchHits<User> search(String firstName)
    {
        // Get all item with given name
        Query query = NativeQuery.builder().withQuery(q -> q.match(m -> m.field("firstName").query(firstName))).build();
        return this.elasticsearchOperations.search(query, User.class);
    }

    private void setUserImage(User user)
    {
        try
        {
            var resource = this.getClass().getClassLoader().getResource("images.png");
            user.setImage(FileUtils.readFileToByteArray(new File(resource.toURI())));
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}