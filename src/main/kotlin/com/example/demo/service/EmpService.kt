package com.example.demo.service

import com.example.demo.model.Employee
import com.example.demo.model.EmployeeMgr
import com.example.demo.repository.EmployeeRepository
import net.objecthunter.exp4j.ExpressionBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class EmpService {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun findByColumn(nameColumn: String, value: String): List<Employee> {
        val query =
            entityManager.createNativeQuery("SELECT * FROM emp WHERE $nameColumn = :value", Employee::class.java)
        when (nameColumn) {
            "hiredate" -> query.setParameter("value", LocalDate.parse(value))
            "sal" -> query.setParameter("value", value.toDouble())
            "commission_pct" -> query.setParameter("value", value.toDouble())
            "deptno" -> query.setParameter("value", value.toInt())
            else -> {
                query.setParameter("value", value)
            }
        }
        return query.resultList as List<Employee>
    }

    fun countSumMgr(mgr: String): EmployeeMgr {
        return employeeRepository.findByMgr(mgr)
    }

    fun getAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun getById(empNo: String): Employee {
        return employeeRepository.findByEmpNoEquals(empNo)
    }

    fun addEmp(emp: Employee): String {
        if (employeeRepository.existsById(emp.empNo)) {
            return "Add Fail"
        } else {
            employeeRepository.save(emp)
            return "Add Success"
        }
    }

    fun updateEmp(emp: Employee): String {
        try {
            var find_emp = employeeRepository.findByEmpNoEquals(emp.empNo)
            if (emp.ename !== null) find_emp.ename = emp.ename
            if (emp.job !== null) find_emp.job = emp.job
            if (emp.mgr !== null) find_emp.mgr = emp.mgr
            if (emp.hiredate !== null) find_emp.hiredate = emp.hiredate
            if (emp.sal !== null) find_emp.sal = emp.sal
            if (emp.commissionPct !== null) find_emp.commissionPct = emp.commissionPct
            if (emp.deptno !== null) find_emp.deptno = emp.deptno
            println(find_emp)
            employeeRepository.save(find_emp)
            return "Update Success"
        } catch (e: Exception) {
            return "Update Fail catch " + e.message
        }
    }

    fun deleteEmp(empNo: String): String {
        try {
            employeeRepository.deleteById(empNo)
            return "Delete Success"
        } catch (e: Exception) {
            return "Delete Fail " + e.message
        }
    }

    fun hello(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun getEmpByEmpNoAndMgr(empNo: String, mgr: String): Employee {
        return employeeRepository.findByEmpNo(empNo, mgr)
    }

    fun calGrade(score: Int): String {
        var grade = ""
        if (score > 100) {
            grade = "Your score gather than 100. not correct !!!"
        } else if (score >= 80) {
            grade = "A"
        } else if (score >= 75) {
            grade = "B+"
        } else if (score >= 70) {
            grade = "B"
        } else if (score >= 65) {
            grade = "C+"
        } else if (score >= 60) {
            grade = "C"
        } else if (score >= 55) {
            grade = "D+"
        } else if (score >= 50) {
            grade = "D"
        } else {
            grade = "F"
        }
        return grade
    }

    fun calculator(mathSentence:String) : String {
        try {
            var result = ExpressionBuilder(mathSentence).build().evaluate()
            return result.toString()
        } catch (e: Exception) {
            return "Error"
        }
    }
}