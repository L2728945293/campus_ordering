package cn.edu.jlu.po;

import lombok.Data;
import lombok.Getter;
@Data
public class NewAddressBody {
    private Integer user_id;
    private String name;
    private String phone;
    private String address;
    private Integer is_default;
}
