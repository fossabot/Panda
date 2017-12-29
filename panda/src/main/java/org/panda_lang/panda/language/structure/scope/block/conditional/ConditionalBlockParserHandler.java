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

package org.panda_lang.panda.language.structure.scope.block.conditional;

import org.panda_lang.panda.framework.language.interpreter.parser.pipeline.ParserHandler;
import org.panda_lang.panda.framework.language.interpreter.token.TokenRepresentation;
import org.panda_lang.panda.framework.language.interpreter.token.TokenUtils;
import org.panda_lang.panda.framework.language.interpreter.token.reader.TokenReader;
import org.panda_lang.panda.language.syntax.tokens.Keywords;

public class ConditionalBlockParserHandler implements ParserHandler {

    @Override
    public boolean handle(TokenReader tokenReader) {
        TokenRepresentation representation = tokenReader.read();
        return TokenUtils.equals(representation, Keywords.IF) || TokenUtils.equals(representation, Keywords.ELSE);
    }

}
