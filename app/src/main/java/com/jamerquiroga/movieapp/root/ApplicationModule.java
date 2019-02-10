package com.jamerquiroga.movieapp.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Clase cuyo método proporciona dependencias
 */
@Module
public class ApplicationModule {

    private Application gApplication;

    public ApplicationModule(Application gApplication) {
        this.gApplication = gApplication;
    }

    /**
     * método que le dicen a Dagger cómo queremos construir y presentar una dependencia
     * @return
     */

    @Provides
    @Singleton
    public Context provideContext(){
        return gApplication;
    }
}
