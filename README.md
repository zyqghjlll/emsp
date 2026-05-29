# emsp — eMobility Service Provider Backend

> A Java backend for managing EV charging infrastructure — built with Domain-Driven Design, an event-driven architecture, and a rigorously modeled state machine.

emsp manages the core entities of an electric-vehicle charging network — **Locations**, **EVSEs** (charging units), and **Connectors** — and the rules that govern how they change over time. It is built as a clean, layered DDD application with explicit aggregates, domain events, and a self-validating state machine.

---

## What it does

- Create and update charging **Locations**
- Register **EVSEs** under a Location, with a validated EVSE ID format (`<CountryCode>*<PartyID>*<LocalEvseID>`, e.g. `US*ABC*EVSE123456`)
- Transition EVSE **status** according to strict state-machine rules
- Add **Connectors** (with standard, voltage, power) to an EVSE
- Paginated queries of Locations and their EVSEs by `last_updated`
- Publish **domain events** when Locations or EVSEs change

---

## Architecture

emsp follows Domain-Driven Design with a strict separation between the domain core and infrastructure. The dependency direction always points inward toward the domain.

```
com.ethan.emsp
├── api                  # Inbound adapters — REST controllers, DTOs, VOs
│   └── controller       # Evse / Location / LocationQuery / Health
├── application          # Use-case orchestration
│   ├── cmd              # Write side (Commands)
│   └── query            # Read side (Queries)
├── domain               # The core — pure business logic, no framework deps
│   ├── model
│   │   ├── evse         # Evse aggregate, EvseStatus state machine, Connector
│   │   └── location     # Location aggregate
│   └── event            # Domain events (EvseChanged, LocationChanged, ...)
├── core                 # DDD building blocks
│   └── ddd              # AggregateRoot, Entity, ValueObject, DomainEvent, ...
└── infrastructure       # Outbound adapters — persistence, event publishing
    ├── persistence      # MyBatis-Plus: domain / event / query repositories
    ├── event            # Event persistence & publishing
    ├── monitor          # Health / monitoring
    └── config           # Spring configuration
```

### Design highlights

**Explicit DDD building blocks**
The `core.ddd` package defines first-class abstractions — `AggregateRoot`, `Entity`, `ValueObject`, `DomainEvent`, `Command`, `AppEventPublisher` — so the domain model expresses intent rather than leaking persistence concerns.

**Aggregates protect their invariants**
`Evse` is an aggregate root. State changes go through behavior (`changeStatus`, `addConnector`) rather than setters, and illegal operations are rejected at the domain boundary.

**A self-validating state machine**
`EvseStatus` encodes the legal transitions explicitly:

```
INITIAL      → AVAILABLE, REMOVED
AVAILABLE    ↔ BLOCKED
AVAILABLE    ↔ INOPERATIVE
INOPERATIVE  → INITIAL, AVAILABLE
ANY          → REMOVED   (terminal, irreversible)
```

Any attempt to make an invalid transition throws a `ConflictException`. The enum even ships a `selfValidate()` method that asserts the transition table matches the intended business rules — the model checks its own correctness.

**CQRS-style read/write separation**
The application layer splits write operations (`cmd`) from reads (`query`), with separate persistence paths (domain repositories vs. query/read models), keeping the write model clean and the read side optimized for queries.

**Event-driven design**
Domain changes raise events (`EvseChangedEvent`, `LocationChangedEvent`, `LocationEvseChangedEvent`) that are persisted and published, enabling downstream consumers to react to state changes.

---

## Tech stack

| Layer | Technology |
| --- | --- |
| Runtime | Java, Spring Boot |
| Web | Spring Boot Web + Validation |
| Persistence | MyBatis-Plus, PageHelper, PostgreSQL |
| Architecture | DDD + Event-Driven + CQRS-style read/write split |
| Utilities | Hutool, Lombok |
| Testing | JUnit 5 |
| CI/CD | GitHub Actions |

See [`docs/DB-DESIGN.md`](docs/DB-DESIGN.md) for the database schema.

---

## Domain model

**Location** — a charging site (name, address, coordinates, opening hours).

**EVSE** — a charging unit belonging to a Location, identified by a structured ID and governed by the status state machine above.

**Connector** — a physical interface on an EVSE (standard, voltage, power).

---

## REST API

- Create / update a **Location**
- Add an **EVSE** to a Location (validates EVSE ID format)
- Change **EVSE status** (enforced by the state machine)
- Add a **Connector** to an EVSE
- Paginated query of Locations and their EVSEs by `last_updated`
- Health endpoint

---

## Getting started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL

### Build & run

```bash
mvn -q -DskipTests clean package
java -jar target/emsp-*.jar
```

The project includes a GitHub Actions workflow (`.github/workflows/deploy-workflow.yml`) and was designed for deployment to cloud free tiers (AWS / Azure).

---

## Engineering notes

This project was built to practice production-grade backend design end to end: modeling a non-trivial domain with DDD, enforcing invariants through a state machine, separating reads from writes, and wiring up events, validation (with meaningful HTTP responses such as 400 / 409), CI, and a Dockerized deployment.

---

## License

Released under the MIT License.