package ac.kr.smu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String uuid;

    @Column
    private String upload_path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Post post;
}
