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

package org.panda_lang.panda.lexer.extractor;

import org.junit.Test;
import org.panda_lang.panda.core.interpreter.lexer.extractor.vague.VagueElement;
import org.panda_lang.panda.core.interpreter.lexer.extractor.vague.VagueExtractor;
import org.panda_lang.panda.core.interpreter.lexer.extractor.vague.VagueResult;
import org.panda_lang.panda.framework.implementation.interpreter.lexer.PandaLexer;
import org.panda_lang.panda.framework.language.interpreter.lexer.Lexer;
import org.panda_lang.panda.framework.language.interpreter.token.Token;
import org.panda_lang.panda.framework.language.interpreter.token.TokenizedSource;
import org.panda_lang.panda.framework.language.interpreter.token.defaults.Separator;
import org.panda_lang.panda.language.syntax.PandaSyntax;
import org.panda_lang.panda.language.syntax.tokens.Operators;
import org.panda_lang.panda.language.syntax.tokens.Separators;

public class VagueExtractorTest {

    private static final VagueExtractor EXTRACTOR = new VagueExtractor(new Separator[]{
            Separators.LEFT_PARENTHESIS_DELIMITER,
            Separators.RIGHT_PARENTHESIS_DELIMITER
    }, new Token[] {
            Operators.ADDITION,
            Operators.SUBTRACTION,
            Operators.DIVISION,
            Operators.MULTIPLICATION });

    @Test
    public void testVagueExtractor() {
        Lexer lexer = new PandaLexer(PandaSyntax.getInstance(), "(new Integer(5).intValue() + 3)");
        TokenizedSource source = lexer.convert();

        VagueResult result = EXTRACTOR.extract(source);

        for (VagueElement element : result.getElements()) {
            System.out.println(element);
        }
    }

}
