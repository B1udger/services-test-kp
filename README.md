Система за класиране на деца (Kindergarten Ranking)

Този проект реализира учебна система за класиране на деца в детска градина според зададени критерии:

Родители: +1 точка за всеки работещ родител на детето

Увреждане: +2 точки

Близнаци: +1 точка

Брат/Сестра: +1 точка

При равни точки – случаен избор

Структура на проекта

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

Сваляне и компилация

git clone https://github.com/B1udger/services-test-kp.git
cd services-test-kp
mvn clean compile

Изпълнение на тестовете

Проектът използва JUnit 5:

mvn test

За подробен отчет в конзолата на всеки изпълнен тест, както и генериране на отчет за покритие, конфигурирано чрез Surefire и JaCoCo.

Code Coverage

За да генерирате HTML отчет за покритие на кода:

mvn jacoco:report

Отчетът се намира в target/site/jacoco/index.html и показва процента на покритие.

Документация

Автоматично генерирана JavaDoc документация:

mvn javadoc:javadoc

Документацията се намира в target/site/apidocs.

LICENCE

MIT License

Проектът е учебно задание и демонстрира разбиране на работния процес при разработка на софтуер.
