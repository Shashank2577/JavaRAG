package dev.shash.bns;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LawObject {

    /**
     * Clause number: what is the clause in BNS
     */
    private String clauseNo;

    /**
     * Actual Text from Reference BNS document: reference the doc and extract actual text
     */
    private String fullClauseText;

    /**
     * Punishment: Associated punishment from the text
     */
    private String punishment;

    /**
     * Examples given in context
     */
    private List<String> examples;

    /**
     * Extra Details if any
     */
    private String otherDetails;

    /**
     * Explanation in detail of the clause
     */
    private String explanationOfClauseInNaturalLanguage;

}
