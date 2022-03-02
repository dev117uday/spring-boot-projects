package app.udayyadav.user.vo;

import app.udayyadav.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
    private Users users;
    private Department department;
}
