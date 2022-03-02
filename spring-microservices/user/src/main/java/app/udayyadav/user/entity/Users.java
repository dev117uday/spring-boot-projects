package app.udayyadav.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;
    private String userName;
    private String userEmail;
    private Long DepartmentId;
}
