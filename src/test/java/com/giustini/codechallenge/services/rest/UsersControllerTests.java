package com.giustini.codechallenge.services.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giustini.codechallenge.exceptions.UserNotFoundException;
import com.giustini.codechallenge.models.User;
import com.giustini.codechallenge.services.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.giustini.codechallenge.models.builders.UserBuilder.aUserBuilder;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void GetUsersRestControllerTest() throws Exception {

        User user = aUserBuilder()
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

        User user = aUserBuilder()
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

    @Test
    public void GetUserByIdNotFoundRestControllerTest() throws Exception {

        when(usersService.getUserById(any(Integer.class)))
                .thenThrow(new UserNotFoundException(any(Integer.class)));

        mvc.perform(get("/users/getusersById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void CreateUserOkRestControllerTest() throws Exception {

        User user = aUserBuilder()
                .withId(1)
                .build();

        when(usersService.createUser(any(User.class))).thenReturn(user);

        mvc.perform(post("/users/createUsers")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(user.getId())));
    }

    @Test
    public void CreateUserInvalidInputRestControllerTest() throws Exception {

        mvc.perform(post("/users/createUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void UpdateUserByIdOKRestControllerTest() throws Exception {

        User user = aUserBuilder()
                .withId(1)
                .build();

        when(usersService.updateUserById(any(Integer.class), any(User.class))).thenReturn(user);

        mvc.perform(put("/users/updateUsersById/1")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())));
    }

    @Test
    public void UpdateUserByIdNotFoundRestControllerTest() throws Exception {

        User user = aUserBuilder()
                .withId(1)
                .build();

        when(usersService.updateUserById(any(Integer.class), any(User.class)))
                .thenThrow(new UserNotFoundException(1));

        mvc.perform(put("/users/updateUsersById/1")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void UpdateUserByIdInvalidUserIdRestControllerTest() throws Exception {

        mvc.perform(put("/users/updateUsersById/someInvalidUserId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void DeleteUserByIdOkRestControllerTest() throws Exception {

        doNothing().when(usersService).deleteUserById(any(Integer.class));

        mvc.perform(delete("/users/deleteUsersById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void DeleteUserByIdInvalidUserIdRestControllerTest() throws Exception {

        mvc.perform(delete("/users/deleteUsersById/someInvalidUserId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void DeleteUserByIdNotFoundRestControllerTest() throws Exception {

        doThrow(new UserNotFoundException(1)).when(usersService).deleteUserById(any(Integer.class));

        mvc.perform(delete("/users/deleteUsersById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
