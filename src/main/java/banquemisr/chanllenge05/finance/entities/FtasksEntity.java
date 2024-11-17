package banquemisr.chanllenge05.finance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
//import javax.persistence.*;

@Data
@Entity
@Table(name = "FTASKS")
public class FtasksEntity {
    @Id
    @jakarta.persistence.GeneratedValue(generator = "FTASKSEQ", strategy = jakarta.persistence.GenerationType.SEQUENCE)
    @jakarta.persistence.SequenceGenerator(name = "FTASKSEQ", sequenceName = "FTASKSEQ", allocationSize = 1)
    @Column(name = "TASKNO", nullable = false)
    private Integer taskno;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name="PRIORITY")
    String priority;

    @Column(name = "STATUS")
    String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "DUE_DATE")
    Date dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "BUSDATE")
    Date busdate;

}
