https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-criteria/persistence-criteria.html
https://github.com/cursodsousa/curso-spring-boot-especialista/tree/1911e4b9aac348427433d59ccd386d2a16c773fa


https://github.com/cursodsousa/curso-spring-boot-especialista/tree/f6eb9f098e1904174b23b9fd9730aca3f4feb6ec IMPORTANTE


empacotar o projeto
> ./mvnw clean package -DskipTests
> java -jar ./target/file


> criar container
> docker build -t guuhworship/libraryapi .
> docker run --name libraryapi-prod -e DATASOURCE_URL=jdbc:postgresql://pg:5432/library -e DATASOURCE_USERNAME=postgres -e DATASOURCE_PASSWORD=postgres --network pg -p 8080:8080 -p 9001:9001 guuhworship/libraryapi