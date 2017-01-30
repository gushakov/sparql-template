package ch.unil.sparql.template;

import ch.unil.sparql.template.bean.Actor;
import ch.unil.sparql.template.bean.Film;
import ch.unil.sparql.template.bean.Person;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

import static ch.unil.sparql.template.Prefixes.DBR;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gushakov
 */
public class SparqlTemplateTestIT {

    @Test
    public void testReadMe() throws Exception {

        // get the default SPARQL template
        final SparqlTemplate sparqlTemplate = new SparqlTemplate("https://dbpedia.org/sparql");

        // load information about Angelina Jolie
        final Person person = sparqlTemplate.load(DBR + ":Angelina_Jolie", Person.class);

        System.out.println(person.getBirthName());
        // Angelina Jolie Voight

        System.out.println(person.getLabel());
        // Джоли, Анджелина

        System.out.println(person.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy (EEE)", Locale.ENGLISH)));
        // 04/06/1975 (Wed)

        System.out.println(person.getSpouse().stream().filter(p -> p.getBirthName().contains("Pitt")).findAny().get().getBirthName());
        // William Bradley Pitt
    }

    @Test
    public void testLoad() throws Exception {
        final SparqlTemplate sparqlTemplate = new SparqlTemplate("https://dbpedia.org/sparql");
        final Person person = sparqlTemplate.load(DBR + ":Angelina_Jolie", Person.class);
        assertThat(person.getBirthName()).isEqualTo("Angelina Jolie Voight");
        assertThat(person.getBirthDate().getYear()).isEqualTo(1975);
        assertThat(person.getCitizenship().getCommonName()).isEqualTo("Cambodia");
        assertThat(person.getYearsMarried()).contains(1996, 1999, 2000, 2003, 2014);
        assertThat(person.getHomepage()).isEqualTo("http://www.unhcr.org/pages/49c3646c56.html");
        assertThat(person.getSpouse()).extracting("birthName").contains("William Bradley Pitt");
        assertThat(person.getSameAsUrl().stream().map(URL::getHost).collect(Collectors.toSet()))
                .contains("data.europa.eu", "rdf.freebase.com", "www.wikidata.org");
    }

    @Test
    public void testSubtype() throws Exception {
        final SparqlTemplate sparqlTemplate = new SparqlTemplate("https://dbpedia.org/sparql");
        final Actor actor = sparqlTemplate.load(DBR + ":Brad_Pitt", Actor.class);
        assertThat(actor.getYearsActive()).contains(1987);
        assertThat(actor.getBirthName()).contains("Bradley", "Pitt");
    }

    @Test
    public void testCollection() throws Exception {
        final SparqlTemplate sparqlTemplate = new SparqlTemplate("https://dbpedia.org/sparql");
        final Film film = sparqlTemplate.load(DBR + ":The_Usual_Suspects", Film.class);
        film.getStarring().forEach(a -> System.out.println(a.getBirthName()));
    }

}
