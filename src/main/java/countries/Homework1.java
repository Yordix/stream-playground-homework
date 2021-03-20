package countries;

import java.util.*;

public class Homework1 {

    private List<Country> countries;

    public Homework1() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns whether there is at least one country with the word "island" in its name ignoring case.
     */
    public boolean streamPipeline1() {
        return countries.stream()
                .anyMatch(c -> c.getName().toLowerCase().contains("island"));
    }

    /**
     *  Returns the first country name that contains the word "island" ignoring case.
     * @return
     */
    public Optional<Country> streamPipeline2() {
        return countries.stream()
                .filter(c -> c.getName().toLowerCase().contains("island"))
                .findFirst();
    }

    /**
     * Prints each country name in which the first and the last letters are the same ignoring case.
     */
    public void streamPipeline3() {
        countries.stream()
                .filter(c -> c.getName().toLowerCase().charAt(0) == c.getName().charAt(c.getName().length() - 1))
                .forEach(System.out::println);
    }

    /**
     * Prints the populations of the first ten least populous countries.
     */
    public void streamPipeline4() {
        countries.stream()
                .sorted(Comparator.comparingLong(Country::getPopulation))
                .map(c -> c.getName())
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * Prints the names of the first ten least populous countries.
     */
    public void streamPipeline5() {
        countries.stream()
                .sorted(Comparator.comparingLong(Country::getPopulation))
                .map(c -> c)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * Returns summary statistics about the number of country name translations associated with each country.
     */
    public IntSummaryStatistics streamPipeline6() {
        return countries.stream()
                .mapToInt(c -> c.getTranslations().size())
                .summaryStatistics();
    }

    /**
     * Prints the names of countries in the ascending order of the number of timezones.
     */
    public void streamPipeline7() {
        countries.stream()
                .sorted(Comparator.comparingInt(c -> c.getTimezones().size()))
                .map(c -> c.getName())
                .forEach(System.out::println);
    }

    /**
     * Prints the number of timezones for each country in the form {@code name:timezones}, in the ascending order of the number of timezones.
     */
    public void streamPipeline8(List<Country> countries) {
        countries.stream()
                .sorted(Comparator.comparingInt(c -> c.getTimezones().size()))
                .collect(LinkedHashMap::new, (map, value) -> map.put(value.getName(), value.getTimezones().size()), HashMap::putAll)
                .forEach((country, value) -> {
                    System.out.println(country + ":" + value);});
    }

    /**
     * Returns the number of countries with no Spanish country name translation (the Spanish language is identifi
ed by the language code "es").
     */
    public long streamPipeline9() {
        return countries.stream()
                .flatMap(c -> c.getTranslations().keySet().stream()
                        .filter(ck -> ck.contains("es")))
                .count();
    }

    /**
     * Prints the names of countries with null area.
     */
    public void streamPipeline10() {
        countries.stream()
                .filter(c -> c.getArea() == null)
                .map(Country::getName)
                .forEach(System.out::println);
    }

    /**
     * Prints all distinct language tags of country name translations sorted in alphabetical order.
     */
    public void streamPipeline11() {
        countries.stream()
                .flatMap(c -> c.getTranslations().keySet().stream())
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Returns the average length of country names.
     */
    public double streamPipeline12() {
        return countries.stream()
                .mapToDouble(c -> c.getName() == null ? 0 : c.getName().length())
                .average().getAsDouble();
    }

    /**
     * Prints all distinct regions of the countries with null area.
     */
    public void streamPipeline13() {
        countries.stream()
                .filter(c -> c.getArea() == null)
                .map(c -> c.getRegion())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Returns the largest country with non-null area.
     */
    public Optional<Country> streamPipeline14() {
        return countries.stream()
                .filter(c -> c.getArea() != null)
                .max(Comparator.comparing(Country::getArea));
    }

    /**
     * Prints the names of countries with a non-null area below 1.
     */
    public void streamPipeline15() {
        countries.stream()
                .filter(c -> c.getArea() != null)
                .filter(c -> c.getArea().doubleValue() < 1)
                .map(country -> country.getName())
                .forEach(System.out::println);
    }

    /**
     * Prints all distinct timezones of European and Asian countries.
     */
    public void streamPipeline16() {
        countries.stream()
                .filter(c -> c.getRegion() == Region.EUROPE || c.getRegion() == Region.ASIA)
                .flatMap(country -> country.getTimezones().stream())
                .distinct()
                .forEach(System.out::println);
    }

}
