import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class SpartanTest {


    @Test
    @Order(3)
    @DisplayName("1. bolum")
    @BeforeEach // bu anatation her methoddan once yzili oldugu methodu calistirir. @AfterEach de her testten sonra calisiyor.
                // @BeforeAll ilk once bu method calisiyor.@AfterAll da en son calistirir.
    public void spartan1(){
        System.out.print("Merhaba Spartan 1");

       assertThat(3, not (4));
       assertThat("Ahmet",is(equalTo("Ahmet")));
       assertThat("ahmet", equalTo("ahmet"));
        List<Integer> list = Arrays.asList(34,35,36,37);
        assertThat(list, hasSize(4));
        assertThat(list, hasItems(35,37));

    }

    @Test
    @Order(2)
    @DisplayName("2. bolum")
    public void spartan2(){
        System.out.print("Merhaba Spartan 2");
    }

    @Test
    @Order(1)
    @DisplayName("3. bolum")
    public void spartan3(){
        System.out.print("Merhaba Spartan 3");
    }
}
