
package com.anrisoftware.resources.binary.internal.binaries;

/*-
 * #%L
 * Resources :: Binary
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.anrisoftware.resources.binary.external.Binaries;
import com.anrisoftware.resources.binary.external.BinariesFactory;
import com.anrisoftware.resources.binary.internal.resources.BinaryResourceModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the binary resources factories.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class BinariesResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new BinaryResourceModule());
		install(new FactoryModuleBuilder().implement(Binaries.class,
				BinariesImpl.class).build(BinariesFactory.class));
		install(new FactoryModuleBuilder().implement(BinariesWorker.class,
				BinariesWorker.class).build(BinariesWorkerFactory.class));
	}

}
