package hello;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SuppressWarnings("Duplicates")
    public void shouldReturnDefaultMessage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        ResultHandler resultHandler = MockMvcResultHandlers.print();

        ResultMatcher resultMatcher_status_ok = MockMvcResultMatchers.status().isOk();

        Matcher<String> matcher = Matchers.containsString("Hello World");
        ResultMatcher resultMatcher_content_matcher = MockMvcResultMatchers.content().string(matcher);

        mockMvc.perform(requestBuilder)
                .andDo(resultHandler)
                .andExpect(resultMatcher_status_ok)
                .andExpect(resultMatcher_content_matcher);
    }
}