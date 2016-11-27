package org.panda_lang.panda.implementation.interpreter.parser;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.panda_lang.framework.interpreter.parser.ParserHandler;
import org.panda_lang.framework.interpreter.parser.ParserPipeline;
import org.panda_lang.framework.interpreter.parser.ParserRepresentation;
import org.panda_lang.framework.interpreter.parser.UnifiedParser;
import org.panda_lang.panda.Panda;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class ParserComposition {

    private final ParserPipeline pipeline;

    public ParserComposition() {
        this.pipeline = new PandaParserPipeline();

        try {
            this.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initialize() throws Exception {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setClassLoaders(new ClassLoader[]{ getClass().getClassLoader() });
        config.addUrls(Panda.class.getProtectionDomain().getCodeSource().getLocation().toURI().toURL());

        Reflections reflections = new Reflections(config);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ParserRegistration.class);

        for (Class<?> clazz : annotated) {
            ParserRegistration parserRegistration = clazz.getAnnotation(ParserRegistration.class);

            ConstructorAccess<? extends UnifiedParser> parserConstructor = ConstructorAccess.get(parserRegistration.parserClass());
            UnifiedParser parser = parserConstructor.newInstance();

            ConstructorAccess<? extends ParserHandler> handlerConstructor = ConstructorAccess.get(parserRegistration.handlerClass());
            ParserHandler handler = handlerConstructor.newInstance();

            ParserRepresentation representation = new PandaParserRepresentation(parser, handler, parserRegistration.priority());
            pipeline.registerParserRepresentation(representation);
        }
    }

    public ParserPipeline getPipeline() {
        return pipeline;
    }

}