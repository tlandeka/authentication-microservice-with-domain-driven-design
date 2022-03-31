package com.tomo.mcauthentication.integration.infrastructure;

import com.tomo.mcauthentication.integration.BaseServiceTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class RandomRestControllerTest extends BaseServiceTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    @Before
    public void init() {
        if (mockMvc == null) {
            mockMvc = webAppContextSetup(context)
                    .apply(springSecurity())
                    .build();
        }
    }

    @Test
    public void shouldReturnReferralCodeIfAlreadyGeneratedForUser() throws Exception {
        mockMvc.perform(get("/random"))
                .andExpect(status().isOk())
                .andExpect(content().string("\"" + "\""));
    }
}
