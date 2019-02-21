/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Kalpesh
 */

@NamedNativeQueries(
        @NamedNativeQuery(name = "getLectureId", query = "select get_lecture_id()")
)
@Entity
@Table(name = "lecture")
public class Lecture implements Serializable {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "lectureIdGenerator",
            strategy = "entities.LectureIdGenerator")
    @GeneratedValue(generator = "lectureIdGenerator")
    @Getter
    @Setter
    private String id;

    @Column(name = "date")
    @Getter
    @Setter
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;

    @Column(name = "count")
    @Getter
    @Setter
    private int count = 1;

    @Column(name = "ended")
    @Getter
    @Setter
    private boolean ended = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tcs_fid", foreignKey = @ForeignKey(name = "lecture_tcs_foreign_key"))
    @Getter
    @Setter
    Teaching teaching;

    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    @Getter
    private List<Attendance> attendances = new ArrayList<>();

    public Lecture() {
    }

    public Lecture(int count, Teaching teaching) {
        this.count = count;
        addTeaching(teaching);
        this.date = LocalDateTime.now();
    }

    public final void addTeaching(Teaching teaching) {
        teaching.addLecture(this);
    }

    @Override
    public String toString() {
        return getId() + " " + getTeaching();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id.equals(lecture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
