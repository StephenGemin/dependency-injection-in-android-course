package com.techyourchance.journeytodependencyinjection.common.di.component;

import com.techyourchance.journeytodependencyinjection.common.di.module.ApplicationModule;
import com.techyourchance.journeytodependencyinjection.common.di.module.NetworkModule;
import com.techyourchance.journeytodependencyinjection.common.di.module.PresentationModule;
import com.techyourchance.journeytodependencyinjection.common.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stephen Gemin on 9/13/2019
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    ServiceComponent newServiceComponent(ServiceModule serviceModule);
}
