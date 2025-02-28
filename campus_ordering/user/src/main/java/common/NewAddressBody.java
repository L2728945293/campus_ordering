package common;

import lombok.Data;

@Data
public class NewAddressBody {
    private Integer user_id;

    private String name;

    private String phone;

    private String address;

    private Integer is_default;

}
