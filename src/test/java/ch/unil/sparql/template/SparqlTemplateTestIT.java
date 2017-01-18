package ch.unil.sparql.template;

import ch.unil.sparql.template.bean.Person;
import org.junit.Test;

import java.time.ZonedDateTime;

import static ch.unil.sparql.template.Prefixes.DBR;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gushakov
 */
public class SparqlTemplateTestIT {

    @Test
    public void testLoad() throws Exception {
        final SparqlTemplate sparqlTemplate = new SparqlTemplate("https://dbpedia.org/sparql");
        final Person person = sparqlTemplate.load(DBR + ":Angelina_Jolie", Person.class);
        assertThat(person.getBirthName()).isEqualTo("Angelina Jolie Voight");
        assertThat(person.getBirthDate().getYear()).isEqualTo(1975);
        assertThat(person.getCitizenship().getCommonName()).isEqualTo("Cambodia");
        assertThat(person.getYearsMarried()).containsOnly(1996, 1999, 2000, 2003, 2014);
//        person.getSpouse().stream().map(Person::getBirthName).forEach(System.out::println);
        for (final Person spouse: person.getSpouse()){
            System.out.println(spouse.getBirthName());
            System.out.println(spouse.getBirthDate());
            System.out.println(spouse.getBirthDate().getDayOfWeek());
            System.out.println();
        }

    }

}
