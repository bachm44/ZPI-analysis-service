package com.zpi.infrastructure.analysis;

import com.zpi.domain.analysis.twoFactor.incident.Incident;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "INCIDENT")
class IncidentTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private RequestTuple request;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<IncidentTypeTuple> type;

    @Enumerated(EnumType.STRING)
    private IncidentSeverityTuple severity;

    IncidentTuple(Incident incident, RequestTuple request) {
        this.request = request;
        this.type = incident.getType().stream().map(IncidentTypeTuple::from).collect(Collectors.toList());
        this.severity = IncidentSeverityTuple.from(incident.getSeverity());
    }

    public static Incident toDomain(IncidentTuple incidentTuple) {
        var types = incidentTuple.getType().stream().map(IncidentTypeTuple::toDomain).collect(Collectors.toList());
        var severity = IncidentSeverityTuple.toDomain(incidentTuple.getSeverity());
        return new Incident(types, severity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IncidentTuple that = (IncidentTuple) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
