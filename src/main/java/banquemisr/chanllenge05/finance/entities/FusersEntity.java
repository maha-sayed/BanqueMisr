package banquemisr.chanllenge05.finance.entities;

import lombok.Data;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Data
@Entity
@Table(name = "FUSERS")
public class FusersEntity implements Serializable {
    @Id
    @Column(name = "USRNO", nullable = false)
    private Integer usrno;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACTIVE")
    private Integer active;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "BRCODE", nullable = false)
    private Integer brcode;
}
