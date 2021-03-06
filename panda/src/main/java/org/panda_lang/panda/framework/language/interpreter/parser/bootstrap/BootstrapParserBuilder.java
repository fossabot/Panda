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

package org.panda_lang.panda.framework.language.interpreter.parser.bootstrap;

import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.ParserHandler;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.ParserRepresentation;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.annotations.Autowired;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.handlers.AbyssPatternHandler;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.interceptor.BootstrapInterceptor;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.interceptor.DefaultInterceptor;
import org.panda_lang.panda.framework.language.interpreter.parser.bootstrap.layer.LayerMethod;
import org.panda_lang.panda.utilities.commons.ReflectionUtils;
import org.panda_lang.panda.utilities.commons.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class BootstrapParserBuilder {

    protected Object instance;
    protected String name;

    protected String pipeline;
    protected ParserHandler handler;
    protected int priority;

    protected String pattern;
    protected String[] wildcardNames;
    protected int maxNestingLevel;

    protected BootstrapInterceptor interceptor;
    protected final List<LayerMethod> layers = new ArrayList<>();

    protected BootstrapParserBuilder() { }

    public BootstrapParserBuilder instance(Object object) {
        this.instance = object;
        return this;
    }

    public BootstrapParserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BootstrapParserBuilder pipeline(String pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public BootstrapParserBuilder interceptor(BootstrapInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }

    public BootstrapParserBuilder handler(ParserHandler handler) {
        this.handler = handler;
        return this;
    }

    public BootstrapParserBuilder pattern(String pattern, String... wildcardNames) {
        this.pattern = pattern;
        this.wildcardNames = wildcardNames;
        return this;
    }

    public BootstrapParserBuilder pattern(String pattern, int maxNestingLevel, String... wildcardNames) {
        this.maxNestingLevel = maxNestingLevel;
        return pattern(pattern, wildcardNames);
    }

    public BootstrapParserBuilder priority(int priority) {
        this.priority = priority;
        return this;
    }

    public BootstrapParserBuilder layer(LayerMethod layer) {
        layers.add(layer);
        return this;
    }

    public BootstrapParserBuilder layers(Class<?> clazz) {
        return layers(ReflectionUtils.getMethodsAnnotatedWith(clazz, Autowired.class));
    }

    public BootstrapParserBuilder layers(Class<?> clazz, String methodName) {
        return layers(ReflectionUtils.getMethods(clazz, methodName));
    }

    public BootstrapParserBuilder layers(Collection<Method> methods) {
        methods.stream()
                .map(LayerMethod::new)
                .sorted(Comparator.comparingInt(LayerMethod::getOrder))
                .forEach(layers::add);

        return this;
    }

    public ParserRepresentation build() {
        if (name == null && instance != null) {
            name(instance.getClass().getSimpleName());
        }

        if (layers.isEmpty() && instance != null) {
            layers(instance.getClass());
        }

        if (interceptor == null) {
            interceptor(new DefaultInterceptor());
        }

        if (pattern == null) {
            pattern = StringUtils.EMPTY;
        }

        if (handler == null) {
            handler = new AbyssPatternHandler(pattern);
        }

        if (layers.isEmpty()) {
            throw new ParserBootstrapException("Bootstrap does not contain any layers");
        }

        return new PandaParserBootstrap(this).generate();
    }

}
