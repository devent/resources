package com.anrisoftware.resources.texts

import java.nio.charset.Charset
import java.util.Locale


import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.resources.api.TextResource
import com.anrisoftware.resources.api.Texts
import com.anrisoftware.resources.api.TextsFactory
import com.anrisoftware.resources.binary.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesResourcesMapsModule
import com.google.common.base.Charsets
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.name.Names

/**
 * Creates the environment to unit test the texts resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractTextResourceTestUtils extends TestUtils {

	def modules

	Injector injector

	TextsFactory factory

	@Before
	void before() {
		modules = lazyCreateModules()
		injector = injector == null ? Guice.createInjector(modules) : injector
		factory = injector.getInstance(TextsFactory)
	}

	/**
	 * Creates the needed modules if they are not already have been created.
	 */
	def lazyCreateModules() {
		modules == null ?
				[
					textsModule,
					textsMapModule,
					binariesModule,
					binariesMapModule,
					characterSetModule,
				].flatten()
				: modules
	}

	/**
	 * Returns the module that binds the texts resources.
	 */
	abstract getTextsModule()

	/**
	 * Returns the module that binds the texts map. 
	 */
	abstract getTextsMapModule()

	/**
	 * Returns the module that binds the binaries resources.
	 */
	def getBinariesModule() {
		new BinariesResourcesModule()
	}

	/**
	 * Returns the module that binds the binaries map.
	 */
	def getBinariesMapModule() {
		new BinariesResourcesMapsModule()
	}

	/**
	 * Returns the module that binds the default character set for texts.
	 */
	def getCharacterSetModule() {
		new AbstractModule() {
					@Override
					protected void configure() {
						bind(Charset).annotatedWith(Names.named("texts-default-charset")) toInstance Charsets.UTF_8
					}
				}
	}

	void "micro-benchmark get the same text resource for different languages"() {
		Logger.getLogger(TextsImpl).setLevel(Level.INFO)
		Locale german = Locale.GERMAN
		Locale russian = new Locale("ru")
		Locale english = Locale.ENGLISH
		def baseName = "TextsWithDefaultCharset"
		def classLoader = getClass().classLoader
		Texts texts = factory.create baseName, classLoader

		TextResource text
		long current
		long now
		int max = 1

		printf "Lookup first time, difference languages:%n"
		Thread.sleep 1000
		current = System.currentTimeMillis()
		text = texts.textResource "hello", german
		text = texts.textResource "hello", russian
		text = texts.textResource "hello", english
		now = System.currentTimeMillis()
		printf "system time : %.3f%n", (now-current) / 3

		printf "Lookup second time, difference languages:%n"
		(0..10).each {
			Thread.sleep 1000
			current = System.currentTimeMillis()
			(0..max).each {
				text = texts.textResource "hello", german
				text = texts.textResource "hello", russian
				text = texts.textResource "hello", english
			}
			now = System.currentTimeMillis()
			printf "system time (%d): %.3f%n", max, (now-current) / max / 3
			max *= 2
		}
	}
}
