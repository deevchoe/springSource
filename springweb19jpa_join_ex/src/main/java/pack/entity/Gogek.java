package pack.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Gogek {

	@Id
    @Column(name="gogek_no")
    private int gno;

    @Column(name="gogek_name")
    private String gname;

    @Column(name="gogek_tel")
    private String gtel;
    
    @Column(name="gogek_jumin")
    private String jumin;

    @ManyToOne(fetch = FetchType.EAGER) // 여러명의 고객은 한명의 직원을 만나야하니까
    @JoinColumn(name="gogek_damsano")
    private Jikwon jikwon;
    
}