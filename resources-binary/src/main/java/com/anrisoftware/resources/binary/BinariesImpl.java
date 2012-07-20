package com.anrisoftware.resources.binary;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.GetBundleWithClassLoader;
import com.anrisoftware.resources.GetBundleWithClassLoaderAndControl;
import com.anrisoftware.resources.GetBundleWithControl;
import com.anrisoftware.resources.api.Binaries;
import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

class BinariesImpl implements Binaries {

	private final BundlesMap bundles;

	private final GetBundle getBundle;

	private final BinariesWorkerFactory workerFactory;

	@AssistedInject
	BinariesImpl(BinariesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName) {
		this(workerFactory, bundles, new GetBundle(baseName));
	}

	@AssistedInject
	BinariesImpl(BinariesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName, @Assisted ClassLoader classLoader) {
		this(workerFactory, bundles, new GetBundleWithClassLoader(baseName,
				classLoader));
	}

	@AssistedInject
	BinariesImpl(BinariesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName, @Assisted ResourceBundle.Control control) {
		this(workerFactory, bundles,
				new GetBundleWithControl(baseName, control));
	}

	@AssistedInject
	BinariesImpl(BinariesWorkerFactory workerFactory, BundlesMap bundles,
			@Assisted String baseName, @Assisted ClassLoader classLoader,
			@Assisted ResourceBundle.Control control) {
		this(workerFactory, bundles, new GetBundleWithClassLoaderAndControl(
				baseName, classLoader, control));
	}

	private BinariesImpl(BinariesWorkerFactory workerFactory,
			BundlesMap bundles, GetBundle getBundle) {
		this.workerFactory = workerFactory;
		this.bundles = bundles;
		this.getBundle = getBundle;
	}

	@Override
	public ClassLoader getClassLoader() {
		return getBundle.getClassLoader();
	}

	@Override
	public String getBaseName() {
		return getBundle.getBaseName();
	}

	@Override
	public Control getControl() {
		return getBundle.getControl();
	}

	@Override
	public BinaryResource getResource(String name) throws ResourcesException {
		return getResource(name, null);
	}

	@Override
	public BinaryResource getResource(String name, Locale locale)
			throws ResourcesException {
		locale = locale == null ? Locale.getDefault() : locale;
		return workerFactory.create(name, locale, getBundle, bundles)
				.binaryResource();
	}

}
