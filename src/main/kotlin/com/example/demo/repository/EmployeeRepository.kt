package com.example.demo.repository

import com.example.demo.model.Employee
import com.example.demo.model.EmployeeMgr
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, String> {
    fun findByEmpNoEquals(@Param("empNo") empNo: String): Employee

    @Query("FROM Employee WHERE empNo = :empNo and mgr = :mgr")
    fun findByEmpNo(@Param("empNo") empNo: String, @Param("mgr") mgr: String): Employee

    @Query("SELECT sum(sal) sum , count(mgr) count FROM emp WHERE mgr = :mgr GROUP BY mgr ", nativeQuery = true)
    fun findByMgr(@Param("mgr") mgr: String): EmployeeMgr

//    @Query(value = "SELECT * FROM emp WHERE {nameColumn} = :value", nativeQuery = true)
//    fun findByColumn(@Param("nameColumn") nameColumn: String, @Param("value") value: String): List<Employee>

}