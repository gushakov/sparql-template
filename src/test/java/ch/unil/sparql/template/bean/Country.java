package ch.unil.sparql.template.bean;

import ch.unil.sparql.template.annotation.Predicate;
import ch.unil.sparql.template.annotation.Rdf;

import static ch.unil.sparql.template.Prefixes.DBP;

/**
 * @author gushakov
 */
@Rdf
public class Country {

    @Predicate(DBP)
    private String commonName;

    public String getCommonName() {
        return commonName;
    }
}
