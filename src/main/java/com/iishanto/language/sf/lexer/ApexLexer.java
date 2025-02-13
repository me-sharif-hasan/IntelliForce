/*
 * Copyright 2014 Mark Borner
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
package com.iishanto.language.sf.lexer;

import com.intellij.lang.java.lexer.JavaLexer;
import com.intellij.lexer.DelegateLexer;
import com.intellij.lexer.LayeredLexer;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.tree.IElementType;

import java.util.Arrays;

public class ApexLexer extends LayeredLexer {
    public ApexLexer() {
        super(new ApexKeywordReplacingLexer(new JavaLexer(LanguageLevel.HIGHEST)));
    }

    private static class ApexKeywordReplacingLexer extends DelegateLexer {
        public ApexKeywordReplacingLexer(JavaLexer baseLexer) {
            super(baseLexer);
        }
        @Override
        public IElementType getTokenType() {
            String[] unavailableKeywordsInApex = new String[]{
                    "global",
                    "virtual",
                    "with",
                    "sharing",
                    "without",
                    "trigger",
                    "on",
                    "before",
                    "insert",
                    "update",
                    "delete",
                    "after",
                    "undelete",
                    "select",
                    "from",
                    "where",
                    "limit",
                    "security_enforced",
                    "by",
                    "asc",
                    "desc",
                    "group",
                    "by",
                    "having",
                    "offset",
                    "nulls",
                    "first",
                    "last"
            };
            if (Arrays.stream(unavailableKeywordsInApex).anyMatch(keyword -> keyword.equals(super.getTokenText().toLowerCase()))) {
                return JavaTokenType.PUBLIC_KEYWORD;
            }
            return super.getTokenType();
        }
    }
}
