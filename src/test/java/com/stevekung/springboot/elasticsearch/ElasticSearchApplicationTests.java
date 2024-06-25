package com.stevekung.springboot.elasticsearch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevekung.springboot.elasticsearch.model.User;
import com.stevekung.springboot.elasticsearch.repo.UserRepository;
import com.stevekung.springboot.elasticsearch.utils.DateUtils;
import com.stevekung.springboot.elasticsearch.utils.Utils;

@SpringBootTest
@AutoConfigureMockMvc
class ElasticSearchApplicationTests
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() throws JsonProcessingException, Exception
    {
        var user = new User();
        user.setId(Utils.randomUUID());
        user.setPrefix("Mr.");
        user.setFirstName("Wasinthorn");
        user.setLastName("Suksri");
        user.setPhoneNumber("088-544-2355");
        user.setBirthDate(DateUtils.date("02/02/1999"));
        user.setCardId("16799248692387");
        user.setEmail("wasinthorn.suksri@gmail.com");
        user.setAddress("Bangkok, Thailand");

        this.mockMvc.perform(post("/api/v1/users/").contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());

        var userEntity = this.userRepository.findByFirstNameLike("Wasinthorn");

        userEntity.ifPresent(userx ->
        {
            assertThat(userx.getFirstName()).isEqualTo("Wasinthorn");
        });
    }
}