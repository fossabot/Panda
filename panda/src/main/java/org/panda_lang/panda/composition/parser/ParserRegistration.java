package org.panda_lang.panda.composition.parser;

import org.panda_lang.core.interpreter.parser.MatchedParser;
import org.panda_lang.core.interpreter.parser.ParserHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ParserRegistration {

    Class<? extends MatchedParser> parserClass();

    Class<? extends ParserHandler> handlerClass();

}

