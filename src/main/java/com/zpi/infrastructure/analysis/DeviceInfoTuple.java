package com.zpi.infrastructure.analysis;

import com.zpi.domain.analysis.request.DeviceInfo;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DEVICE_INFO")
class DeviceInfoTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(unique = true, nullable = false)
    private String fingerprint;
    private String userAgent;
    private String browser;
    private String engine;
    private String engineVersion;
    private String OS;
    private String OSVersion;
    private String device;
    private String deviceType;
    private String deviceVendor;
    private String CPU;

    @Lob
    @Column
    private String screenPrint;
    private String colorDepth;
    private String currentResolution;
    private String availableResolution;
    private String mimeTypes;

    @Lob
    @Column
    private String fonts;
    private String timeZone;
    private String language;
    private String systemLanguage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @ToString.Exclude
    private Set<RequestTuple> requests;

    DeviceInfoTuple(DeviceInfo info) {
        this.fingerprint = info.fingerprint();
        this.userAgent = info.userAgent();
        this.browser = info.browser();
        this.engine = info.engine();
        this.engineVersion = info.engineVersion();
        this.OS = info.OS();
        this.OSVersion = info.OSVersion();
        this.device = info.device();
        this.deviceType = info.deviceType();
        this.deviceVendor = info.deviceVendor();
        this.CPU = info.CPU();
        this.screenPrint = info.screenPrint();
        this.colorDepth = info.colorDepth();
        this.currentResolution = info.currentResolution();
        this.availableResolution = info.availableResolution();
        this.mimeTypes = info.mimeTypes();
        this.fonts = info.fonts();
        this.timeZone = info.timeZone();
        this.language = info.language();
        this.systemLanguage = info.systemLanguage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeviceInfoTuple that = (DeviceInfoTuple) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public DeviceInfo toDomain() {
        return new DeviceInfo(
                fingerprint,
                userAgent,
                browser,
                engine,
                engineVersion,
                OS,
                OSVersion,
                device,
                deviceType,
                deviceVendor,
                CPU,
                screenPrint,
                colorDepth,
                currentResolution,
                availableResolution,
                mimeTypes,
                fonts,
                timeZone,
                language,
                systemLanguage
        );
    }
}