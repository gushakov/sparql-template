package ch.unil.sparql.template.bean.example;

import ch.unil.sparql.template.annotation.Predicate;
import ch.unil.sparql.template.annotation.Rdf;

import static ch.unil.sparql.template.Vocabulary.EXP_NS;

/**
 * @author gushakov
 */
@Rdf
public class Resource {

    @Predicate(EXP_NS)
    private String name;

    public String getName() {
        return name;
    }
}
