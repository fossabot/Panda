/*
 * Copyright (c) 2015-2017 Dzikoysk
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

package org.panda_lang.panda.language.composition.prototypes.lang;

import org.panda_lang.panda.language.structure.group.Group;
import org.panda_lang.panda.language.structure.group.GroupRegistry;
import org.panda_lang.panda.language.structure.prototype.structure.ClassPrototype;
import org.panda_lang.panda.language.structure.prototype.registry.ClassPrototypeRegistrationCall;

@ClassPrototypeRegistrationCall
public class BooleanPrototype {

    static {
        GroupRegistry registry = GroupRegistry.getDefault();
        Group defaultGroup = registry.getOrCreate("panda.lang");

        ClassPrototype prototype = new ClassPrototype("Boolean");
        prototype.getGroup().setObject(defaultGroup);
        defaultGroup.add(prototype);
    }

}
