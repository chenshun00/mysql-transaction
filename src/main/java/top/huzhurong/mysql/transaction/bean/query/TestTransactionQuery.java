package top.huzhurong.mysql.transaction.bean.query;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenshun00@gmail.com
 * @since 2019-05-03 10:03:05
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TestTransactionQuery extends BaseQuery {
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

    private Date birthStart;

    private Date birthEnd;

    private List<OrderFields> orderFields = new ArrayList<>();

    @Builder
    private static class OrderFields{
        @Getter
        @Setter
        private String orderBy;
        @Getter
        @Setter
        private String desc;
    }

    public TestTransactionQuery orderById(boolean desc){
        orderFields.add(OrderFields.builder().orderBy("id").desc(desc?"ASC":"DESC").build());
        return this;
    }
    public TestTransactionQuery orderByName(boolean desc){
        orderFields.add(OrderFields.builder().orderBy("name").desc(desc?"ASC":"DESC").build());
        return this;
    }
    public TestTransactionQuery orderByEmail(boolean desc){
        orderFields.add(OrderFields.builder().orderBy("email").desc(desc?"ASC":"DESC").build());
        return this;
    }
    public TestTransactionQuery orderByBirth(boolean desc){
        orderFields.add(OrderFields.builder().orderBy("birth").desc(desc?"ASC":"DESC").build());
        return this;
    }
}
