/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
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
