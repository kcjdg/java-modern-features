import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;


/**
 *
 * To run: `java JEP430_StringTemplates.java`
 * @see https://openjdk.org/jeps/445
 */
public class JEP430_StringTemplates {
    public static void main(String[] args) {
        interpolationUsingSTRProcessor("btcusdt", LocalDate.of(2023, Month.OCTOBER, 2), 27477.6);
        System.out.println(interpolationOfFMTJSONBlock("btcusdt", LocalDate.of(2023, Month.OCTOBER, 3), 27477.6));

    }



    static void interpolationUsingSTRProcessor(String coinName, LocalDate date, Double price){


        //Interpolation by relatively replacing each embedded expression
        StringTemplate st = RAW."The price of \{ coinName } on \{ date } is $\{ price }";
        System.out.println(STR.process(st));

        //Same as above build from raw template processor
        System.out.println(STR. "The price of \{ coinName } on \{ date } is $\{ price }");

        //Using multiline text blocks
        System.out.println(interpolationOfJSONBlock(coinName, date, price));

        //Inject expressions inline
        System.out.println(interpolationWithExpressions(coinName, date, price));

    }

    static String interpolationWithExpressions(String coinName, LocalDate date, Double price){
        int counter = 3;
        MarketType marketType = MarketType.F;
        System.out.println(STR."\{coinName} price change in \{counter--}, \{counter--}, \{counter--}");
        return STR
                ."""
                {
                 "coinName": "\{ coinName = coinName.toUpperCase() }",
                 "price": "\{ price + 2}",
                 "date": "\{ date.format((DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH))) }",
                 "stableCoin": "\{ coinName.endsWith("USDT") ? "USDT" : "USDC"}",
                 "marketType": "\{
                    switch (marketType) {
                        case F->"FUTURES";
                        case M->"MARGIN";
                        default->"SPOT";
                        }
                    }",
                }
                """;
    }

    static String interpolationOfJSONBlock(String coinName, LocalDate date, Double price){
        return STR
                ."""
                {
                 "coinName": "\{coinName}",
                 "price": "\{price}",
                 "date": "\{date}"
                }
                """;
    }


    static String interpolationOfFMTJSONBlock(String coinName, LocalDate date, Double price){
        return FMT."""
                {
                 "coinName": "%1s\{coinName}",
                 "price": "%2.2f\{price}",
                 "date": "%1s\{date}"
                }
                """;
    }


    enum MarketType {
        F,
        S,
        M

    }


}
