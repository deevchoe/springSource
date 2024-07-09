package pack.model;

import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface SqlMapperInter {
    @Select("SELECT jikwon_no, jikwon_name, buser_name, substr(jikwon_ibsail,1,4) as jikwon_ibsail FROM jikwon INNER JOIN buser ON buser_num = buser_no")
    public List<JikwonDto> selectDataAll();

    @Select("SELECT buser_name, COUNT(jikwon_no) as cnt FROM jikwon INNER JOIN buser ON buser_num = buser_no GROUP BY buser_name")
    public List<JikwonDto> selectBuser();

    @Select("SELECT buser.buser_name, jikwon.jikwon_name, jikwon.jikwon_pay FROM jikwon INNER JOIN ( SELECT buser_num, MAX(jikwon_pay) AS maxpay FROM jikwon GROUP BY buser_num ) maxtab ON jikwon.buser_num = maxtab.buser_num AND jikwon.jikwon_pay = maxtab.maxpay INNER JOIN buser ON jikwon.buser_num = buser.buser_no")
    public List<JikwonDto> selectMaxpay();
}