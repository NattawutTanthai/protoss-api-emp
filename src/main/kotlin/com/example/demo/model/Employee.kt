package com.example.demo.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "emp")
data class Employee(
    @Id
    @Column(name = "empno", nullable = false )
    var empNo : String,
    @Column(name = "ename")
    var ename : String?,
    @Column(name = "job")
    var job : String?,
    @Column(name = "mgr")
    var mgr : String?,
    @Column(name = "hiredate")
    var hiredate : LocalDate?,
    @Column(name = "sal")
    var sal : Double?,
    @Column(name = "commission_pct")
    var commissionPct : Double?,
    @Column(name = "deptno")
    var deptno : Int?,
    )