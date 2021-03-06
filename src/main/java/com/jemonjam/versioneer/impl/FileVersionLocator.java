/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Returns a version from the given file, using a UTF-8 charset.
 *
 * @author jmeacham
 */
public class FileVersionLocator implements VersionLocator {
    private final Path path;

    public FileVersionLocator(final Path path) {
        if (path == null) {
            throw new IllegalArgumentException("path is required");
        }
        this.path = path;
    }

    @Override
    public final Optional<String> getVersion() {
        try {
            String version = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return Optional.ofNullable(version);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
