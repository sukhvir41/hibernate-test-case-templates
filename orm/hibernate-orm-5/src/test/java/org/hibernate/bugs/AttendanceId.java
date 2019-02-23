/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.bugs;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * @author development
 */
@Embeddable
public class AttendanceId implements Serializable {


    @Column(name = "lecture_fid")
    private String lectureId;

    @Column(name = "student_fid", columnDefinition = "uuid")
    private UUID studentId;

    public AttendanceId() {
    }

    public AttendanceId(String lectureId, UUID studentId) {
        this.lectureId = lectureId;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceId that = (AttendanceId) o;
        return lectureId.equals(that.lectureId) &&
                studentId.equals(that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureId, studentId);
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
