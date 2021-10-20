package com.belean.mall.tiny.it;

import com.belean.mall.tiny.Application;
import com.belean.mall.tiny.controller.EsProductController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @author belean
 * @date 2021/10/7
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("it")
public class EsProductControllerIT {

    private MockMvc mockMvc;

    @Value("${test.type}")
    private String testType;

    //@Autowired
    //private WebApplicationContext wac;

    @Autowired
    private EsProductController productController;

    @Before
    public void setup() {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.productController).build();
    }

    @Test
    public void test() {
        Assert.assertEquals("integration-test", testType);
    }

    @Test
    public void testSearchSimple() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/esProduct/search/simple")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("pageNum", "0")
                        .param("pageSize", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        System.out.println(response.getContentAsString());
    }

}
