package com.example.demo.controller

import com.example.demo.model.Department
import com.example.demo.model.Employee
import com.example.demo.model.EmployeeMgr
import com.example.demo.service.DeptService
import com.example.demo.service.EmpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class HelloController {

//    1.ทำ CRUD ของ table emp
//    2.find ให้สามารถหาได้จากทุก column
//    3.return คนที่อยู่ภายใต้ mgr คนนึง ว่า มีกี่คน เงินเดือนรวมกันเท่าไหร
//    exam get ไปหาหลังบ้านด้วยค่า 7698 ให้ return ออกมาว่า
//    มีลูกน้องภายใต้การดูแลกี่คน และแต่ละคนเงินเดือนรวมกันกี่บาท

    @Autowired
    lateinit var EmpService: EmpService

    @Autowired
    lateinit var DeptService: DeptService

    @GetMapping("/findColumn/{nameColumn}/{value}")
    fun countSumMgr(
        @PathVariable("nameColumn") nameColumn: String,
        @PathVariable("value") value: String
    ): List<Employee>? {
        return EmpService.findByColumn(nameColumn, value)
    }

    @GetMapping("/countSumMgr")
    fun countSumMgr(@RequestParam("mgr") mgr: String): EmployeeMgr {
        return EmpService.countSumMgr(mgr)
    }

    @CrossOrigin("*")
    @GetMapping("/emp/getAll")
    fun getAll(): List<Employee> {
        return EmpService.getAll()
    }

    @CrossOrigin("*")
    @GetMapping("/dept/getAll")
    fun getdeptAll(): List<Department> {
        return DeptService.getDeptAll()
    }

    @GetMapping("/emp/getById/{empNo}")
    fun getById(@PathVariable("empNo") empNo: String): Employee {
        return EmpService.getById(empNo)
    }

    @PostMapping("/emp/addEmp")
    fun addEmp(@RequestBody emp: Employee): String {
        return EmpService.addEmp(emp)
    }

    @PatchMapping("/emp/updateEmp")
    fun updateEmp(@RequestBody emp: Employee): String {
        return EmpService.updateEmp(emp);
    }

    @DeleteMapping("/emp/deleteEmp")
    fun deleteEmp(@RequestParam("empNo") empNo: String): String {
        return EmpService.deleteEmp(empNo)
    }

    @GetMapping("/getEmpByEmpNoAndMgr/{empNo}/{mgr}")
    fun hello(
        @PathVariable("empNo") empNo: String,
        @PathVariable("mgr") mgr: String
    ): Employee {
        return EmpService.getEmpByEmpNoAndMgr(empNo, mgr)
    }

    @GetMapping("/calGrade/{score}")
    fun cal(@PathVariable("score") score: String): String {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        if (score.matches(regex)) {
            return EmpService.calGrade(score.toInt())
        } else {
            return "Input not correct."
        }
    }

    @PostMapping("/calculator")
    fun calculator(@RequestBody mathSentence: String): String {
//        val regex = "^\\s*[-+]?[0-9]*\\.?[0-9]+([-+*/]?([0-9]*\\.?[0-9]+))*\\s*\$".toRegex()
        return EmpService.calculator(mathSentence)
    }


}