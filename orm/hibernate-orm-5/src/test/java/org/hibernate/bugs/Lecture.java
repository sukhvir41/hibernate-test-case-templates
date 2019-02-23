/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.bugs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

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
    /*@GenericGenerator(name = "lectureIdGenerator",
            strategy = "entities.LectureIdGenerator")
    @GeneratedValue(generator = "lectureIdGenerator")*/
    private String id;

    @Column(name = "date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;

    @Column(name = "count")
    private int count = 1;

    @Column(name = "ended")
    private boolean ended = false;

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tcs_fid", foreignKey = @ForeignKey(name = "lecture_tcs_foreign_key"))
    @Getter
    @Setter
    private Teaching teaching;*/

    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();

    public Lecture() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }


    /*  public Lecture(int count, Teaching teaching) {
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
*/

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
