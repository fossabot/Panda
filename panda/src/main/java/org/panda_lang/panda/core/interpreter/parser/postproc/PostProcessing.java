/*
 * Copyright (c) 2015-2018 Dzikoysk
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

package org.panda_lang.panda.core.interpreter.parser.postproc;

import org.panda_lang.panda.framework.language.interpreter.Interpreter;

import java.util.ArrayList;
import java.util.List;

public class PostProcessing {

    private final List<PostProcessingCallback> callbacks;

    public PostProcessing() {
        this.callbacks = new ArrayList<>();
    }

    public void call(Interpreter interpreter) {
        for (PostProcessingCallback callback : callbacks) {
            callback.call(interpreter);
        }
    }

    public void addCallback(PostProcessingCallback callback) {
        this.callbacks.add(callback);
    }

}
