package com.example.teammanagement.config;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateScalarConfiguration {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(dateScalar());
    }

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Date scalar")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof LocalDate) {
                            return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                        throw new CoercingSerializeException("Expected LocalDate");
                    }

                    @Override
                    public LocalDate parseValue(Object input) throws CoercingParseValueException {
                        if (input instanceof String) {
                            return LocalDate.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                        throw new CoercingParseValueException("Expected String");
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof String) {
                            return LocalDate.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                        throw new CoercingParseLiteralException("Expected String literal");
                    }
                })
                .build();
    }
}
