/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author sukhvir
 */
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

    @EmbeddedId
    private AttendanceId id;

    @Column(name = "marked_by_teacher")
    private boolean markedByTeacher = false;

    @Column(name = "attended")
    private boolean attended;

    @Column(name = "leave")
    private boolean leave = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lectureId")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private Student student;


    public AttendanceId getId() {
        return id;
    }

    public void setId(AttendanceId id) {
        this.id = id;
    }

    public boolean isMarkedByTeacher() {
        return markedByTeacher;
    }

    public void setMarkedByTeacher(boolean markedByTeacher) {
        this.markedByTeacher = markedByTeacher;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public boolean isLeave() {
        return leave;
    }

    public void setLeave(boolean leave) {
        this.leave = leave;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Attendance() {
    }

   /* public Attendance(Lecture theLecture, Student theStudent, boolean attended) {
        this.lecture = theLecture;
        this.student = theStudent;
        this.attended = attended;
        this.id = new AttendanceId(lecture.getId(), student.getId());
    }

    public Attendance(@NonNull Lecture lecture, @NonNull Student student, boolean attended, boolean markedByTeacher, boolean leave) {
        this.markedByTeacher = markedByTeacher;
        this.attended = attended;
        this.leave = leave;
        this.lecture = lecture;
        this.student = student;
        this.id = new AttendanceId(lecture.getId(), student.getId());
    }

    protected void setLecture(Lecture theLecture) {
        this.id.setLectureId(theLecture.getId());
    }

    protected void setStudent(Student theStudent) {
        this.id.setStudentId(theStudent.getId());
    }

    public Lecture getLecture() {
        return this.lecture;
    }

    public Student getStudent() {
        return this.student;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return lecture.equals(that.lecture) &&
                student.equals(that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecture, student);
    }
}
