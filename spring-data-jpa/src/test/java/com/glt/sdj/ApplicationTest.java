package com.glt.sdj;


import com.glt.sdj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.boot.test.mock.mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Application.class)
public class ApplicationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    /*@Test
    public void helloTest() throws Exception {
        BDDMockito.given(userService.hello()).willReturn("hello");

        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }*/
}
