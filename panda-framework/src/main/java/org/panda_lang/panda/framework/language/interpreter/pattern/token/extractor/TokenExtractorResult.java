package org.panda_lang.panda.framework.language.interpreter.pattern.token.extractor;

import org.panda_lang.panda.framework.design.interpreter.token.TokenizedSource;

import java.util.ArrayList;
import java.util.List;

public class TokenExtractorResult {

    private final boolean matched;
    private final List<String> identifiers;
    private final List<TokenizedSource> wildcards;
    private String errorMessage;

    public TokenExtractorResult(boolean matched) {
        this.matched = matched;
        this.identifiers = matched ? new ArrayList<>() : null;
        this.wildcards = matched ? new ArrayList<>() : null;
        this.errorMessage = matched ? null : "<unknown>";
    }

    public TokenExtractorResult() {
        this(false);
    }

    public TokenExtractorResult(String message) {
        this(false);
        this.errorMessage = message;
    }

    public TokenExtractorResult(String message, String variables, Object... data) {
        this(false);

        StringBuilder content = new StringBuilder(message);
        String[] variableNames = variables.split("\\|");

        for (int i = 0; i < variableNames.length; i++) {
            content.append(" [").append(variableNames[i]).append(": ").append(data[i]).append("]");
        }

        this.errorMessage = content.toString();
    }

    public TokenExtractorResult merge(TokenExtractorResult otherResult) {
        if (!otherResult.isMatched()) {
            throw new RuntimeException("Cannot merge unmatched result");
        }

        identifiers.addAll(otherResult.identifiers);
        wildcards.addAll(otherResult.wildcards);
        return this;
    }

    public TokenExtractorResult addWildcard(TokenizedSource wildcard) {
        wildcards.add(wildcard);
        return this;
    }

    public TokenExtractorResult addIdentifier(String identifier) {
        this.identifiers.add(identifier);
        return this;
    }

    public TokenExtractorResult setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public boolean hasErrorMessage() {
        return errorMessage != null;
    }

    public boolean isMatched() {
        return matched;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<TokenizedSource> getWildcards() {
        return wildcards;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

}
