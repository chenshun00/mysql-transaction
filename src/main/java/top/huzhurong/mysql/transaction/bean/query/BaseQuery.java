package top.huzhurong.mysql.transaction.bean.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chenshun00@gmail.com
 * @since 2019-05-03 10:03:05
 */
public abstract class BaseQuery {

    @Getter
    private Integer pageSize;

    public BaseQuery setPageSize(Integer pageSize) {
        if (pageSize <= 0) {
            pageSize = 20;
        }
        this.pageSize = pageSize;
        if (this.pageNo != null) {
            this.startRow = (this.pageNo - 1) * this.pageSize;
        } else {
            this.startRow = 0;
        }
        return this;
    }

    @Getter
    private Integer pageNo;

    /**
    * 偏移量 =  pageNo * pageSize
    * limit 20 , 5  =  pageNo * pageSize  = 1 * 20
    */
    @Getter
    @Setter
    private Integer startRow;

    public BaseQuery setPageNo(Integer pageNo) {
        if (pageNo < 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        if (this.pageSize != null) {
            this.startRow = (this.pageNo - 1) * this.pageSize;
        } else {
            this.startRow = 0;
        }
        return this;
    }
}
