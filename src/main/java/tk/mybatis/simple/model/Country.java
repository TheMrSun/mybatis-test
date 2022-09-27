package tk.mybatis.simple.model;

import lombok.Data;

/**
 * @author Slience
 * @version 1.0
 */

@Data
public class Country {

    private Long id;
    private String countryName;
    private String countryCode;

}
