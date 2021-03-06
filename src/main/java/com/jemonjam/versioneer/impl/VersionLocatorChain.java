/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Creates a chain of {@link VersionLocator} and returns the first non-empty version.
 *
 * The locators are queried in order, and the first non-empty version is returned.
 * If all versions are empty, an empty version is returned.
 *
 * @author jmeacham
 */
public class VersionLocatorChain implements VersionLocator {
    private final List<VersionLocator> locators = new ArrayList<>();

    public VersionLocatorChain(List<? extends VersionLocator> locators) {
        if (locators == null) {
            throw new IllegalArgumentException("locators are required");
        }

        this.locators.addAll(locators);
    }

    public VersionLocatorChain(VersionLocator... locators) {
        if (locators == null || locators.length == 0) {
            throw new IllegalArgumentException("locators are required");
        }

        for (VersionLocator locator : locators) {
            this.locators.add(locator);
        }
    }

    @Override
    public final Optional<String> getVersion() {
        Optional<String> version = Optional.empty();
        for (VersionLocator locator : locators) {
            version = locator.getVersion();
            if (version.isPresent()) {
                break;
            }
        }

        return version;
    }

}
