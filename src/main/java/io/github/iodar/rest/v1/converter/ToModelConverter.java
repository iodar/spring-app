package io.github.iodar.rest.v1.converter;

public interface ToModelConverter<M, T> {
    M convert(final T typeToBeConverted);
}
