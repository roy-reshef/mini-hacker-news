package reshef.minihackernews.api.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import reshef.minihackernews.api.Application;
import reshef.minihackernews.api.dtos.NewPostResponseDto;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.services.PostsService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:test.properties")
public class PostsResourceTest {
    private MockMvc mockMvc;

    @MockBean
    private PostsService service;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private String exampleNewPostJson = "{" +
            "\"author\":\"roy\"," +
            "\"title\":\"post title\"," +
            "\"text\":\"this is the content of the test post\"" +
            "}";

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddPost() throws Exception {
        String expecteId = "1234";
        Mockito.when(
                service.add(Mockito.any(Post.class))).thenReturn(expecteId);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/minihackernews")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleNewPostJson.getBytes())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(instanceOf(NewPostResponseDto.class)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        NewPostResponseDto responseDto = (NewPostResponseDto)result.getAsyncResult();

        assertEquals(expecteId, responseDto.getId());
    }
}