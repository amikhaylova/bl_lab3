package business.logic.lab2.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EmailMessageDTO implements Serializable {
    private String email;
    private Date beginningDate;
}
