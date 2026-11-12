package com.prueba.ProyectoAlquiler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT, properties={"spring.jpa.hibernate.ddl-auto=validate","app.bootstrap.admin-password="})
@EnabledIfEnvironmentVariable(named="VERIFY_REAL_DB", matches="true")
class ActualDatabaseSchemaTest {
 @Test void actualSchemaMatchesJpaAndApplicationStarts() {}
}
