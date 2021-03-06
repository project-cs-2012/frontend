/**
 * Copyright 2012 Ericsson, Uppsala University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Uppsala University
 *
 * Project CS course, Fall 2012
 *
 * Projekt DV/Project CS, is a course in which the students develop software for
 * distributed systems. The aim of the course is to give insights into how a big
 * project is run (from planning to realization), how to construct a complex
 * distributed system and to give hands-on experience on modern construction
 * principles and programming methods.
 *
 */
package project.cs.netinfservice.application;

import java.io.IOException;
import java.io.InputStream;

import project.cs.netinfservice.netinf.node.module.Module;
import project.cs.netinfutilities.UProperties;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Base class for maintaining a global application state.
 * 
 * @author Linus Sunde
 * @author Paolo Boschini
 * @author Thiago Costa Porto
 * 
 */
public class MainNetInfApplication extends Application {
    /** Debugging tag. */
    public static final String TAG = "MainNetInfApplication";
    
    /** Properties file. */
    public static final String PROPERTIES_FILE = "config.properties";

    /** Injector for injecting classes. */	
    private static Injector sInjector;

    /** The context for this application. */
    private static Context sContext;

    /**
     * Initializes netinf application, setting context and injector.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // Create the properties reader
        AssetManager assets = getApplicationContext().getResources().getAssets();
        try {
			InputStream is = assets.open(PROPERTIES_FILE);
			UProperties.INSTANCE.init(is);
			is.close();
		} catch (IOException e) {
			Log.e(TAG, "Could not initialize properties file.");
		}

        // Set context
        sContext = getApplicationContext();
        
        // Set Injector
        sInjector = Guice.createInjector(new Module());
    }

    /**
     * Returns the injector for injecting classes.
     * 
     * @return
     *      The injector
     */
    public static Injector getInjector() {
        return sInjector;
    }
    
    /**
     * Returns the current application context.
     * This is useful i.e. for registering broadcast receivers.
     * 
     * @return
     *      The application context.
     */
    public static Context getAppContext() {
        return sContext;
    }
}