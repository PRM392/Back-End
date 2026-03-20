package org.foodie_tour;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class HashTest {
    public static void main(String[] args) {
        System.out.println("HASH_OUTPUT=" + new BCryptPasswordEncoder().encode("123456"));
    }
}
