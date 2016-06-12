package com.ricamgar.notify.base.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ricardo.campos on 07/01/16.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PerService {
}
