package cat.itb.m09_marccanals_apirest_persistent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserCheckDTO {
    private String username;
    private String avatar;
    private String rol;
}
