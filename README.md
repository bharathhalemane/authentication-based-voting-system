voting-system/
├── backend/                          ← Spring Boot
│   ├── src/main/java/com/voting/
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── MongoConfig.java
│   │   ├── model/
│   │   │   ├── Voter.java
│   │   │   ├── Candidate.java
│   │   │   ├── Vote.java
│   │   │   ├── BiometricTemplate.java
│   │   │   └── AuditLog.java
│   │   ├── repository/
│   │   │   ├── VoterRepository.java
│   │   │   └── VoteRepository.java
│   │   ├── service/
│   │   │   ├── VoterService.java
│   │   │   ├── AuthService.java
│   │   │   ├── VoteService.java
│   │   │   └── BiometricService.java
│   │   └── controller/
│   │       ├── AdminController.java
│   │       ├── VoterController.java
│   │       └── VoteController.java
├── frontend/                         ← Java Swing
│   ├── AdminFrame.java               (login, tabs)
│   ├── panels/
│   │   ├── RegisterVoterPanel.java
│   │   ├── ManageCandidatesPanel.java
│   │   ├── LiveDashboardPanel.java
│   │   └── ResultsPanel.java
│   └── voter/
│       ├── VoterTerminalFrame.java
│       ├── BiometricCapturePanel.java
│       └── BallotPanel.java
└── pom.xml

🚀 Development Plan (Step-by-Step)
Phase 1:

Setup Spring Boot + MongoDB

Create APIs

Phase 2:

Build Swing UI (Admin + Voting)

Phase 3:

Integrate Face Recognition

Phase 4:

Add vote locking & validation

Phase 5:

Testing + UI polish