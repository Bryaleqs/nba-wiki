package com.bquinonez.nbawiki;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utilidad para nunca guardar contraseñas en texto plano.
 *
 * Nota academica: aqui se usa SHA-256 + salt aleatorio por usuario, que ya
 * es muchisimo mejor que texto plano y es facil de explicar en un examen.
 * En un sistema real de produccion se usaria BCrypt o Argon2 (mas lentos
 * a proposito, para dificultar ataques de fuerza bruta), pero eso agrega
 * una libreria externa que no es necesaria para este proyecto academico.
 */
public class PasswordUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generarSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Error generando el hash de la contraseña", e);
        }
    }

    public static boolean verificar(String passwordIngresada, String salt, String hashGuardado) {
        String hashCalculado = hashPassword(passwordIngresada, salt);
        return hashCalculado.equals(hashGuardado);
    }
}
