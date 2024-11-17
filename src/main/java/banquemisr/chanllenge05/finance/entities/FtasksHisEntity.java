package banquemisr.chanllenge05.finance.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "FTASKS_HIS")
public class FtasksHisEntity {
    @Id
    @Column(name = "TASKNO", nullable = false)
    private Integer taskno;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name="PRIORITY")
    String priority;

    @Column(name = "OLD_STATUS")
    String oldStatus;

    @Column(name = "NEW_STATUS")
    String newStatus;

    @Column(name = "DUE_DATE")
    Date dueDate;

    @Column(name = "UPDATED_DATE")
    Date updatedDate;

}
