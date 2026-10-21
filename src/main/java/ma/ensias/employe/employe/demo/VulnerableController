package ma.ensias.employe.employe.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.ensias.employe.employe.entite.Employe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * CODE VOLONTAIREMENT VULNERABLE : uniquement pour tester la detection de SonarCloud.
 * A ne jamais fusionner dans main ni deployer.
 */
@RestController
public class VulnerableController {

    // Secret ecrit en dur dans le code
    private static final String DB_PASSWORD = "admin123";

    @PersistenceContext
    private EntityManager entityManager;

    // Injection SQL (JDBC) : le parametre est concatene dans la requete
    @GetMapping("/search")
    public String search(@RequestParam String name) throws Exception {
        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employe", "root", DB_PASSWORD);
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(
                "SELECT * FROM employe WHERE name = '" + name + "'")) {
            return rs.next() ? rs.getString("name") : "aucun resultat";
        }
    }

    // Injection SQL (JPA native query) : meme probleme avec l'EntityManager
    @GetMapping("/employes/search")
    @SuppressWarnings("unchecked")
    public List<Employe> searchJpa(@RequestParam String name) {
        return entityManager
                .createNativeQuery("SELECT * FROM employe WHERE name = '" + name + "'", Employe.class)
                .getResultList();
    }

    // XSS reflechi : le parametre est renvoye tel quel dans une page HTML
    @GetMapping(value = "/hello", produces = "text/html")
    public String hello(@RequestParam String name) {
        return "<html><body><h1>Bonjour " + name + "</h1></body></html>";
    }

    // Algorithme de hachage faible (MD5)
    @GetMapping("/hash")
    public String hash(@RequestParam String value) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(value.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}