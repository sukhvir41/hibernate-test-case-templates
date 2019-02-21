/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

/**
 *
 * @author sukhvir
 */
@Entity
@Table(name = "tcs")
public class Teaching implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_fid", foreignKey = @ForeignKey(name = "tcs_teacher_foreign_key"))
    @Getter
    @Setter
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_fid", foreignKey = @ForeignKey(name = "tcs_class_foreign_key"))
    @Getter
    @Setter
    private ClassRoom classRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_fid", foreignKey = @ForeignKey(name = "tcs_subject_foreign_key"))
    @Getter
    @Setter
    private Subject subject;

    @OneToMany(mappedBy = "teaching", fetch = FetchType.LAZY)
    @Getter
    @Setter
    List<Lecture> lectures = new ArrayList<>();

    public Teaching() {
    }

    public Teaching(ClassRoom classRoom, Subject subject) {
        this.classRoom = classRoom;
        this.subject = subject;
    }

    public Teaching(Teacher teacher, ClassRoom classRoom, Subject subject) {
        addTeacher(teacher);
        addClassRoom(classRoom);
        addSubject(subject);
    }

    /**
     * this method adds lecture to teachings and vice versa
     */
    public final void addLecture(Lecture lecture) {
        if (!lectures.contains(lecture)) {
            lectures.add(lecture);
            lecture.setTeaching(this);
        }
    }

    /**
     * this method adds class room to this teaching
     */
    public final void addClassRoom(ClassRoom classRoom) {
        setClassRoom(classRoom);
    }

    /**
     * this method adds subject to teaching
     */
    public final void addSubject(Subject subject) {
        setSubject(subject);
    }

    /**
     * this method adds teaching to teacher and vice versa
     */
    public final void addTeacher(Teacher teacher) {
        teacher.addTeaching(this);
    }

    @Override
    public String toString() {
        return subject + " - " + classRoom;
    }

}
