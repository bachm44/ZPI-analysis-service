package com.zpi.domain.common;

public record DeviceInfo(
        String fingerprint,
        String userAgent,
        String browser,
        String engine,
        String engineVersion,
        String OS,
        String OSVersion,
        String device,
        String deviceType,
        String deviceVendor,
        String CPU,
        String colorDepth,
        String currentResolution,
        String availableResolution,
        String mimeTypes,
        String timeZone,
        String language,
        String systemLanguage
) {
}