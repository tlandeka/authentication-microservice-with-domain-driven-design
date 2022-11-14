package com.tomo.mcauthentication.unit.domain;

import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.MockitoSession;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = { "unit" })
public abstract class AbstractUnitTest {

    protected MockitoSession mockito;

}
