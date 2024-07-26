package pack.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Buser {

    @Id
    @Column(name = "buser_no")
    private int bno;

    @Column(name = "buser_name")
    private String bname;

    @Column(name="buser_tel")
    private String btel;

    @OneToMany(mappedBy = "buser")	// 하나의 부서는 여러명의 직원을 가질 수 있으니까
    private List<Jikwon> jikwonList;	// jikwonList가 읽힐거야

}