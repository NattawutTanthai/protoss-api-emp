package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "dept")
data class Department(
    @Id
    @Column(name = "deptno", nullable = false)
    val deptno: Int,
    @Column(name = "dname")
    val dname: String,
    @Column(name = "loc")
    val loc: String
)
