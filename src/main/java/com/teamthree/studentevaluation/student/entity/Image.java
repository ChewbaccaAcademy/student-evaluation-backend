package com.teamthree.studentevaluation.student.entity;

import com.teamthree.studentevaluation.student.helper.ImageUtil;

import javax.persistence.*;

@Entity
@Table(name = "student_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;

    @OneToOne(mappedBy = "image")
    private Student student;

    @Lob
    private byte[] data;

    public Image() {
    }

    public Image(String name, String type, byte[] imgByte) {
        this.name = name;
        this.type = type;
        this.data = imgByte;
    }

    public Image(Long id, String name, String type, byte[] imgByte) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = imgByte;
    }

    public void decompress() {
        this.data = ImageUtil.decompressBytes(data);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public byte[] getImgByte() {
        return data;
    }
}
