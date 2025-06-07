# Система за класиране на деца (Kindergarten Ranking)

Този проект реализира учебна система за класиране на деца в детска градина според зададени критерии:

- **Родители**: +1 точка за всеки работещ родител на детето
- **Увреждане**: +2 точки
- **Близнаци**: +1 точка
- **Брат/Сестра**: +1 точка
- При равни точки – случаен избор

## Структура на проекта

```text
kindergarten-ranking/
├─ pom.xml                  # Maven конфигурация
├─ src
│  ├─ main/java
│  │  └─ com/example/kindergarten/
│  │     ├─ model/         # Child, Parent, Administrator интерфейси
│  │     ├─ repository/    # ChildRepository, ParentRepository и InMemory реализации
│  │     └─ service/       # RankingService, AdministratorService
│  └─ test/java
│     └─ com/example/kindergarten/
│        ├─ repository/    # Unit тестове за репозиториите
│        └─ service/       # Unit тестове за RankingService и AdministratorService
└─ target/                  # компилирани класове и отчети


Изисквания
Java 17

Maven 3.x

git clone https://github.com/B1udger/services-test-kp.git
cd services-test-kp
mvn clean compile

mvn test

mvn jacoco:report



