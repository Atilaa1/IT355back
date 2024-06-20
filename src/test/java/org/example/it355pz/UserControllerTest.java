package org.example.it355pz;


import org.example.it355pz.controller.UserController;
import org.example.it355pz.model.UsersEntity;
import org.example.it355pz.model.enums.RoleType;
import org.example.it355pz.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsers() throws Exception {
        UsersEntity user1 = new UsersEntity("username1", "password1", "mail1@example.com", RoleType.ROLE_USER);
        UsersEntity user2 = new UsersEntity("username2", "password2", "mail2@example.com", RoleType.ROLE_ADMIN);
        user1.setId(1);
        user2.setId(2);
        List<UsersEntity> usersList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(usersList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("username1"))
                .andExpect(jsonPath("$[0].mail").value("mail1@example.com"))
                .andExpect(jsonPath("$[0].role").value("ROLE_USER"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].username").value("username2"))
                .andExpect(jsonPath("$[1].mail").value("mail2@example.com"))
                .andExpect(jsonPath("$[1].role").value("ROLE_ADMIN"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById() throws Exception {
        UsersEntity user = new UsersEntity("username1", "password1", "mail1@example.com", RoleType.ROLE_USER);
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("username1"))
                .andExpect(jsonPath("$.mail").value("mail1@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void createUser() throws Exception {
        UsersEntity user = new UsersEntity("username1", "password1", "mail1@example.com", RoleType.ROLE_USER);
        user.setId(1);

        when(userService.saveUser(any(UsersEntity.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"username1\", \"password\":\"password1\", \"mail\":\"mail1@example.com\", \"role\":\"ROLE_USER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("username1"))
                .andExpect(jsonPath("$.mail").value("mail1@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));

        verify(userService, times(1)).saveUser(any(UsersEntity.class));
    }

    @Test
    void updateUser() throws Exception {
        UsersEntity user = new UsersEntity("updatedUsername", "updatedPassword", "updatedMail@example.com", RoleType.ROLE_ADMIN);
        user.setId(1);

        when(userService.saveUser(any(UsersEntity.class))).thenReturn(user);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"updatedUsername\", \"password\":\"updatedPassword\", \"mail\":\"updatedMail@example.com\", \"role\":\"ROLE_ADMIN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("updatedUsername"))
                .andExpect(jsonPath("$.mail").value("updatedMail@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_ADMIN"));

        verify(userService, times(1)).saveUser(any(UsersEntity.class));
    }

    @Test
    void deleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1);
    }
}
