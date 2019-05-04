package top.huzhurong.mysql.transaction.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author chenshun00@gmail.com
 * @since 2019-05-03 10:03:05
 */
@Getter
@Setter
public class TestTransaction {
    /**
     * id
     */
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * email
     */
    private String email;
    /**
     * birth
     */
    private Date birth;
}