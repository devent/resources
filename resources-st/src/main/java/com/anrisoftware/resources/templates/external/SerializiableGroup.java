package com.anrisoftware.resources.templates.external;

import java.io.Serializable;

import org.stringtemplate.v4.STGroup;

/**
 * Wrap {@link STGroup} group in a serializable to be use in the attributes map.
 *
 * @author Erwin Müller <erwin.mueller@deventm.de>
 * @version 2.1
 */
@SuppressWarnings("serial")
public class SerializiableGroup implements Serializable {

    public final STGroup group;

    public SerializiableGroup(STGroup group) {
        this.group = group;
    }

}
