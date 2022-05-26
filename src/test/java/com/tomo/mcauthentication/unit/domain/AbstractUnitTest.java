package com.tomo.mcauthentication.unit.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractUnitTest {

    protected MockitoSession mockito;

}
