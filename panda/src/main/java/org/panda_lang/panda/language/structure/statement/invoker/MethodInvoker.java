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

package org.panda_lang.panda.language.structure.statement.invoker;

import org.panda_lang.panda.framework.design.architecture.detach.StandaloneExecutable;
import org.panda_lang.panda.framework.design.architecture.value.Value;
import org.panda_lang.panda.framework.design.architecture.detach.ExecutableBranch;
import org.panda_lang.panda.framework.design.architecture.detach.Expression;
import org.panda_lang.panda.language.structure.general.expression.ExpressionUtils;
import org.panda_lang.panda.framework.design.architecture.prototype.method.PrototypeMethod;

import javax.annotation.Nullable;

public class MethodInvoker implements StandaloneExecutable {

    private final PrototypeMethod method;
    private final Expression instanceExpression;
    private final Expression[] arguments;

    public MethodInvoker(PrototypeMethod method, @Nullable Expression instance, Expression[] arguments) {
        if (method == null) {
            throw new IllegalArgumentException("PrototypeMethod cannot be null");
        }

        this.method = method;
        this.instanceExpression = instance;
        this.arguments = arguments;
    }

    @Override
    public void execute(ExecutableBranch branch) {
        Value instance = null;

        if (instanceExpression != null) {
            instance = instanceExpression.getExpressionValue(branch);
            branch.instance(instance);
        }

        Value[] values = ExpressionUtils.getValues(branch, arguments);
        method.invoke(branch, instance != null ? instance.getObject() : null, values);
    }

    public PrototypeMethod getMethod() {
        return method;
    }

}
