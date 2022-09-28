/*package oop.io.demo.auth.registrationtoken;

import java.beans.Transient;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import oop.io.demo.user.User;

@Entity
@Document("Token")
public class RegistrationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Long id;

    @Column(unique= true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private TimeStamp timeStamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    private User user;

    @Transient
    private boolean isExpired;
    
    
}
*/