/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fdsanalysis-keysgui-mainwindow. All rights reserved.
 */
package com.anrisoftware.resources.texts.central;

import static java.lang.reflect.Modifier.isStatic;
import static org.apache.commons.lang3.StringUtils.split;

import java.awt.event.KeyEvent;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.KeyStroke;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.resources.api.LocaleListener;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Centralized access to all text resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.4
 */
@Singleton
public class TextsResources {

	private final Texts texts;

	private final Texts actions;

	private final Texts mnemonics;

	private final Texts accelerators;

	private Reference<Map<String, Integer>> keyCodes;

	private final EventListenerSupport<LocaleListener> localeListeners;

	private Locale locale;

	@Inject
	TextsResources(TextsFactory textsFactory) {
		this.localeListeners = new EventListenerSupport<LocaleListener>(
				LocaleListener.class);
		this.texts = textsFactory.create("Texts");
		this.actions = textsFactory.create("Actions");
		this.mnemonics = textsFactory.create("ActionMnemonics");
		this.accelerators = textsFactory.create("ActionAccelerators");
		this.locale = Locale.getDefault();
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		localeListeners.fire().updateLocale(locale);
	}

	public String getText(String name) throws ResourcesException {
		return texts.getResource(name, locale).getText();
	}

	public String getTextFormat(String name, Object... args)
			throws ResourcesException {
		return texts.getResource(name, locale).getFormattedText(args);
	}

	public String getAction(String name) throws ResourcesException {
		return actions.getResource(name, locale).getText();
	}

	/**
	 * Loads a mnemonic text resource with the specified name and returns the
	 * mnemonic key code.
	 * <p>
	 * The resource can contain a key code name or the character.
	 * <ul>
	 * <li>{@code VK_A}</li>
	 * <li>{@code a}</li>
	 * </ul>
	 * 
	 * @param name
	 * @return
	 * @throws ResourcesException
	 */
	public int getMnemonic(String name) throws ResourcesException {
		String keyname = mnemonics.getResource(name, locale).getText();
		String[] keynames = split(keyname, ",");
		return getKeyCode(keynames[0]);
	}

	/**
	 * Loads a mnemonic text resource with the specified name and returns the
	 * displayed mnemonic index if such index was set in the resource.
	 * <p>
	 * To set the index, the mnemonic character and the index must be specified
	 * and separated with a comma:
	 * 
	 * <ul>
	 * <li>{@code VK_A,5}</li>
	 * <li>{@code a,5}</li>
	 * </ul>
	 * 
	 * @param name
	 *            the mnemonic text resource name.
	 * 
	 * @return displayed mnemonic index or -1. The value -1 means no index was
	 *         specified.
	 * 
	 * @throws ResourcesException
	 */
	public int getMnemonicIndex(String name) throws ResourcesException {
		String keyname = mnemonics.getResource(name, locale).getText();
		String[] keynames = split(keyname, ",");
		if (keynames.length == 2) {
			return Integer.valueOf(keynames[1]);
		} else {
			return -1;
		}
	}

	/**
	 * Loads a accelerator text resource with the specified name and returns the
	 * accelerator key for an action.
	 * <p>
	 * The accelerator can be just a key character or the key code name. In
	 * addition, the modifiers can be set as the modifiers names.
	 * 
	 * <ul>
	 * <li>{@code VK_A,ALT_DOWN_MASK,CTRL_DOWN_MASK}</li>
	 * <li>{@code a,ALT_DOWN_MASK,CTRL_DOWN_MASK}</li>
	 * </ul>
	 * 
	 * @param name
	 *            the accelerator text resource name.
	 * 
	 * @return accelerator {@link KeyStroke} or {@code null} if no accelerator
	 *         key was specified.
	 * 
	 * @throws ResourcesException
	 */
	public KeyStroke getAccelerator(String name) {
		try {
			String keyname = accelerators.getResource(name, locale).getText();
			String[] keynames = split(keyname, ",");
			int keycode = getKeyCode(keynames[0]);
			int modifiers = 0;
			for (int i = 1; i < keynames.length; i++) {
				modifiers |= getKeyCode(keynames[i]);
			}
			return KeyStroke.getKeyStroke(keycode, modifiers);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	private int getKeyCode(String keynames) {
		if (keynames.startsWith("VK_")) {
			Map<String, Integer> codes = getKeyCodes();
			return codes.get(keynames);
		}
		if (keynames.endsWith("_MASK")) {
			Map<String, Integer> codes = getKeyCodes();
			return codes.get(keynames);
		}
		return KeyEvent.getExtendedKeyCodeForChar(keynames.charAt(0));
	}

	private Map<String, Integer> getKeyCodes() {
		if (keyCodes == null || keyCodes.get() == null) {
			keyCodes = new SoftReference<Map<String, Integer>>(findKeys());
		}
		return keyCodes.get();
	}

	private Map<String, Integer> findKeys() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Field field : KeyEvent.class.getFields()) {
			if (isStatic(field.getModifiers())) {
				if (field.getName().startsWith("VK_")
						|| field.getName().endsWith("_MASK")) {
					try {
						map.put(field.getName(), field.getInt(null));
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					}
				}
			}
		}
		return map;
	}

	public void addTextsLocaleListeners(LocaleListener l) {
		localeListeners.addListener(l);
	}

	public void removeTextsLocaleListeners(LocaleListener l) {
		localeListeners.removeListener(l);
	}
}
