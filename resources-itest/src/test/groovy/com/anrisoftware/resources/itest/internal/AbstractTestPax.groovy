/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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
 */

package com.anrisoftware.resources.itest.internal

import static org.ops4j.pax.exam.CoreOptions.*

import javax.inject.Inject

import org.ops4j.pax.exam.Configuration
import org.ops4j.pax.exam.Option
import org.ops4j.pax.exam.util.PathUtils
import org.osgi.framework.BundleContext

abstract class AbstractTestPax {

    @Inject
    BundleContext bc

    @Configuration
    Option[] config() {
        def options = []
        options.addAll createConfig(options)
        options
    }

    List<Option> createConfig(List<Option> options) {
        options << junitBundles()
        options << systemProperty('logback.configurationFile').value("file:${PathUtils.baseDir}/src/test/resources/logback-test.xml")
        options << mavenBundle("org.apache.felix", "org.apache.felix.scr", "2.1.6")
        options << mavenBundle("org.slf4j", "slf4j-api").versionAsInProject()
        options << mavenBundle("ch.qos.logback", "logback-classic").versionAsInProject()
        options << mavenBundle("ch.qos.logback", "logback-core").versionAsInProject()
        options << mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.aopalliance").versionAsInProject()
        options << mavenBundle("com.google.guava", "guava").versionAsInProject()
        options << mavenBundle("org.codehaus.groovy", "groovy").versionAsInProject()
        options << mavenBundle("com.google.inject", "guice").versionAsInProject()
        options << mavenBundle("com.google.inject.extensions", "guice-assistedinject").versionAsInProject().noStart()
        options << mavenBundle("commons-io", "commons-io").versionAsInProject().noStart()
        options << mavenBundle("org.apache.commons", "commons-lang3").versionAsInProject().noStart()
        options << mavenBundle("com.anrisoftware.globalpom", "globalpom-log").versionAsInProject()
        options << mavenBundle("com.anrisoftware.resources", "resources-api").versionAsInProject()
        options << mavenBundle("com.anrisoftware.resources", "resources-getbundle").versionAsInProject()
    }
}
