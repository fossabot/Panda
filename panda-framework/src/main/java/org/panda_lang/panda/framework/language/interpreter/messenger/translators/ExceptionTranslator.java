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

package org.panda_lang.panda.framework.language.interpreter.messenger.translators;

import org.panda_lang.panda.framework.design.interpreter.messenger.*;
import org.panda_lang.panda.framework.language.interpreter.messenger.*;
import org.panda_lang.panda.framework.language.interpreter.messenger.defaults.*;
import org.panda_lang.panda.utilities.redact.format.*;

public class ExceptionTranslator implements MessengerMessageTranslator<Exception> {

    @Override
    public void handle(Messenger messenger, Exception element) {
        MessageFormatter formatter = DefaultMessageFormatter.getFormatter()
                .register("{{message}}", element::getMessage)
                .register("{{details}}", () -> {
                    return null;
                });

        DefaultFailureTemplateBuilder templateBuilder = new DefaultFailureTemplateBuilder()
                .includeCause()
                .includeDetails(element.getStackTrace())
                .includeEnvironment()
                .includeEnd();

        PandaMessengerMessage message = new PandaMessengerMessage(MessengerMessage.Level.FAILURE, templateBuilder.getAsLines(formatter, "InterpreterFailure"));
        messenger.sendMessage(message);
    }

    @Override
    public Class<Exception> getType() {
        return Exception.class;
    }

}