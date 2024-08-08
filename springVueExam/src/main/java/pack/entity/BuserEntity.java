package pack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "buser")
public class BuserEntity {

    @Id
    @Column(name = "buser_no")
    private String bno;

    @Column(name = "buser_name")
    private String bname;

    @Column(name="buser_tel")
    private String btel;

}
