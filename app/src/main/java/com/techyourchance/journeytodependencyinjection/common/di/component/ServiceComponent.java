package com.techyourchance.journeytodependencyinjection.common.di.component;

import com.techyourchance.journeytodependencyinjection.common.di.module.ServiceModule;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by Stephen Gemin on 9/14/2019
 */
@Subcomponent(modules = ServiceModule.class)
public interface ServiceComponent {

}
