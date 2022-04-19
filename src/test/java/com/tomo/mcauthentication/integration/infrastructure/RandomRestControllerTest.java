package com.tomo.mcauthentication.integration.infrastructure;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class RandomRestControllerTest /*extends BaseServiceTest*/ {

    /*@Autowired
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
    }*/
}
