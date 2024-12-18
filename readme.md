https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-criteria/persistence-criteria.html
https://github.com/cursodsousa/curso-spring-boot-especialista/tree/1911e4b9aac348427433d59ccd386d2a16c773fa


https://github.com/cursodsousa/curso-spring-boot-especialista/tree/f6eb9f098e1904174b23b9fd9730aca3f4feb6ec IMPORTANTE


empacotar o projeto
> ./mvnw clean package -DskipTests
> java -jar ./target/file


> criar container
> docker build -t guuhworship/libraryapi .
> docker run --name libraryapi-prod -e DATASOURCE_URL=jdbc:postgres://baasu.db.elephantsql.com/kbtyzjvi -e DATASOURCE_USERNAME=kbtyzjvi -e DATASOURCE_PASSWORD=a3aGFjeNjd1cILd34toqeAN0BGWB58X0 --network pg -p 8080:8080 -p 9001:9001 guuhworship/libraryapi

