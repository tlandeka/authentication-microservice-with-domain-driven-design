package com.tomo.mcauthentication.ddd.domain;

import com.tomo.mcauthentication.ddd.AssertionConcern;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class RootEntity extends BusinessRuleValidator {

}
