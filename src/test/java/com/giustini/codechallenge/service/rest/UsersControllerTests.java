package com.giustini.codechallenge.service.rest;

import com.giustini.codechallenge.models.User;
import com.giustini.codechallenge.services.UsersService;
import com.giustini.codechallenge.services.rest.UsersController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.giustini.codechallenge.builders.UserBuilder.anUserBuilder;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;


    @Test
    public void GetUsersRestControllerTest() throws Exception {

        User user = anUserBuilder()
                .withId(1)
                .build();

        when(usersService.getUsers()).thenReturn(singletonList(user));

        mvc.perform(get("/users/getusers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(user.getId())));
    }

    @Test
    public void GetUserByIdOKRestControllerTest() throws Exception {

        User user = anUserBuilder()
                .withId(1)
                .build();

        when(usersService.getUserById(any(Integer.class))).thenReturn(user);

        mvc.perform(get("/users/getusersById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())));
    }

    @Test
    public void GetUserByIdInvalidUserIdRestControllerTest() throws Exception {

        mvc.perform(get("/users/getusersById/someInvalidUserId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
