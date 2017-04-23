package pers.westwind.learn.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pers.westwind.learn.web.controller.HomeController;

/**
 * Created by Administrator on 2017/4/12.
 */
public class HomeControllerTest {
    @Test
    public void testsHomePage() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}
