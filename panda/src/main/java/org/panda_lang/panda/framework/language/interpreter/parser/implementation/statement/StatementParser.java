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

package org.panda_lang.panda.framework.language.interpreter.parser.implementation.statement;

import org.panda_lang.panda.framework.design.interpreter.parser.ParserData;
import org.panda_lang.panda.framework.design.interpreter.parser.UnifiedParser;
import org.panda_lang.panda.framework.design.interpreter.parser.component.UniversalComponents;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.ParserPipeline;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.registry.PipelineRegistry;
import org.panda_lang.panda.framework.design.interpreter.token.TokenizedSource;
import org.panda_lang.panda.framework.design.interpreter.token.stream.SourceStream;
import org.panda_lang.panda.framework.language.interpreter.parser.PandaParserFailure;
import org.panda_lang.panda.framework.language.interpreter.parser.PandaPipelines;
import org.panda_lang.panda.framework.language.interpreter.parser.PandaPriorities;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.BootstrapParser;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.annotations.Autowired;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.annotations.Component;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.annotations.Redactor;
import org.panda_lang.panda.framework.language.interpreter.parser.pipeline.ParserRegistration;
import org.panda_lang.panda.framework.language.interpreter.token.stream.PandaSourceStream;

@ParserRegistration(target = PandaPipelines.SCOPE, priority = PandaPriorities.STATEMENT_VARIABLE_PARSER)
public class StatementParser extends BootstrapParser {

    {
        parserBuilder = builder()
                .pattern("+* ;", "statement");
    }

    @Autowired
    private void parse(ParserData data, @Component PipelineRegistry registry, @Redactor("statement") TokenizedSource statement) throws Throwable {
        SourceStream declarationStream = new PandaSourceStream(statement);

        ParserPipeline pipeline = registry.getPipeline(PandaPipelines.STATEMENT);
        UnifiedParser statementParser = pipeline.handle(declarationStream);

        if (statementParser == null) {
            throw new PandaParserFailure("Cannot recognize statement", data);
        }

        ParserData statementParserData = data.fork();
        statementParserData.setComponent(UniversalComponents.SOURCE_STREAM, declarationStream);
        statementParser.parse(statementParserData);
    }

}
