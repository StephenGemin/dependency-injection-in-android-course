package com.techyourchance.journeytodependencyinjection.common.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Stephen Gemin on 9/14/2019
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PresentationScope {
}
