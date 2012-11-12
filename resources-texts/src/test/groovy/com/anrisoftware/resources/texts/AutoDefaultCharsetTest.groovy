package com.anrisoftware.resources.texts

import java.nio.charset.Charset

import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.texts.ResourcesTextsCharsetModule;

/**
 * Test the text resources with an automatically set default charset.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AutoDefaultCharsetTest extends TextResourceTest {

	def getCharacterSetModule() {
		new ResourcesTextsCharsetModule()
	}
}
