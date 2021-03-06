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

package org.panda_lang.panda.utilities.commons.text.pattern.text;

import java.util.List;

public class TextHollowPatternCompiler {

    private final TextHollowPatternBuilder builder;

    protected TextHollowPatternCompiler(TextHollowPatternBuilder builder) {
        this.builder = builder;
    }

    public TextHollowPatternBuilder compile(String pattern) {
        List<String> fragments = TextHollowPatternUtils.toFragments(pattern);

        for (String fragment : fragments) {
            if (fragment.equals("*")) {
                builder.hollow();
            }
            else {
                builder.basis(fragment);
            }
        }

        return builder;
    }

    public TextHollowPatternBuilder getBuilder() {
        return builder;
    }

}
